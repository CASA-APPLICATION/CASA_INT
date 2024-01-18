package kr.co.casa_int.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class User  {


    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true, length=50)
    private String uid;


    @Column(nullable = false, length=200)

    private String upw;


    @Column(nullable = false, unique = true, length=50)
    private String useEmail;

    @CreationTimestamp
    private Date regdate;

    @UpdateTimestamp
    private Date updatedate;

    // ROLE_USER, ROLE_ADMIN
    @Column(nullable = false)
    private String role;

//    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//    @JoinColumn(name="uid")
//    private List<UserRole> roles;


}
