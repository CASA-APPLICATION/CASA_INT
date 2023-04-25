package kr.co.casa_int.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gyutae park
 * @since 2023.04.10
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class updateUserInfo {
    private String nowPwd ;
    private String updatePwd;
    private String checkPwd;
    private String address1;
    private String address2;
}
