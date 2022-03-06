package by.epam.tr.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import by.epam.tr.beans.Passenger;
import by.epam.tr.dao.PassengerDAO;

@RunWith(MockitoJUnitRunner.class)
public class PassengerServiceImplTest {
  @Mock
  private PassengerDAO passengerDAO;
  @InjectMocks
  private PassengerServiceImpl passengerServiceImpl;

  public PassengerServiceImplTest() {
    MockitoAnnotations.openMocks(this);
    this.passengerServiceImpl = new PassengerServiceImpl();
  }

  @Test
  public void testSelectPaymentType() {
    String passengerName = "CreditCard";
    String passengerSurname = "CreditCard";
    String paymentType = "CreditCard";

    given(passengerDAO.getNearestPassenger())
        .willReturn(new Passenger(passengerName, passengerSurname));

    assertEquals("Please add a credit card", passengerServiceImpl.selectPaymentType(paymentType));
  }

  @Test
  public void testSelectPaymentType2() {
    String passengerName = "CreditCard";
    String passengerSurname = "CreditCard";
    String paymentType = "worngj";

    given(passengerDAO.getNearestPassenger())
        .willReturn(new Passenger(passengerName, passengerSurname));

    assertEquals("You entered incorrect data. Check it and try again",
        passengerServiceImpl.selectPaymentType(paymentType));
  }
}
