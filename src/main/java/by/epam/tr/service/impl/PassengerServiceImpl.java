package by.epam.tr.service.impl;

import by.epam.tr.beans.NavigationMap;
import by.epam.tr.beans.Passenger;
import by.epam.tr.beans.Payment;
import by.epam.tr.dao.DAOProvider;
import by.epam.tr.dao.PassengerDAO;
import by.epam.tr.service.MapService;
import by.epam.tr.service.PassengerService;
import by.epam.tr.service.ServiceProvider;
import by.epam.tr.utils.GenerateWord;

/**
 * Класс реализации интерфейса слоя Service
 * @see PassengerService
 */
public class PassengerServiceImpl implements PassengerService {
  /**
   * Инициализация объектов слоя DAO с помощью DAOProvider
   * @see PassengerServiceImpl#nameRegexp Регулярное выражение для проверки наименований
   */
  private static final String nameRegexp = "[a-zA-Z]+";
  private final DAOProvider provider = DAOProvider.getDaoProvider();
	private PassengerDAO passengerDAO = provider.getPassengerDAO();
    private GenerateWord newWord = new GenerateWord();

    /**
     * Проверяет на соответствие имя и фамилию пассажира
     * @param name Имя пассажира
     * @param Фамилия пассажира
     */
    public boolean validate(String name, String surname) {
      return name.matches(nameRegexp) && surname.matches(nameRegexp);
    }

    // 2 для теста
    /**
     * Установка вида платёжного средства пассажиром
     * @param type строка с видом платёжного средства
     * @return Возвращает соответствующий ответ в виде строки
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
     * Генерирует несколько пассажиров рядом с водителем
     * @return Возвращает ответ в виде boolean - зачения
     */
    public boolean generatePassengers() {
      passengerDAO.clearListOfPassengers();
      for (int i = 0; i < 5; i++) {
        String passengerName = newWord.generateRandomWord();
        String passengerSurname = newWord.generateRandomWord();
        register(passengerName, passengerSurname);
      }
      return true;
    }

    /**
     * Регистрирует нового пассажира
     * @param name Имя пассажира
     * @param surname Фамилия пассажира
     * @return Возвращает ответ в виде boolean - зачения о регистрации нового пассажира
     */
    public boolean register(String name, String surname) {
      ServiceProvider instance = new ServiceProvider();
      MapService mapService = instance.getMapService();
      if (validate(name, surname)) {
        Passenger passenger = new Passenger(name, surname);
        NavigationMap passengerMap = passenger.getMap();
        String passengerLocation = mapService.defineLocation();
        passengerMap.setPassengerLocation(passengerLocation);
        passengerDAO.addPassenger(passenger);
        return true;
      } else {
        return false;
      }
    }

    /**
     * Выбирает первого в списке пассажира рядом с водителем
     */
    public Passenger choosePassenger() {
      return passengerDAO.getNearestPassenger();
    }
}