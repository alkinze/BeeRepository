package unit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class BeeControllerUnitTests {

	//mock service
	@Mock
	private BeeService beeService;
	
	@InjectMocks
	private BeeController beeController;
	
	private MockMvc mockMvc;
	
	//Universal Test Data
	private List<Bee> beeList = new ArrayList<Bee>();
	private Bee oneBee = new Bee();
	
	
	@BeforeSuite
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(BeeController).build();
		//fill List
		
		
	}//end setup
	
	@Test
	public void getAllBeesTest() {
		Mockito.when(this.beeService.findAll()).thenReturn(beeList);
		try {
			mockMvc.perform(get("/bee/all")).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print()).andReturn();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}//end getAllBeesTest
	
	@Test
	public void getBeeByNameTest() {
		Mockito.when(this.beeService.findBySpecies()).thenReturn(oneBee);
		try {
			mockMvc.perform(get("/bee/species/forest")).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print()).andReturn();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}//end getBeeByNameTest
	
	@Test
	public void getBeeByData() {
		Mockito.when(this.beeService.getByData()).thenReturn(beeList);
		try {
			mockMvc.perform(get("/bee/data")).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print()).andReturn();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
