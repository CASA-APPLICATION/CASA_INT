package kr.co.casa_int.service;

import kr.co.casa_int.dto.updateUserInfo;
import kr.co.casa_int.entity.Article;
import kr.co.casa_int.entity.LikeArticle;
import kr.co.casa_int.entity.User;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

/**
 * @author gyutae park
 * @since 2023.04.10
 */

public interface UserMgService {

    // 탈퇴
    public ResponseEntity<String> leaveMember(Long id);
    // 장바구니 보기
    public ResponseEntity<List<Article>> showBasket(Principal principal) throws Exception;
    // 장바구니 담기
    public ResponseEntity<String> addBasket(Article article, Principal principal) throws Exception;
    // 장바구니 삭제
    public ResponseEntity<String> deleteBasket(Article article, Principal principal) throws Exception;
    // 작품 좋아요
    public ResponseEntity<String> likeArticle(LikeArticle likeArticle, Principal principal) throws  Exception;
    // 작품 좋아요 해제
    public ResponseEntity<String> unLikeArticle(LikeArticle likeArticle, Principal principal) throws  Exception;

    // 유저 인증키, 문자넘버 확인
    //public boolean checkUserAuth(String uuid, String authKey) throws  Exception;
    // 회원정보 수정
    //public String updateUser(updateUserInfo userInfo, User loginUser) throws Exception;

}
