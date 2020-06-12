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

public class ProductControllerUnitTest {
	//mock service
	@Mock
	private ProductService productService;
	
	@InjectMocks
	private ProductController productController;
	
	private MockMvc mockMvc;
	
	//Universal Test Data
	private List<Product> productList = new ArrayList<Product>();
	private Product oneProduct = new Product();
	
	
	@BeforeSuite
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(ProductController).build();
		//fill List
		
		
	}//end setup
	
	@Test
	public void getAllProductsTest() {
		Mockito.when(this.productService.findAll()).thenReturn(productList);
		try {
			mockMvc.perform(get("/product/all")).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print()).andReturn();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}//end getAllProductsTest
	
	@Test
	public void getProductByNameTest() {
		Mockito.when(this.productService.findBySpecies()).thenReturn(oneProduct);
		try {
			mockMvc.perform(get("/product/name/honeycomb")).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print()).andReturn();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}//end getProductByNameTest
}
