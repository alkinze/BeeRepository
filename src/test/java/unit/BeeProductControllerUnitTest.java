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

public class BeeProductControllerUnitTest {
	//mock service
	@Mock
	private BeeProductService beeProductService;
	
	@InjectMocks
	private BeeProductController beeProductController;
	
	private MockMvc mockMvc;
	
	//Universal Test Data
	private List<BeeProduct> beeProductList = new ArrayList<BeeProduct>();
	private BeeProduct oneBeeProduct = new BeeProduct();
	
	
	@BeforeSuite
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(BeeProductController).build();
		//fill List
		
		
	}//end setup
	
	@Test
	public void getBeeProductByBeeTest() {
		Mockito.when(this.beeProductService.findAll()).thenReturn(beeProductList);
		try {
			mockMvc.perform(get("/beeproduct/bee/forest")).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print()).andReturn();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}//end getBeeProductByBeeTest
	
	@Test
	public void getBeeProductByProductTest() {
		Mockito.when(this.beeProductService.findBySpecies()).thenReturn(beeProductList);
		try {
			mockMvc.perform(get("/beeproduct/product/honeycomb")).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print()).andReturn();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}//end getBeeProductByProductTest
}
