package unit;

import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ProductServiceUnitTest {
	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductService productService;
	
	List<Product> allProducts = new ArrayList<Product>();
	Product oneProduct = new Product();
	
	@BeforeSuite
	public void setup() {
		MockitoAnnotations.initMocks(this);
		productService = new ProductService();
	}//end setup
	
	@Test
	public void getAllTest() {
		Mockito.when(productRepository.findAll()).thenReturn(allProducts);
		
		//verify data is returned
		Assertions.assertNotNull(allProducts[0]);
	}//end getAllTest
	
	@Test
	public void getByNameTest() {
		Mockito.when(productRepository.findByName("honeycomb")).thenReturn(oneProduct);
		
		//verify bee is the same
		Assertions.assertEquals(oneProduct, this.productService.getByName("honeycomb"));
	}//end getByNameTest
	
}
