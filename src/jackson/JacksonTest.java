package jackson;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

public class JacksonTest {

	
	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void rawToJson() throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, String> dummyData = new HashMap<>();
		dummyData.put("value1", "value1");
		dummyData.put("value2", "value2");
		
		System.out.println(mapper.writeValueAsString(dummyData));

		assertThat(mapper.writeValueAsString(dummyData), is("{\"value2\":\"value2\",\"value1\":\"value1\"}"));
		
	}
	
	@Test
	public void TreeModel() {
		
	}

}
