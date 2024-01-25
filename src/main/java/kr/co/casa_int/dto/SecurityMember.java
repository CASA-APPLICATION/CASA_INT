package kr.co.casa_int.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SecurityMember{// extends User {
//
//    private static final String ROLE_PREFIX = "ROLE_";
//    private static final long serialVersionUID = 1L;
//
//    public SecurityMember(kr.co.casa_int.entity.User member) {
//        super(member.getUid(), member.getUpw(), makeGrantedAuthority(member.getRoles()));
//    }
//
//    private static List<GrantedAuthority> makeGrantedAuthority(List<UserRole> roles){
//        List<GrantedAuthority> list = new ArrayList<>();
//        roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
//        return list;
//    }

}
