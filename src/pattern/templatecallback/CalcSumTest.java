package pattern.templatecallback;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;



public class CalcSumTest {

	Calculator calculator;
	String numFilePath;
	
	@Before
	public void setUp() {
		this.calculator = new Calculator();
		this.numFilePath = getClass().getResource("numbers.txt").getPath();
	}
	
	@Test
	public void sumOfNumbers() throws IOException {
		assertThat(calculator.calcSum(this.numFilePath), is(10));
	}
	
	@Test
	public void multiplyOfNumbers() throws IOException {
		assertThat(calculator.calcMultiply(this.numFilePath), is(24));
	}
	
	@Test
	public void concatenateString() throws IOException {
		assertThat(calculator.concatenate(this.numFilePath), is("1234"));
	}

}




interface LineCallback<T> {
	T doSomethingWithLine(String line, T value);
}

interface BufferedReaderCallBack {
	Integer doSomethingWithReader(BufferedReader br) throws IOException;
}

class Calculator {
	public Integer calcSum(String filePath) throws IOException {
		LineCallback<Integer> sumCallback = 
				new LineCallback<Integer>() {
					
					@Override
					public Integer doSomethingWithLine(String line, Integer value) {
						return value + 	Integer.valueOf(line);
					}
				};
		return lineReadTemplate(filePath, sumCallback, 0);
		
//		BufferedReaderCallBack sumCallback = 
//				new BufferedReaderCallBack() {
//					
//					@Override
//					public Integer doSomethingWithReader(BufferedReader br) throws IOException {
//						Integer sum = 0;
//						String line = null;
//						while ((line = br.readLine()) != null) {
//							sum += Integer.valueOf(line);
//						}
//						return sum;
//					}
//				};
//		return fileReadTemplate(filePath, sumCallback);
		
	}
	
	public String concatenate(String filePath) throws IOException {
		LineCallback<String> concatenateCallback =
				new LineCallback<String>() {
				public String doSomethingWithLine(String line, String value) {
					return value + line;
				}};
		return lineReadTemplate(filePath, concatenateCallback, "");
	}
	
	public Integer calcMultiply(String filePath) throws IOException {
		
			LineCallback<Integer> multiplyCallback = 
					new LineCallback<Integer>() {
						
						@Override
						public Integer doSomethingWithLine(String line, Integer value) {
							return value * 	Integer.valueOf(line);
						}
					};
			return lineReadTemplate(filePath, multiplyCallback, 1);
			
//		BufferedReaderCallBack multiplyCallback = 
//				new BufferedReaderCallBack() {
//					
//					@Override
//					public Integer doSomethingWithReader(BufferedReader br) throws IOException {
//						Integer multiply = 1;
//						String line = null;
//						while ((line = br.readLine()) != null) {
//							multiply *= Integer.valueOf(line);
//						}
//						return multiply;
//					}
//				};
//		return fileReadTemplate(filePath, multiplyCallback);
	}
	
	public <T> T lineReadTemplate(String filePath, LineCallback<T> callback, T initVal) throws IOException {
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(filePath));
			T res = initVal;
			String line = null;
			
			while ((line = br.readLine()) != null) {
				res = callback.doSomethingWithLine(line, res);
			}
			return res;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if (br != null) {
				try { br.close();}
				catch (IOException e) { System.out.println(e.getMessage() );}
			}
		}
	}

	public Integer fileReadTemplate(String filePath, BufferedReaderCallBack callback) throws IOException {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(filePath));
			int ret = callback.doSomethingWithReader(br);
			return ret;

		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw e;
		}
		finally {
			if (br != null) {
				try { br.close();}
				catch (IOException e) { System.out.println(e.getMessage() );}
			}
		}
	}
	
}