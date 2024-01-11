package kr.co.casa_int.service;

import kr.co.casa_int.repository.UserMgRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService { //implements UserDetailsService {

    private final UserMgRepo userMgRepo;

//    @Override
//    public User loadUserByUserName(String loginId){
//        return userMgRepo.findById(loginId)
//                .orElseThrow(() -> new IllegalArgumentException(loginId));
//    }

}
