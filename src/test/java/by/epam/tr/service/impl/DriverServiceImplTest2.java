package by.epam.tr.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import by.epam.tr.beans.Driver;
import by.epam.tr.beans.Rate;
import by.epam.tr.dao.CarDAO;
import by.epam.tr.dao.DriverDAO;

@RunWith(MockitoJUnitRunner.class)
public class DriverServiceImplTest2 {
  @Mock
  private DriverDAO driverDAO;
  @Mock
  private CarDAO carDAO;
  @InjectMocks
  private DriverServiceImpl driverServiceImpl;

  public DriverServiceImplTest2() {
    MockitoAnnotations.openMocks(this);
    this.driverServiceImpl = new DriverServiceImpl();
  }

  @Test
  public void testSelectCar() {
    String driverName = "pojkm";
    String driverSurname = "dogjn";
    String legalEntityName = "dofgnj";
    String carModel = "VW PASSAT";
    String carNumber = "ejwfj3145";

    given(driverDAO.getNearestDriver())
        .willReturn(new Driver(driverName, driverSurname, legalEntityName));
    given(carDAO.checkCar(carModel)).willReturn(true);
    given(carDAO.getCarRate(carModel)).willReturn(Rate.MIDDLE);

    assertTrue(driverServiceImpl.selectCar(carModel, carNumber));
  }

  @Test
  public void testSelectCar2() {
    String driverName = "pojkm";
    String driverSurname = "dogjn";
    String legalEntityName = "dofgnj";
    String carModel = "FIAT 500";
    String carNumber = "ejwfj3145";

    given(driverDAO.getNearestDriver())
        .willReturn(new Driver(driverName, driverSurname, legalEntityName));

    assertEquals(false,
        driverServiceImpl.selectCar(carModel, carNumber));
  }
}
