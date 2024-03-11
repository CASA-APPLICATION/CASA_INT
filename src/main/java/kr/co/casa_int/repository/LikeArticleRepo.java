package kr.co.casa_int.repository;

import jakarta.persistence.Column;
import kr.co.casa_int.dto.LikeArticleDto;
import kr.co.casa_int.entity.LikeArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeArticleRepo extends JpaRepository<LikeArticle, Integer> {

    // 사용자 pk 로 북마크 모든 항목 조회.
    public List<LikeArticle> findByUserId(String userId);

    // 유저 아이디로 삭제
    public String deleteByUserIdAndArticleId(String userId, String articleId);
}


//    @Column(name = "user_id")
//    private String userId;

