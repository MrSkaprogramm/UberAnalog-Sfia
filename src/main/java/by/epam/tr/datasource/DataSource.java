import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import by.epam.tr.beans.Driver;
import by.epam.tr.beans.Order;
import by.epam.tr.beans.Passenger;
import by.epam.tr.beans.Rate;
import by.epam.tr.utils.DriversSortByStreet;

/**
 * Класс реализации базы данных коллекцией
 */
public class DataSource {
  /**
   * @see DataSource#streetsLocationsFromCentre Улицы города и дистанция от центра
   * @see DataSource#suitableCarsTaxis Машины, допустимые для поездок в такси
   * @see DataSource#drivers Список водителей
   * @see DataSource#passengers Список пассажиров
   * @see DataSource#orders Список заказов
   */
  private Map<String, Double> streetsLocationsFromCentre = new HashMap<String, Double>();
  private Map<String, Rate> suitableCarsTaxis = new HashMap<String, Rate>();
  private Set<Driver> drivers = new TreeSet<Driver>(new DriversSortByStreet());
  private Set<Passenger> passengers = new TreeSet<Passenger>();
  private List<Order> orders = new ArrayList<Order>();
	
	public DataSource() {}

    public Map<String, Double> getStreetsLocationsFromCentre() {
      return streetsLocationsFromCentre;
  }

    public void setStreetsLocationsFromCentre(Map<String, Double> streetsLocationsFromCentre) {
      this.streetsLocationsFromCentre = streetsLocationsFromCentre;
  }

    public Map<String, Rate> getSuitableCarsTaxis() {
      return suitableCarsTaxis;
    }

    public void setSuitableCarsTaxis(Map<String, Rate> suitableCarsTaxis) {
      this.suitableCarsTaxis = suitableCarsTaxis;
    }

    public Set<Driver> getDrivers() {
      return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
      this.drivers = drivers;
    }

    public Set<Passenger> getPassengers() {
      return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
      this.passengers = passengers;
    }

    public List<Order> getOrders() {
      return orders;
    }

    public void setOrders(List<Order> orders) {
      this.orders = orders;
    }
}
