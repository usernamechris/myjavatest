package common.java;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

interface Hello {
	String sayHello(String name);
	String sayHi(String name);
	String sayThankYou(String name);
}

class HelloTarget implements Hello {

	@Override
	public String sayHello(String name) {
		return "Hello " + name;
	}

	@Override
	public String sayHi(String name) {
		return "Hi " + name;
	}

	@Override
	public String sayThankYou(String name) {
		return "Thank You " + name;
	}
	
}

class HelloUppercase implements Hello { // proxy class
	
	Hello hello; // target
	
	public HelloUppercase(Hello hello) {
		this.hello = hello;
	}

	@Override
	public String sayHello(String name) {
		return hello.sayHello(name).toUpperCase();
	}

	@Override
	public String sayHi(String name) {
		return hello.sayHi(name).toUpperCase();
	}

	@Override
	public String sayThankYou(String name) {
		return hello.sayThankYou(name).toUpperCase();
	}
	
}

class UppercaseHandler implements InvocationHandler { // dynamic proxy
	//Hello target;
	Object target; // 여러종류의 인터페이스를 구현한 타깃에도 적용가능토록 Object타입으로 수정

	//public UppercaseHandler(Hello target) {
	public UppercaseHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		//String ret = (String) method.invoke(target, args);
		Object ret = (String) method.invoke(target, args);
		if (ret instanceof String /* && method.getName().startsWith("say") 조건 추가 가능*/) {
			return ((String)ret).toUpperCase();
		}
		else {
			return ret;
		}
	}
}

public class ProxyTest {

	@Test
	public void simpleProxy() {

		Hello hello = new HelloTarget();

		assertThat(hello.sayHello("Han"), is("Hello Han"));
		assertThat(hello.sayHi("Han"), is("Hi Han"));
		assertThat(hello.sayThankYou("Han"), is("Thank You Han"));
		
		// proxy 적용
		Hello proxiedHello = new HelloUppercase(new HelloTarget());

		assertThat(proxiedHello.sayHello("Han"), is("HELLO HAN"));
		assertThat(proxiedHello.sayHi("Han"), is("HI HAN"));
		assertThat(proxiedHello.sayThankYou("Han"), is("THANK YOU HAN"));
		
	}
	
	@Test
	public void dynamicProxy() {
		
		Hello proxiedHello = (Hello) Proxy.newProxyInstance(
				getClass().getClassLoader(), // 다이내믹 프록시가 정의되는 클래스 로더 지정
				new Class[] {Hello.class}, // 다이내믹 프록시가 구현해야 할 인터페이스. 한번에 하나이상 가능
				new UppercaseHandler(new HelloTarget())); // 부가기능과 위임 과련 오브젝트 제공
		
		assertThat(proxiedHello.sayHello("Han"), is("HELLO HAN"));
		assertThat(proxiedHello.sayHi("Han"), is("HI HAN"));
		assertThat(proxiedHello.sayThankYou("Han"), is("THANK YOU HAN"));
		
	}
}
