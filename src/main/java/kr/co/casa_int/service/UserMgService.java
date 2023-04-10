package kr.co.casa_int.service;

import kr.co.casa_int.entity.User;

/**
 * @author gyutae park
 * @since 2023.04.10
 */

public interface UserMgService {

    public boolean singUp(User userInfo) throws  Exception;

}
