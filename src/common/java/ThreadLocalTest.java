package common.java;

import java.util.Date;

import org.junit.Test;

class Context {
	public static ThreadLocal<Date> local = new ThreadLocal<Date>();
}

class A {

	public void a() throws InterruptedException {
		// 현재 쓰레드의 로컬 변수에 Date 객체를 저장한다.
		Context.local.set(new Date());
		Thread.sleep(2000);
		
		B b = new B();
		b.b();
		
		Context.local.remove();
	}

}

class B {
	public void b() {
		// 현재 쓰레드의 로컬 변수에 저장된 Date 객체를 읽어와 사용한다.
		Date date = Context.local.get();
		System.out.println(date);
		
		C c = new C();
		c.c();
	}
}

class C {
	public void c() {
		// 현재 쓰레드의 로컬 변수에 저장된 Date 객체를 읽어와 사용한다.
		Date date = Context.local.get();
		System.out.println(date);
	}
}

public class ThreadLocalTest {
	
	@Test
	public void test() throws InterruptedException {
		A a = new A();
		a.a();
		
		/*
		 A.a()에서 생성한 Date 객체를 B.b() 메서드나 C.c() 메서드에 파라미터로 전달하지 않는다는 것이다.
		 즉, 파라미터로 객체를 전달하지 않아도 한 쓰레드로 실행되는 코드가 동일한 객체를 참조할 수 있게 된다.
		 *   
		 ThreadLocal의 활용
		 ThreadLocal은 한 쓰레드에서 실행되는 코드가 동일한 객체를 사용할 수 있도록 해 주기 때문에 
		 쓰레드와 관련된 코드에서 파라미터를 사용하지 않고 객체를 전파하기 위한 용도로 주로 사용되며, 주요 용도는 다음과 같다.

		* 사용자 인증정보 전파 - Spring Security에서는 ThreadLocal을 이용해서 사용자 인증 정보를 전파한다.
		* 트랜잭션 컨텍스트 전파 - 트랜잭션 매니저는 트랜잭션 컨텍스트를 전파하는 데 ThreadLocal을 사용한다.
		* 쓰레드에 안전해야 하는 데이터 보관
		
		이 외에도 쓰레드 기준으로 동작해야 하는 기능을 구현할 때 ThreadLocal을 유용하게 사용할 수 있다.

		ThreadLocal 사용시 주의 사항
		쓰레드 풀 환경에서 ThreadLocal을 사용하는 경우 ThreadLocal 변수에 보관된 데이터의 사용이 끝나면 반드시 해당 데이터를 삭제해 주어야 한다.
		그렇지 않을 경우 재사용되는 쓰레드가 올바르지 않은 데이터를 참조할 수 있다.

		 */
	}

}
