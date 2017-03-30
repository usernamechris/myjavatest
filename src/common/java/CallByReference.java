package common.java;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CallByReference {

	@Test
	public void CallByReference1() {
		Dog aDog = new Dog("MAX");
		foo(aDog);
		assertThat(aDog.getName(), is("MAX"));
	}

	@Test
	public void CallByReference2() {
		Dog f = new Dog("f");

		changeReference(f);
		assertThat(f.getName(), is("f"));

		modifyReference(f);
		assertThat(f.getName(), is("c"));
	}
	
	private void modifyReference(Dog c) {
		c.setName("c");
	}

	private void changeReference(Dog a) {
		Dog b = new Dog("b");
		a = b;
	}

	private void foo(Dog d) {

		assertThat(d.getName(), is("MAX"));
		
		d = new Dog("Fox");
		assertThat(d.getName(), is("Fox"));
	}
}

class Dog {
	String name;
	
	public Dog(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}