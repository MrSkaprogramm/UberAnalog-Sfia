package by.epam.tr.dao.impl;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import by.epam.tr.dao.MapDAO;

public class DriverServiceImplTest {
  @Mock
  private MapDAO mapDAO;

  @InjectMocks
  private DriverServiceImpl driverServiceImpl;



  // @SuppressWarnings("deprecation")
  public DriverServiceImplTest() {
    MockitoAnnotations.openMocks(this);
    this.driverServiceImpl = new DriverServiceImpl();
  }



  @Test
  public void testRegister() {
    String name = "wjrngj";
    String surname = "woinrg";
    String legalEntityName = "oirnh";

    given(mapDAO.getRandomLocation()).willReturn("Mazurova");
    // assertTrue(driverServiceImpl.register(name, surname, legalEntityName));
    boolean driverRegister = driverServiceImpl.register(name, surname, legalEntityName);

    assertTrue(driverRegister);
    // fail("Not yet implemented");
  }
}
