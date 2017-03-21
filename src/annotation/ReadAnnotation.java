package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.junit.Test;

public class ReadAnnotation {
	
	@Test
	public void readAnnotations() {

		//1.클래스 정보를 가져온다.
		Class persion = Persion.class;
		
		//2.클래스에 선언 된 필드를 찾는다.
		Field[] fields = persion.getDeclaredFields();	
		
		//3.필드에 어노테이션이 부착되어 있는지 확인
		for (Field f : fields) {
			Annotation[] anno = f.getAnnotations();
			try {
				//4.PersonInfo annotation을 가져옴
				PersionInfo pInfo = (PersionInfo)anno[0];
				
				// 출력
				System.out.println(pInfo.setInt());
				System.out.println(pInfo.setString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
