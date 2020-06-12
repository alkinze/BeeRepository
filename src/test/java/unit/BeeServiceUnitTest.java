package unit;

import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BeeServiceUnitTest {
	//mock dependencies
	@Mock
	private BeeRepository beeRepository;
	
	@InjectMocks
	private BeeService beeService;
	
	List<Bee> allBees = new ArrayList<Bee>();
	List<Bee> hotBees = new ArrayList<Bee>();
	Bee oneBee = new Bee();
	
	@BeforeSuite
	public void setup() {
		MockitoAnnotations.initMocks(this);
		beeService = new BeeService();
	}//end setup
	
	@Test
	public void getAllTest() {
		Mockito.when(beeRepository.findAll()).thenReturn(allBees);
		
		//verify data is returned
		Assertions.assertNotNull(allBees[0]);
	}//end getAllTest
	
	@Test
	public void getByNameTest() {
		Mockito.when(beeRepository.findBySpecies("forest")).thenReturn(oneBee);
		
		//verify bee is the same
		Assertions.assertEquals(oneBee, this.beeService.getByName("forest"));
	}//end getByNameTest
	
	@Test
	public void getByDataTest() {
		Mockito.when(beeRepository.findByData("select * where climate = 'hot'")).thenReturn(hotBees);
		
		//verify climate
		Assertions.assertEquals("HOT", this.beeService.getByData("hot").get(0).getClimate());
	}//end getByDataTest
}
