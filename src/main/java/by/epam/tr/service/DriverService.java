package by.epam.tr.service;
import by.epam.tr.beans.Driver;
import by.epam.tr.beans.Rate;

/**
 * Интерфейс слоя Service для bean
 * @see Driver
 */
public interface DriverService {
  /**
   * Возвращает boolean - значение правильности ввода данных водителя
   */
  public boolean validate(String name, String surname, String legalEntityName);

  /**
   * Возвращает ответ в виде boolean - зачения о регистрации нового водителя
   */
  public boolean register(String name, String surname, String legalEntityName);

  /**
   * Возвращает ответ в виде boolean - зачения о выборе автомобиля водителем
   */
  public boolean selectCar(String carModel, String carNumber);

  /**
   * Возвращает ответ в виде boolean - зачения
   */
  public boolean generateDrivers();

  /**
   * Выбирает первого в списке водителя рядом с пассажиром
   */
  public Driver chooseDriver();

  /**
   * Удаляет всех водителей в списке, не соответствующих выбранному пассажиром тарифу и записывает
   * соответствующих водителей в список в базе данных
   */
  public boolean choiseDriverByRate(Rate rate);
}
