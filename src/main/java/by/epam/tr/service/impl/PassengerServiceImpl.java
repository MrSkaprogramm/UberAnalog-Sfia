package by.epam.tr.service.impl;

import by.epam.tr.beans.NavigationMap;
import by.epam.tr.beans.Passenger;
import by.epam.tr.beans.Payment;
import by.epam.tr.dao.DAOException;
import by.epam.tr.dao.DAOProvider;
import by.epam.tr.dao.PassengerDAO;
import by.epam.tr.service.MapService;
import by.epam.tr.service.PassengerService;
import by.epam.tr.service.ServiceProvider;
import by.epam.tr.utils.GenerateWord;

/**
 * Implementation class of the Service layer interface * @see PassengerService
 */
public class PassengerServiceImpl implements PassengerService {
  /**
   * Initializing DAO layer objects using DAOProvider
   * 
   * @see PassengerServiceImpl#nameRegexp Regular expression for checking names
   */
  private static final String nameRegexp = "[a-zA-Z]+";
  private final DAOProvider provider = DAOProvider.getDaoProvider();
  private PassengerDAO passengerDAO = provider.getPassengerDAO();
  private GenerateWord newWord = new GenerateWord();

  /**
   * Checks for compliance with the passenger's first and last name
   * 
   * @param name Passenger name
   * @param Last name of the passenger
   */
  public boolean validate(String name, String surname) {
    return name.matches(nameRegexp) && surname.matches(nameRegexp);
  }

  /**
   * Setting the type of payment means by the passenger
   * 
   * @param type line with the type of payment instrument
   * @return Returns the corresponding response as a string
   */
  public String selectPaymentType(String type) {
    Passenger passenger = passengerDAO.getNearestPassenger();
    Payment passangerPayment = passenger.getPayment();
    if (type.equalsIgnoreCase("CreditCard") && passangerPayment.getCreditCard() == null) {
      return "Please add a credit card";
    } else if (type.equalsIgnoreCase("CreditCard")) {
      passenger.setPaymentType(type);
      return "You have chosen the credit card payment method";
    } else if (type.equalsIgnoreCase("Cash")) {
      passenger.setPaymentType(type);
      return "You have chosen the cash payment method";
    } else {
      return "You entered incorrect data. Check it and try again";
    }
  }

  /**
   * Generates multiple passengers next to the driver
   * 
   * @return Returns the response as a boolean offset
   * @throws DAOException
   */
  public boolean generatePassengers() throws DAOException {
    passengerDAO.clearListOfPassengers();
    for (int i = 0; i < 5; i++) {
      String passengerName = newWord.generateRandomWord();
      String passengerSurname = newWord.generateRandomWord();
      if (!register(passengerName, passengerSurname)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Registers a new passenger
   *
   * @param name Passenger name
   * @param surname Last name of the passenger
   * @return Returns a response in the form of a boolean notification about the registration of a
   *         new passenger
   * @throws DAOException
   */
  public boolean register(String name, String surname) throws DAOException {
    ServiceProvider instance = new ServiceProvider();
    MapService mapService = instance.getMapService();
    if (validate(name, surname)) {
      Passenger passenger = new Passenger(name, surname);
      NavigationMap passengerMap = passenger.getMap();
      String passengerLocation = null;
      passengerLocation = mapService.defineLocation();
      passengerMap.setPassengerLocation(passengerLocation);
      passengerDAO.addPassenger(passenger);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Selects the first passenger in the list next to the driver
   */
  public Passenger choosePassenger() {
    return passengerDAO.getNearestPassenger();
  }
}
