package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class AspectV6Advice {

    @Around("hello.aop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            //@Before
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            //@AfterReturning
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        }catch (Exception e){
            //@AfterThrowing
            log.info("[log] {} ", joinPoint.getSignature()); //join point 시그니처
            throw e;
        }finally {
            //@After
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());

        }
    }

    @Before("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint){ //매개 변수를 받지 않아도 된다.
        log.info("[before] {}", joinPoint.getSignature());
        //joinPoint 실행은 알아서 해준다
    }

    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result){ // 리턴타입이 맞아야 실행시켜 준다
        log.info("[return] {}, return={}", joinPoint.getSignature(), result); //리턴값 변경은 안된다//@Around 는 가능
    }


    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.allOrder()", returning = "result")
    public void doReturn2(JoinPoint joinPoint, Integer result){ // 리턴타입이 맞아야 실행시켜 준다
        log.info("[return2] {}, return={}", joinPoint.getSignature(), result); //리턴값 변경은 안된다//@Around 는 가능
    }

    @AfterThrowing(value = "hello.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex){ //예외도 마찬가지로 타입이 맞아야 사용이 가능하다.
        log.info("[ex] {} message={}", ex, ex.getMessage());
    }

    @After(value = "hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint){
        log.info("[after] {}", joinPoint.getSignature());
    }
}
