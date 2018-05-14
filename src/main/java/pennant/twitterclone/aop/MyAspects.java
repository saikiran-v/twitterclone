package pennant.twitterclone.aop;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@EnableAspectJAutoProxy
@Aspect
public class MyAspects {
	@Autowired
	Logger logger;

	//@Before("execution(* *(*))")
	public void f1(JoinPoint jp) {
		logger.info(jp.getSignature() + " is about to call from " + jp.getSignature());
		System.out.println("---- Setter is about to call from " + jp.getSignature());
	}

	@After("execution(* *(*))")
	public void f2(JoinPoint jp) {

		System.out.println(jp.getSignature() + "---- method  is called from " + jp.getClass());
	}

}
