package kr.co.casa_int.repository;

import kr.co.casa_int.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository

public interface UserMgRepo extends JpaRepository<User, Integer> {


    User findByUid(String uid);

    Optional<User> findById(Long id);

}

