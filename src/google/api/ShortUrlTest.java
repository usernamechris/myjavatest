package google.api;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class ShortUrlTest {
	private static final String URL_GOOGL_SERVICE = "https://www.googleapis.com/urlshortener/v1/url";
	private static final String GOOGLE_API_KEY = "AIzaSyDhLvEQzGvv7Dv2hoPy_W7Ci6cut8TVWrY";
	private static final String requestURL = URL_GOOGL_SERVICE + "/?key=" + GOOGLE_API_KEY;

	private String testLongUrl = "http://www.naver.com/";
	
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8089); // No-args constructor defaults to port 8080
	
	@Test
	public void shorten() throws Exception {
		assertThat(getShortUrl(testLongUrl), containsString("goo.gl"));
	}
	
	@Test
	public void differentUrl() throws Exception {
		
		List<String> urlList = new ArrayList<String>();
		urlList.add("www.naver.com");
		urlList.add("www.naver.com/");
		urlList.add("http://www.naver.com");
		urlList.add("http://naver.com");
		urlList.add("https://www.naver.com");
		
		for (String longUrl : urlList) {
			System.out.println(longUrl + " : " + getShortUrl(longUrl));
		}
	}

	private String getShortUrl(String longUrl) throws Exception {
		HttpClient httpClient = HttpClientBuilder.create().build();
		
		HttpPost request = new HttpPost(requestURL);
		StringEntity params = new StringEntity(urlToJsonRequest(longUrl));
		request.addHeader("content-type", "application/json");
		request.setEntity(params);

		HttpResponse response = httpClient.execute(request);
		    
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> resultJsonMap = mapper.readValue(response.getEntity().getContent(), Map.class);

		//System.out.println(resultJsonMap);
		assertThat(resultJsonMap.get("error") , nullValue());	
		
	    return resultJsonMap.get("id").toString();
	}
	
	private String urlToJsonRequest(String longUrl) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, String> dummyData = new HashMap<>();
		dummyData.put("longUrl", longUrl);
		
		return mapper.writeValueAsString(dummyData);

	}
	
	
}
