package kr.co.casa_int.dto;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gyutae park
 * @since 2023.04.10
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer userSequenceId;

    private String password;

    private String userEmail;

    private String userBirth;

    private String userNickname;

    private String gender;

    private String admin;

    private String role;

    private List<String> roles = new ArrayList<>();

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


}
