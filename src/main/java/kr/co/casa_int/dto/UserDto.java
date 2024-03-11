package kr.co.casa_int.dto;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.*;

/**
 * @author gyutae park
 * @since 2023.04.10
 * @실험 겸 임시로 만든 Dto
 */

@Entity
@Getter
@Builder
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class UserDto {


    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true, length=50)
    private String uid;

    @Column(nullable = false, length=200)
    private String upw;


    @Column(nullable = false, unique = true, length=50)
    private String useEmail;

    @Column()
    private String emailLock;

    @Column
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

    @Column
    private String leaveUser;

    @CreationTimestamp
    private Date regdate;

    @UpdateTimestamp
    private Date updatedate;



}
