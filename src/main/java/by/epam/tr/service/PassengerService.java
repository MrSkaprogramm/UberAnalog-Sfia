package by.epam.tr.service;

import by.epam.tr.beans.Passenger;

/**
 * Интерфейс слоя Service для bean
 * @see Passenger
 */
public interface PassengerService {
  /**
   * Проверяет на соответствие имя и фамилию пассажира
   */
  public boolean validate(String name, String surname);

  /**
   * Регистрирует нового пассажира
   */
  public boolean register(String name, String surname);

  /**
   * Генерирует несколько пассажиров рядом с водителем
   */
    public boolean generatePassengers();

  /**
   * Установка вида платёжного средства пассажиром
   */
    public String selectPaymentType(String type);

  /**
   * Выбирает первого в списке пассажира рядом с водителем
   */
    public Passenger choosePassenger();
}