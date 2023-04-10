package kr.co.casa_int.servicepl;

import kr.co.casa_int.entity.User;
import kr.co.casa_int.repository.UserMgRepo;
import kr.co.casa_int.service.UserMgService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author gyutae park
 * @since 2023.04.10
 */

@Service
@RequiredArgsConstructor
public class UserMgServicepl implements UserMgService {

    private final UserMgRepo repository;

    public boolean singUp(User userInfo) throws  Exception{


        return true;
    }

}
