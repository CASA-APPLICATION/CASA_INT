package kr.co.casa_int.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Integer id;

    // 유저 Id
    @Column(name = "USER_LOGIN_ID", nullable = false, unique = true)
    private String loginId;

    @Column(name = "USER_PASSWD", nullable = false)
    private String passwd;

    @Column(name = "USER_EMAIL", nullable = false)
    private String email;

    @Column(name = "USER_EMAIL_CERT", nullable = false)
    private String emailCert;

    @Column(name = "USER_ID_LOCK", nullable = false)
    private String idLock;

    @Column(name = "USER_NICKNAME", nullable = false)
    private String nickname;

    @Column(name = "USER_AUTH", nullable = false)
    private String auth;

    @Column(name = "USER_GENDER", nullable = false)
    private String gender;

    @Column(name = "USER_IMAGE_URL", nullable = false)
    private String imageUrl;

    @Column(name = "USER_SNS")
    private String sns;

    @Column(name = "USER_INTRO")
    private String intro;

    @Column(name = "USER_PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "USER_TERMS_AGREE_DATE")
    private Date termsAgreeDate;

    @Column(name = "USER_BAN", nullable = false)
    private String userBan;





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
        return nickname;
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
