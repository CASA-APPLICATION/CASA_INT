package kr.co.casa_int.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.*;

/**
 * @author gyutae park
 * @since 2023.04.10
 */

@Entity
@Getter
@Builder
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class User {


    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true, length=50)
    private String uid;

    @Column(nullable = false, length=200)
    private String upw;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userResidentRegistrationNumber;

    @Column(nullable = false, unique = true, length=50)
    private String useEmail;

    @ColumnDefault("False")
    private String emailLock;

    @ColumnDefault("False")
    private String idLock;

    @Column(nullable = false, unique = true, length = 10)
    private String nickName;

    // 20240124
    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String gender;

    @Column
    private String imgURL;

    @Column
    private String snsList;

    @Column
    private String infoSelf;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String agreeRule;

    @Column
    private String foreverBan;

    //  장바구니 -- Article 의 id 를 넣는다.
    @Column
    private List<Integer> shoppingBaskets;
    public void updateShoppingBaskets(Integer article){
        List<Integer> originBaskets = this.shoppingBaskets;
        originBaskets.add(article);
        this.shoppingBaskets = originBaskets;
    }

    //  작품 좋아요
    @Column
    private List<String> likeArticles;

    //  작품 북마크
    @Column
    private List<String> bookMarks;

    @ColumnDefault("False")
    private String leaveUser;

    @CreationTimestamp
    private Date regdate;

    @UpdateTimestamp
    private Date updatedate;



}
