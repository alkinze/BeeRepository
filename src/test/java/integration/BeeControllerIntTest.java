package integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@WebAppConfiguration
public class BeeControllerIntTest {

	@Autowired
	private BeeService beeService;
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockmvc;

	// Sanity Test to see if Spring Context is actually loaded
	@Test
	public void contextLoads() {
	}
	
	@BeforeClass
	public void beforeUserIntegrationTest() {
		MockitoAnnotations.initMocks(this);
		mockmvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void getAllBeesTest() {
		List<Bee> allBees = new ArrayList<>();
		
		try {
			MvcResult result = mockmvc.perform(get("/bee/all"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print()).andReturn();
			
			//getList
			ObjectMapper om = new ObjectMapper();
			allBees = om.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Bee>>() {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Assertions.assertNotNull(allBees[0]);
	}//end getAllBeesTest
	
	@Test
	public void getBeeByNameTest() {
		Bee aBee = new Bee();
		
		try {
			MvcResult result = mockmvc.perform(get("/bee/species/forest"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print()).andReturn();
			
			//getList
			ObjectMapper om = new ObjectMapper();
			aBee = om.readValue(result.getResponse().getContentAsString(), new TypeReference<Bee>() {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Assertions.assertEquals(1, aBee.getId());
	}//end getBeeByNameTest
	
	@Test
	public void getBeeByDataTest() {
		List<Bee> dataBees = new ArrayList<>();
		try {
			MvcResult result = mockmvc.perform(get("/bee/data"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print()).andReturn();
			
			//getList
			ObjectMapper om = new ObjectMapper();
			dataBees = om.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Bee>>() {});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//end getBeeByDataTest
}
