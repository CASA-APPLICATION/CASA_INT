package kr.co.casa_int.utils;

import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 * @author gyutae park
 * @since 2023.04.25
 */
@Component
@RequiredArgsConstructor
public class PhoneNumberChk {

    final DefaultMessageService messageService;
    private final RedisUtil redisUtil;

    public PhoneNumberChk(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
        // 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
        this.messageService = NurigoApp.INSTANCE.initialize("INSERT_API_KEY", "INSERT_API_SECRET_KEY", "https://api.coolsms.co.kr");
    }
    // coolsms 를 이용할 계획인데, 무료 유료 어떻게 할 것 인지.
    public HashMap<String, Object> sendMessage(String phoneNumber) throws Exception{

        // 난수 생성
        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }

        // 메세지 보내기.
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        // 카사 전화번호
        message.setFrom("발신번호 입력");
        message.setTo(phoneNumber);
        message.setText("핫띵크 휴대폰인증 테스트 메시지 : 인증번호는" + "["+numStr+"]" + "입니다.");

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));

        // redis 에 인증링크를 저장
        UUID uuid = UUID.randomUUID();
        // redis 에 key : value -> uuid : 난수값 생성
        redisUtil.setDataExpire(uuid.toString(), numStr, 60 * 30L);

        HashMap<String, Object> result = new HashMap<String, Object>();
        // uuid 로 할지, phone number 로 할지 고민
        result.put("uuid",uuid);
        result.put("phoneNumber",phoneNumber);
        result.put("authKey", numStr);

        return result;

    }
}
