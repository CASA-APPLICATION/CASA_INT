package kr.co.casa_int.repository;

import kr.co.casa_int.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDtoMgRepo extends JpaRepository<UserDto, Integer> {



}
