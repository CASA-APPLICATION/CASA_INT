package kr.co.casa_int.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
/**
 * @author gyutae park
 * @since 2023-08-07 ~ing
 */
public class LikeArticle {

    @Id
    @Column(name = "like_pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int likePk;

    //@ManyToOne
    //@JoinColumn(name ="user_id")
    // uid 말고 로그인할 아이디로 검색.
    @Column(name = "user_id")
    private String userId;

//    @ManyToOne
//    @JoinColumn(name = "article_id")
    @Column(name = "article_id")
    private String articleId;

    @Column(name = "date")
    @CreationTimestamp
    private Date date ;

    public void updateLikeArticle(int likePk, String userId, String articleId, Date date){
        this.likePk = likePk;
        this.userId = userId;
        this.articleId = articleId;
        this.date = date;
    }

}
