package kr.co.casa_int.repository;

import kr.co.casa_int.dto.UserDto;
import kr.co.casa_int.entity.User;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserMgRepo extends JpaRepository<User, Integer> {
    User findById(String userId);

}

