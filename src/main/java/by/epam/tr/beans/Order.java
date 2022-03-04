package by.epam.tr.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс, описывающий заказ
 */
public class Order implements Serializable{
  private static final long serialVersionUID = 59216310138547877L;
  private static int orderNum = 0;
  private final int currentOrderNum;
  private double totalDistance;
	private double totalTime;
    private double totalWaitingTime;
	private Payment payment;
    private Passenger passenger;
    private Driver driver;
    private NavigationMap tripMap;
    private LocalDateTime orderTime;

    public Order(Payment payment, Passenger passenger, Driver driver, NavigationMap tripMap) {
      currentOrderNum = orderNum++;
      this.payment = payment;
      this.passenger = passenger;
      this.driver = driver;
      this.tripMap = tripMap;
    }

    public double getTotalDistance() {
      return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
      this.totalDistance = totalDistance;
    }

    public double getTotalTime() {
      return totalTime;
    }

    public void setTotalTime(double totalTime) {
      this.totalTime = totalTime;
    }

    public double getTotalWaitingTime() {
      return totalWaitingTime;
    }

    public void setTotalWaitingTime(double totalWaitingTime) {
      this.totalWaitingTime = totalWaitingTime;
    }

    public Payment getPayment() {
      return payment;
    }

    public void setPayment(Payment payment) {
      this.payment = payment;
    }

    public Passenger getPassenger() {
      return passenger;
    }

    public void setPassenger(Passenger passenger) {
      this.passenger = passenger;
    }

    public Driver getDriver() {
      return driver;
    }

    public void setDriver(Driver driver) {
      this.driver = driver;
    }

    public NavigationMap getTripMap() {
      return tripMap;
    }

    public void setTripMap(NavigationMap tripMap) {
      this.tripMap = tripMap;
    }

    public LocalDateTime getOrderTime() {
      return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
      this.orderTime = orderTime;
    }

    public int getOrderNum() {
      return orderNum;
    }

    public static long getSerialversionuid() {
      return serialVersionUID;
    }

    public static void setOrderNum(int orderNum) {
      Order.orderNum = orderNum;
    }



    public int getCurrentOrderNum() {
      return currentOrderNum;
    }

    @Override
    public int hashCode() {
      return Objects.hash(driver, orderTime, passenger, payment, totalDistance, totalTime,
          totalWaitingTime, tripMap);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Order other = (Order) obj;
      return Objects.equals(driver, other.driver) && Objects.equals(orderTime, other.orderTime)
          && Objects.equals(passenger, other.passenger) && Objects.equals(payment, other.payment)
          && Double.doubleToLongBits(totalDistance) == Double.doubleToLongBits(other.totalDistance)
          && Double.doubleToLongBits(totalTime) == Double.doubleToLongBits(other.totalTime)
          && Double.doubleToLongBits(totalWaitingTime) == Double
              .doubleToLongBits(other.totalWaitingTime)
          && Objects.equals(tripMap, other.tripMap);
    }

    @Override
    public String toString() {
      return "Order [totalDistance=" + totalDistance + ", totalTime=" + totalTime
          + ", totalWaitingTime=" + totalWaitingTime + ", payment=" + payment + ", passenger="
          + passenger + ", driver=" + driver + ", tripMap=" + tripMap + ", orderTime=" + orderTime
          + "]";
    }
}
