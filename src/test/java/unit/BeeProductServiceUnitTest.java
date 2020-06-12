package unit;

import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BeeProductServiceUnitTest {
	@Mock
	private BeeProductRepository beeProductRepository;
	
	@InjectMocks
	private BeeProductService beeProductService;
	
	List<BeeProduct> allBeeProducts = new ArrayList<BeeProduct>();
	List<BeeProduct> specificBeeProducts = new ArrayList<BeeProduct>();
	BeeProduct oneBeesProduct = new BeeProduct();
	
	@BeforeSuite
	public void setup() {
		MockitoAnnotations.initMocks(this);
		beeProductService = new BeeProductService();
	}//end setup
	
	@Test
	public void getByBeeTest() {
		Mockito.when(beeProductRepository.findByBeeId(1)).thenReturn(oneBeeProduct);
		
		//verify bee_product is the same
		Assertions.assertEquals(oneBeeProduct, this.beeProductService.getByBee(1));
	}//end getByBeeTest
	
	@Test
	public void getByProductTest() {
		Mockito.when(beeProductRepository.findByProductId(21)).thenReturn(oneBeeProduct);
		
		//verify climate
		Assertions.assertEquals("honeycomb", this.beeProductService.getByProduct(21));
	}//end getByDataTest
}
