package by.epam.tr.dao;

import by.epam.tr.beans.Passenger;

/**
 * Интерфейс слоя DAO для bean
 * @see Passenger
 */
public interface PassengerDAO {
  /**
   * Добавляет пассажира в список пассажиров
   */
  public void addPassenger(Passenger newPassenger);

  /**
   * Возвращает первого пассажира в списке
   */
    public Passenger getNearestPassenger();

  /**
   * Очищает список пассажиров
   */
    public boolean clearListOfPassengers();
}
