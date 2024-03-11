package kr.co.casa_int.repository;

import kr.co.casa_int.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository

public interface UserMgRepo extends JpaRepository<User, Integer> {


    // userUid 로 유저 정보 탐색
    User findByUid(String uid);

    // 유저 고유 userId 번호로 유저 정보 탐색디
    Optional<User> findById(Long id);

}

