package kr.co.casa_int.dto;


import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gyutae park
 * @since 2024.01.18
 */



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDto {

    private Integer id;

    private String loginId;

    private String passwd;

    private String roles;

    private String email;

    private String emailCert;

    private String idLock;

    private String nickname;

    private String auth;

    private String gender;

    private String imageUrl;

    private String sns;

    private String intro;

    private String phoneNumber;

    private Date termsAgreeDate;

    private String ban;

//    private String secession;
//    private List<String> roles = new ArrayList<>();
//
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.roles.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }
//
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getLoginId() {
//        return loginId;
//    }
//
//    public void setLoginId(String loginId) {
//        this.loginId = loginId;
//    }
//
//    public String getPasswd() {
//        return passwd;
//    }
//
//    public void setPasswd(String passwd) {
//        this.passwd = passwd;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getEmailCert() {
//        return emailCert;
//    }
//
//    public void setEmailCert(String emailCert) {
//        this.emailCert = emailCert;
//    }
//
//    public String getIdLock() {
//        return idLock;
//    }
//
//    public void setIdLock(String idLock) {
//        this.idLock = idLock;
//    }
//
//    public String getNickname() {
//        return nickname;
//    }
//
//    public void setNickname(String nickname) {
//        this.nickname = nickname;
//    }
//
//    public String getAuth() {
//        return auth;
//    }
//
//    public void setAuth(String auth) {
//        this.auth = auth;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }
//
//    public String getSns() {
//        return sns;
//    }
//
//    public void setSns(String sns) {
//        this.sns = sns;
//    }
//
//    public String getIntro() {
//        return intro;
//    }
//
//    public void setIntro(String intro) {
//        this.intro = intro;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public Date getTermsAgreeDate() {
//        return termsAgreeDate;
//    }
//
//    public void setTermsAgreeDate(Date termsAgreeDate) {
//        this.termsAgreeDate = termsAgreeDate;
//    }
//
//    public String getBan() {
//        return ban;
//    }
//
//    public void setBan(String ban) {
//        this.ban = ban;
//    }
//
//    public String getSecession() {
//        return secession;
//    }
//
//    public void setSecession(String secession) {
//        this.secession = secession;
//    }
//
//    public List<String> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<String> roles) {
//        this.roles = roles;
//    }
}
