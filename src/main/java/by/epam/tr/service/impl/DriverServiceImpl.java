package by.epam.tr.service.impl;

import java.util.Iterator;
import java.util.Set;
import by.epam.tr.beans.Car;
import by.epam.tr.beans.Driver;
import by.epam.tr.beans.NavigationMap;
import by.epam.tr.beans.Rate;
import by.epam.tr.dao.CarDAO;
import by.epam.tr.dao.DAOException;
import by.epam.tr.dao.DAOProvider;
import by.epam.tr.dao.DriverDAO;
import by.epam.tr.dao.MapDAO;
import by.epam.tr.service.DriverService;
import by.epam.tr.utils.GenerateWord;

/**
 * Implementation class of the Service layer interface * @see DriverService
 */
public class DriverServiceImpl implements DriverService {
  /**
   * Initialization of DAO layer objects using DAOProvider
   * 
   * @see DriverServiceImpl#nameRegexp Regular expression for checking names
   * @see DriverServiceImpl#averageDistrictTaxiDrivers Average number of drivers in the area of *
   *      passenger location
   */
  private static final String nameRegexp = "[a-zA-Z]+";
  private static final int averageDistrictTaxiDrivers = 10;
  private final DAOProvider provider = DAOProvider.getDaoProvider();
  private DriverDAO driverDAO = provider.getDriverDAO();
  private CarDAO carDAO = provider.getCarDAO();
  private MapDAO mapDAO = provider.getMapDAO();
  private GenerateWord newWord = new GenerateWord();

  /**
   * Checking the correctness of the driver's data entry
   *
   * @param name Driver's name
   * @param surname Last name of the driver
   * @param legalEntityName Name of the legal entity
   * @return Returns the boolean value of the match
   */
  public boolean validate(String name, String surname, String legalEntityName) {
    return name.matches(nameRegexp) && legalEntityName.matches(nameRegexp)
        && surname.matches(nameRegexp);
  }

  /**
   * Registers a new driver
   *
   * @param name Driver's name
   * @param surname Last name of the driver
   * @param legalEntityName Name of the legal entity
   * @return Returns the response in the form of boolean - the completion of registration
   */
  public boolean register(String name, String surname, String legalEntityName) throws DAOException {
    if (validate(name, surname, legalEntityName)) {
      Driver driver = new Driver(name, surname, legalEntityName);
      NavigationMap driverMap = driver.getMap();
      String driverLocation = null;
      driverLocation = mapDAO.getRandomLocation();
      driverMap.setDriverLocation(driverLocation);
      driverDAO.addDriver(driver);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Car Selection by the Driver
   *
   * @param CarModel Car Model
   * @param carNumber Car Number
   * @return Returns the response in the form of a boolean selection offset
   */
  public boolean selectCar(String carModel, String carNumber) {
    Driver driver = driverDAO.getNearestDriver();
    if (carDAO.checkCar(carModel)) {
      Rate rate = carDAO.getCarRate(carModel);
      Car car = new Car(carModel, carNumber, rate);
      driver.setCar(car);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Generates multiple drivers next to the passenger
   * 
   * @return Returns the response as a boolean offset
   */
  public boolean generateDrivers() throws DAOException {
    driverDAO.clearListOfDrivers();
    for (int i = 0; i < averageDistrictTaxiDrivers; i++) {
      String driverName = newWord.generateRandomWord();
      String driverSurname = newWord.generateRandomWord();
      String legalEntityName = newWord.generateRandomWord();

      register(driverName, driverSurname, legalEntityName);

      String driverCarModel = carDAO.getRandomCarModel();
      String driverCarNumber = newWord.generateRandomWord();
      Rate driverCarRate = carDAO.getCarRate(driverCarModel);

      Set<Driver> drivers = driverDAO.getDriversOnTheLine();
      for (Driver driver : drivers) {
        String currentDriverName = driver.getName();
        String currentDriverSurname = driver.getSurname();
        if (currentDriverName.equalsIgnoreCase(driverName)
            && currentDriverSurname.equalsIgnoreCase(driverSurname)) {
          driver.setCar(new Car(driverCarModel, driverCarNumber, driverCarRate));
        }
      }
      driverDAO.updateDrivers(drivers);
    }

    return true;
  }

  /**
   * Selects the first driver in the data source list next to the passenger
   */
  public Driver chooseDriver() {
    return driverDAO.getNearestDriver();
  }

  /**
   * Deletes all drivers in the list that do not match the fare selected by the passenger and
   * records relevant drivers are listed in the data source
   *
   * @param rate The fare of the trip selected by the passenger
   */
  public boolean choiseDriverByRate(Rate rate) {
    Set<Driver> drivers = driverDAO.getDriversOnTheLine();
    Iterator<Driver> iterator = drivers.iterator();
    while (iterator.hasNext()) {
      Car currentCar = iterator.next().getCar();
      Rate currentCarRate = currentCar.getRate();
      if (currentCarRate.getTripRate() != rate.getTripRate()) {
        iterator.remove();
      }
    }
    if (drivers.size() > 0) {
      driverDAO.clearListOfDrivers();
      driverDAO.updateDrivers(drivers);
      return true;
    } else {
      return false;
    }
  }
}
