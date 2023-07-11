package kr.co.casa_int.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

public class User implements UserDetails {

    @Id
    private String uuid;

    // 유저 seq
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_SEQUENCE_ID")
    private Integer userSequenceId;

    // 유저 Id
    @Column(name = "USER_ID", nullable = false)
    private String id;

    @Column(name = "USER_PASSWORD", nullable = false)
    private String password;

    @Column(name = "USER_EMAIL", nullable = false, unique = true)
    private String userEmail;

    // 이메일 인증 유무
    @Column(name = "USER_EMAIL_CERTIFICATION", nullable = false, unique = true)
    private String userEmail_certification;

    // 계정 잠김 유무
    // 영구밴 넣으면 될듯.
    // 탈퇴회원도 넣어도 될듯.
    @Column(name = "USER_BEN", nullable = false, unique = true)
    private String userBEN;

    @Column(name = "USER_NICKNAME", nullable = false, unique = true)
    private String userNickname;

    /*
    // 권한목록
    @Column(name = "USER_AUTH_LIST")
    private List<String> userAUTH;
    */
    @Column(name = "USER_GENDER")
    private String gender;

    @Column(name = "USER_IMG_URL")
    private String imgURL;

    @Column(name = "USER_SNS")
    private String sns;

    @Column(name = "USER_INTRO")
    private String intro;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "USER_AGREE_DATE")
    private String agreeDate;

    /*
    @Column(name = "USER_BIRTH")
    private String userBirth;
    */

    @Column(name = "ADMIN")
    private String admin;

    // 권한 목록
    @Column(name = "ROLE")
    private String role;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return userEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
