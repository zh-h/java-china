package org.javachina.config;

import com.blade.aop.AbstractMethodInterceptor;
import com.blade.aop.Invocaction;
import com.blade.ioc.annotation.Component;

@Component
public class AopInterceptor extends AbstractMethodInterceptor{
	
	@Override
	public Object doInvoke(Invocaction invocaction) throws Throwable {
		System.out.println("do invoke");
		return invocaction.invoke();
	}
	
}
