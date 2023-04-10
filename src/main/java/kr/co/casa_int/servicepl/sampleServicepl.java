package kr.co.casa_int.servicepl;

import kr.co.casa_int.service.sampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author gyutae park
 * @since 2023.04.10
 */

@Service
@Log4j2
@RequiredArgsConstructor

public class sampleServicepl implements sampleService {

    // 의존성 기입
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    // 메소드 기입

    public void sampleMethod() throws  Exception{
        logger.info("hello Casa");
    }
}
