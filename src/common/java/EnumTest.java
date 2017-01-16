package common.java;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

enum Level {
	/*
	GOLD, SILVER, BASIC;
	
	private Level() {
		
	}
	*/
	
	GOLD(3, null), SILVER(2, GOLD), BASIC(1, SILVER);
	
	private final int value;
	private final Level next;
	
	private Level(int value, Level next) { // 무조건 private. 문법
		this.value = value;
		this.next = next;
	}

	public int intValue() {
		return value;
	}

	public Level nextLevel() {
		return next;
	}
	
	public static Level valueOf(int value) {
		switch(value) {
		case 1: return BASIC;
		case 2: return SILVER;
		case 3: return GOLD;
		default: throw new AssertionError("Unknown value: " + value);
		}
	}
	
}


public class EnumTest {
	
	@Test
	public void enumTest() {
		Level level = Level.SILVER; // 생성자가 private이르모 객체 생성은 안됨
		assertThat(level.nextLevel(), is(Level.GOLD));
		
		Level[] levels = Level.values();
		assertThat(levels[0], is(Level.GOLD));
		assertThat(levels[1], not(Level.GOLD));
		assertThat(levels[2], is(Level.BASIC));
		
	}
}
