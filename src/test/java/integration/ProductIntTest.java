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
public class ProductIntTest {
	@Autowired
	private ProductService productService;
	
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
	public void getAllProductsTest() {
		List<Product> allProducts = new ArrayList<>();
		
		try {
			MvcResult result = mockmvc.perform(get("/product/all"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print()).andReturn();
			
			//getList
			ObjectMapper om = new ObjectMapper();
			allProducts = om.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Product>>() {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Assertions.assertNotNull(allProducts[0]);
	}//end getAllProductsTest
	
	@Test
	public void getProductByNameTest() {
		Product aProduct = new Product();
		
		try {
			MvcResult result = mockmvc.perform(get("/product/name/honeycomb"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print()).andReturn();
			
			//getList
			ObjectMapper om = new ObjectMapper();
			aProduct = om.readValue(result.getResponse().getContentAsString(), new TypeReference<Product>() {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Assertions.assertEquals(21, aProduct.getId());
	}//end getProductByNameTest
}
