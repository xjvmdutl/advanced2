package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1){
        log.info("callServiceV1 setter={}", callServiceV1.getClass());
        this.callServiceV1 = callServiceV1;
    }

    /*
    @Autowired
    public CallServiceV1(CallServiceV1 callServiceV1){
        //빈등록 전에 나를 의존성을 주입하려고 하니 안된다.
        this.callServiceV1 = callServiceV1;
    }
    */

    public void external(){
        log.info("call external"); //외부 호출
        callServiceV1.internal(); //외부 메서드 호출
    }

    public void internal(){
        log.info("call internal");
    }
}
