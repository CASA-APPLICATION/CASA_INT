package kr.co.casa_int.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@Log4j2
public class IdAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final String uid;

    public IdAuthenticationToken(String uid, Object credentials){
        super(null, credentials);;
        this.uid =uid;
    }
    public String getUid(){
        return uid;
    }

}
