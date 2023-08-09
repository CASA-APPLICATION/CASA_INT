package kr.co.casa_int.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "user_id")
    private int userId;

//    @ManyToOne
//    @JoinColumn(name = "article_id")
    @Column(name = "article_id")
    private int articleId;

    @Column(name = "date")
    private String date ;
}
