package kr.co.casa_int.repository;

import kr.co.casa_int.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoUserMgRepo extends JpaRepository<User, Integer> {

    String findById(String id);

    String findByPasswd(String passwd);

    String findByEmail(String email);

    //String findByNickName(String nickName);

    String findByPhoneNumber(String phoneNumber);

}
