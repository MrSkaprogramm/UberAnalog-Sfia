package by.epam.tr.controller.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import by.epam.tr.beans.Driver;
import by.epam.tr.dao.DAOException;
import by.epam.tr.service.DriverService;
import by.epam.tr.service.MapService;
import by.epam.tr.service.PassengerService;

@RunWith(MockitoJUnitRunner.class)
public class DriverGetOnTheLineCommandTest {
  private Logger logger = LogManager.getLogger(DriverGetOnTheLineCommandTest.class);

  @Mock
  private DriverService driverService;
  @Mock
  private PassengerService passengerService;
  @Mock
  private MapService mapService;
  @InjectMocks
  private DriverGetOnTheLineCommand driverGetOnTheLine;

  public DriverGetOnTheLineCommandTest() {
    MockitoAnnotations.openMocks(this);
    this.driverGetOnTheLine = new DriverGetOnTheLineCommand();
  }

  @Test
  public void testExecute() {
    String[] requestParts = {"driverBeginTrip"};
    String name = "wjrngj";
    String surname = "woinrg";
    String legalEntityName = "oirnh";
    String deliveryAddress = "Kalinina";
    String response = "The nearest passanger is 6.4km away from you";

    try {
      given(passengerService.generatePassengers()).willReturn(true);
      given(driverService.chooseDriver()).willReturn(new Driver(name, surname, legalEntityName));
      given(mapService.defineLocation()).willReturn(deliveryAddress);
      given(mapService.locationDistance(deliveryAddress)).willReturn(6.4);
    } catch (DAOException e) {
      logger.error("Test failed with exception");
      return;
    }

    assertEquals(response, driverGetOnTheLine.execute(requestParts));
  }
}
