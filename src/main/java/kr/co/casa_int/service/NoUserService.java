package kr.co.casa_int.service;

import kr.co.casa_int.entity.User;

import java.util.HashMap;
import java.util.Objects;

public interface NoUserService {

    public HashMap<String, String> registerNewMember(User userInfo) throws Exception;

}
