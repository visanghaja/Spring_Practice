package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // 이걸 적어주어야지 AOP!
@Component // 이렇게 하면 굳이 config 에 Bean으로 안 넣어도 ok!
public class TimeTraceAop {

    @Around("execution(* hello.hello_spring..*(..))")
    // 공통 관심 사항을 어디에 적용 할 것인지..
    // hello.hello_spring 여기 아래다가 다 적용
    //"execution(* hello.hello_spring.service..*(..))" 이러면 service에 있는거 다
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("Start : " + joinPoint.toString());
        try {
            Object result = joinPoint.proceed(); // 다음 메소드로 진행
            return result;
            // inline 가능!
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = (finish - start);
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
