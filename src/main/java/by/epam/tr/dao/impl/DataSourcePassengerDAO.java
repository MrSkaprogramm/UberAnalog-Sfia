package by.epam.tr.dao.impl;

import java.util.Set;
import by.epam.tr.beans.Passenger;
import by.epam.tr.dao.PassengerDAO;
import by.epam.tr.datasource.DataSource;

/**
 * Класс реализации интерфейса слоя DAO
 * @see PassengerDAO
 */
public class DataSourcePassengerDAO implements PassengerDAO {
  private DataSource database = new DataSource();
  private Set<Passenger> passengers = database.getPassengers();

  /**
   * Добавляет пассажира в список пассажиров {@link DataSource#getPassengers()}
   * @param newPassenger объект класса Passenger
   */
  public void addPassenger(Passenger newPassenger) {
    passengers.add(newPassenger);
	}

    /**
     * @return возвращает первого пассажира в списке {@link DataSource#getPassengers()}
     */
    public Passenger getNearestPassenger() {
      return passengers.iterator().next();
    }

    /**
     * Очищает список пассажиров {@link DataSource#getPassengers()}
     */
    public boolean clearListOfPassengers() {
      passengers.clear();
      return true;
    }
}