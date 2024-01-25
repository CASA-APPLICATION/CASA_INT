package kr.co.casa_int.entity;

import jakarta.persistence.*;
import lombok.*;
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
public class User { //implements UserDetails {


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

//    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
//    @Enumerated(EnumType.STRING)


    // ROLE_USER, ROLE_ADMIN

//    @ManyToMany(cascade=CascadeType.MERGE)
//    @JoinTable(
//    name="user_role",
//    joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
//    inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})



//    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<UserRole> roles;


    // 20240124
    @Column(nullable = false)
    private String role;


//    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//    @JoinColumn(name="uid")
//    private List<UserRole> roles;

    //출처: https://zeroco.tistory.com/102 [zeroco:티스토리]
//    @ManyToMany(cascade=CascadeType.MERGE)
//    @JoinTable(
//            name="user_role",
//            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
//            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
//    private List<Role> roles;



}
