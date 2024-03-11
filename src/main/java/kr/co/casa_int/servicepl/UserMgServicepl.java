package kr.co.casa_int.servicepl;

import kr.co.casa_int.dto.LikeArticleDto;
import kr.co.casa_int.dto.UserDto;
import kr.co.casa_int.entity.Article;
import kr.co.casa_int.entity.LikeArticle;
import kr.co.casa_int.entity.User;
import kr.co.casa_int.repository.ArticleRepo;
import kr.co.casa_int.repository.LikeArticleRepo;
import kr.co.casa_int.repository.NoUserMgRepo;
import kr.co.casa_int.repository.UserMgRepo;
import kr.co.casa_int.service.UserMgService;
import kr.co.casa_int.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author gyutae park
 * @since 2023.04.10
 */

@Service
@Log4j2
@RequiredArgsConstructor
public class UserMgServicepl implements UserMgService {

    private final UserMgRepo repository;
    private final PasswordEncoder passwordEncoder;

    // 현재 안 씀
    // private final RedisUtil redisUtil ;

    private final NoUserMgRepo noUserMgRepo;
    private final UserMgRepo userMgRepo;
    private final ArticleRepo articleRepo;
    private final LikeArticleRepo likeArticleRepo;
    private final ModelMapper modelMapper;

    // 20240308 금 프포필 수정
    @Transactional
    public ResponseEntity<String> modifyProfile(User user, Principal principal) throws  Exception{

        log.info("modifyProfile Service login user =[{}]", principal.getName());

        // 만약 현재 로그인한 사용자와 들어온 정보가 다를시에 따른 방어로직
        if (user.getUid().equals(principal.getName()) == false){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        // 만약 현재 로그인한 사용자와 들어온 정보가 같을시, 예외문으로 방어하여 업데이트 진행.
        try {
            return new ResponseEntity<>(userMgRepo.save(user).toString(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(userMgRepo.save(user).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 장바구니 보기
    public ResponseEntity<List<Article>> showBasket(Principal principal) throws Exception {

        // 유저 정보 가져오기 -- 여기에 basket 정보가 들어있다.
        User userInfo = userMgRepo.findByUid(principal.getName());
        // 작품 정보들을 가져온다.
        List<Integer> articleIds = new ArrayList<>();
        articleIds = userInfo.getShoppingBaskets();

        List<Article> articles = new ArrayList<>();
        for (int i = 0 ; i <= articleIds.size() ; i ++) {
            // jpa 에서 optional 로 주기 때문에 마지막에 .get() 으로 엔티티로 변환한다.
            articles.add(articleRepo.findById(articleIds.get(i)).get());
        }

        try {
            return new ResponseEntity<>(articles, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(articles, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 20240311 장바구니 담기
    @Transactional
    public ResponseEntity<String> addBasket(Article article, Principal principal) throws Exception{

        log.info("addBasket Service login user =[{}]", principal.getName());

        // 현재 로그인한 유저 정보
        User userInfo = userMgRepo.findByUid(principal.getName());

        // 장바구니 항목이 중복되는지 확인
        // save 에서 걸러주지만 오류 메세지를 다르게 내기 위해 개발
        if(userInfo.getShoppingBaskets().isEmpty() == false) {
            for(int i = 0 ; i <= userInfo.getShoppingBaskets().size() ; i++){
                // 만약 중복되는 사항이 있다면
                if(userInfo.getShoppingBaskets().get(i) == article.getArticleId()){
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
            }
        }

        // 만약 중복되는 장바구니 항목이 없다면
        // 유저의 장바구니항목 추가
        // 해당 메소드는 User Entity 확인
        userInfo.updateShoppingBaskets(article.getArticleId());
        // article 의 pk 를 User.shoppingBasket 에 넣어줘야한다.
        try {
            return new ResponseEntity<>(userMgRepo.save(userInfo).toString(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(userMgRepo.save(userInfo).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 장바구니 삭제 기능
    @Transactional
    public ResponseEntity<String> deleteBasket(Article article, Principal principal) throws Exception {

        log.info("deleteBasket login user info =[{}]", principal.getName());

        try {

        }catch (Exception e){

        }
        return null;
    }

    // 20240226 북마크 기능
    // 20240311 북마크 -> 좋아요로 변경
    @Transactional
    public ResponseEntity<String> likeArticle(LikeArticle likeArticle, Principal principal) throws  Exception {

        // 현재 사용자 로그 출력
        log.info("bookMark Service login user =[{}]", principal.getName());

        // 사용자 북마크 중복 검사. -- 이미 북마크가 되어있다면.
        List<LikeArticle> checkPoint = new ArrayList<>();
        checkPoint = likeArticleRepo.findByUserId(principal.getName());
        for ( int userChekPoingSize = 0 ; userChekPoingSize <= checkPoint.size() ; userChekPoingSize++ ) {
            if ( checkPoint.get(userChekPoingSize).getArticleId().equals(likeArticle.getArticleId()) ){
                return new ResponseEntity<>(likeArticle.toString(), HttpStatus.CONFLICT);
            }
        }

        // 북마크 등록
        try {
            likeArticleRepo.save(likeArticle);
            return new ResponseEntity<>(likeArticle.toString(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 20240308 북마크 해제 기능
    @Transactional
    public ResponseEntity<String> unLikeArticle(LikeArticle likeArticle, Principal principal) throws  Exception{
        log.info("unBookMark Service login user =[{}]", principal.getName());

        // 좋아요 작품에서 회원 아이디로 검색 후, 해당 내역 삭제
        // where 유저 아이디, 작품 아이디
        String response = likeArticleRepo.deleteByUserIdAndArticleId(principal.getName(), likeArticle.getArticleId());

        try {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * @apiNote 회원탈퇴
     * @param id
     * @return
     */
    @Transactional
    public ResponseEntity<String> leaveMember(Long id) {
        try {
            Optional<User> user = userMgRepo.findById(id);
            user.ifPresent(value -> value.setLeaveUser("true"));
            return new ResponseEntity<>(user.toString(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 사용자 문자 인증
    // 1. redis 인증키 확인
    // 2. 문자 인증넘버 확인
    //public boolean checkUserAuth(String uuid, String authKey) throws  Exception{

        // 1.
//        String userAuthKey = redisUtil.getData(uuid);
//        if ( userAuthKey == null ){
//            throw new Exception("유효하지 않은 인증키입니다.");
//        }
//        // 2.
//        else {
//            if ( userAuthKey.equals(authKey) ){
//                // redis 에 키를 사용했으니 삭제한다.
//                redisUtil.deleteData(uuid);
//                return true;
//            }
//            else{
//                return false;
//            }
//        }
    //}

    // 회원정보 수정
    // 비밀번호와 같은 경우는, 사용자가 입력한 비밀번호를 암호화하여 DB에 저장된 값과 비교하여 매칭한다.
    //@Transactional
    //public String updateUser(updateUserInfo userInfo, User loginUser) throws  Exception{

//
//
//        ModelMapper modelMapper = new ModelMapper();
//        String userEncoderPwd = passwordEncoder.encode(userInfo.getNowPwd());
//
//        UserDto upadteUserInfo = new UserDto();
//        upadteUserInfo = modelMapper.map(loginUser, UserDto.class);
//
//        // 만약 현재 비밀번호가 일치한다면. || 비밀번호 재확인도 알맞게 입력하였다면.
//        if ( loginUser.getPassword().equals(userEncoderPwd) && userInfo.getUpdatePwd().equals(userInfo.getCheckPwd())) {
//            upadteUserInfo.setPassword(userInfo.getUpdatePwd());
//            return "updateUser Success";
//        }
//        // 만약 현재 비밀번호가 일치하지 않거나, 비밀번호 재확인이 일치하지 않는다면.
//        else {
//            return "updateUser Fail";
//        }
//
//    }

}
