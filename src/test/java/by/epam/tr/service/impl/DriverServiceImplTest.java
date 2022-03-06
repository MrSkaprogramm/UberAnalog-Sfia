package by.epam.tr.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import by.epam.tr.dao.DAOException;
import by.epam.tr.dao.MapDAO;

@RunWith(MockitoJUnitRunner.class)
public class DriverServiceImplTest {
  private Logger logger = LogManager.getLogger(DriverServiceImplTest.class);
  @Mock
  private MapDAO mapDAO;

  @InjectMocks
  private DriverServiceImpl driverServiceImpl;

  public DriverServiceImplTest() {
    MockitoAnnotations.openMocks(this);
    this.driverServiceImpl = new DriverServiceImpl();
  }

  @Test
  public void testRegister() {
    String name = "wjrngj";
    String surname = "woinrg";
    String legalEntityName = "oirnh";

    boolean driverRegister = false;
    try {
      given(mapDAO.getRandomLocation()).willReturn("Mazurova");
      driverRegister = driverServiceImpl.register(name, surname, legalEntityName);
    } catch (DAOException e) {
      logger.error("Test failed with exception");
      return;
    }

    assertTrue(driverRegister);
  }

  @Test
  public void testRegister2() {
    String name = "wjrngj";
    String surname = "woinrg";
    String legalEntityName = "OOO ЭйрТакси";

    boolean driverRegister = false;
    try {
      driverRegister = driverServiceImpl.register(name, surname, legalEntityName);
    } catch (DAOException e) {
      logger.error("Test failed with exception");
      return;
    }

    assertEquals(false, driverRegister);
  }
}
