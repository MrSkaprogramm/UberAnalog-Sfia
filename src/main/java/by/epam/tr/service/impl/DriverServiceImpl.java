package by.epam.tr.service.impl;
import java.util.Iterator;
import java.util.Set;
import by.epam.tr.beans.Car;
import by.epam.tr.beans.Driver;
import by.epam.tr.beans.NavigationMap;
import by.epam.tr.beans.Rate;
import by.epam.tr.dao.CarDAO;
import by.epam.tr.dao.DAOProvider;
import by.epam.tr.dao.DriverDAO;
import by.epam.tr.dao.MapDAO;
import by.epam.tr.service.DriverService;
import by.epam.tr.utils.GenerateWord;

/**
 * Класс реализации интерфейса слоя Service
 * @see DriverService
 */
public class DriverServiceImpl implements DriverService {
  /**
   * Инициализация объектов слоя DAO с помощью DAOProvider
   * @see DriverServiceImpl#nameRegexp Регулярное выражение для проверки наименований
   * @see DriverServiceImpl#averageDistrictTaxiDrivers Средне количество водителей в районе
   *      нахождения пассажира
   */
  private static final String nameRegexp = "[a-zA-Z]+";
  private static final int averageDistrictTaxiDrivers = 10;
  private final DAOProvider provider = DAOProvider.getDaoProvider();
  private DriverDAO driverDAO = provider.getDriverDAO();
  private CarDAO carDAO = provider.getCarDAO();
  private MapDAO mapDAO = provider.getMapDAO();
  private GenerateWord newWord = new GenerateWord();

    /**
     * @param name Имя водителя
     * @param surname Фамилия водителя
     * @param legalEntityName Название юридического лица
     * @return Возвращает boolean - значение правильности ввода данных водителя
     */
    public boolean validate(String name, String surname, String legalEntityName) {
      return name.matches(nameRegexp) && legalEntityName.matches(nameRegexp)
          && surname.matches(nameRegexp);
    }

    /**
     * @param name Имя водителя
     * @param surname Фамилия водителя
     * @param legalEntityName Название юридического лица
     * @return Возвращает ответ в виде boolean - зачения о регистрации нового водителя
     */
    public boolean register(String name, String surname, String legalEntityName) {
      if (validate(name, surname, legalEntityName)) {
        Driver driver = new Driver(name, surname, legalEntityName);
        NavigationMap driverMap = driver.getMap();
        String driverLocation = mapDAO.getRandomLocation();
        driverMap.setDriverLocation(driverLocation);
        driverDAO.addDriver(driver);
        return true;
      } else {
        return false;
      }
    }

    /**
     * @param carModel Модель автомобиля
     * @param carNumber Номер автомобиля
     * @return Возвращает ответ в виде boolean - зачения о выборе автомобиля водителем
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
     * Генерирует несколько водителей рядом с пассажиром
     * @return Возвращает ответ в виде boolean - зачения
     */
    public boolean generateDrivers() {
      driverDAO.clearListOfDrivers();
      for (int i = 0; i < averageDistrictTaxiDrivers; i++) {
        String driverName = newWord.generateRandomWord();
        String driverSurname = newWord.generateRandomWord();
        String legalEntityName = newWord.generateRandomWord();

        register(driverName, driverSurname, legalEntityName);

        String driverCarModel = carDAO.getRandomCarModel();
        String driverCarNumber = newWord.generateRandomWord();
        Rate driverCarRate = carDAO.getCarRate(driverCarModel);

        for (Driver driver : driverDAO.getDrivers()) {
          String currentDriverName = driver.getName();
          String currentDriverSurname = driver.getSurname();
          if (currentDriverName.equalsIgnoreCase(driverName)
              && currentDriverSurname.equalsIgnoreCase(driverSurname)) {
            driver.setCar(new Car(driverCarModel, driverCarNumber, driverCarRate));
          }
        }
      }

      return true;
    }

    /**
     * Выбирает первого в списке водителя рядом с пассажиром
     */
    public Driver chooseDriver() {
      return driverDAO.getNearestDriver();
    }

    /**
     * Удаляет всех водителей в списке, не соответствующих выбранному пассажиром тарифу и записывает
     * соответствующих водителей в список в базе данных
     * @param rate Тариф поездки, выбранный пассажиром
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
        driverDAO.setupDriversByRate(drivers);
        return true;
      } else {
        return false;
      }
    }
}
