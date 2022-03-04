import java.io.Serializable;
import java.util.Objects;

/**
 * Класс, описывающий навигационную карту в приложении
 */
public class NavigationMap implements Serializable{
  private static final long serialVersionUID = -8145835483018798695L;
  private String deliveryAddress;
  private String driverLocation;
  private String passengerLocation;
  
  public NavigationMap() {}

  public String getDeliveryAddress() {
    return deliveryAddress;
  }

  public void setDeliveryAddress(String deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  public String getDriverLocation() {
    return driverLocation;
  }

  public void setDriverLocation(String driverLocation) {
    this.driverLocation = driverLocation;
  }

  public String getPassengerLocation() {
    return passengerLocation;
  }

  public void setPassengerLocation(String passengerLocation) {
    this.passengerLocation = passengerLocation;
  }

  @Override
  public int hashCode() {
    return Objects.hash(deliveryAddress, driverLocation, passengerLocation);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    NavigationMap other = (NavigationMap) obj;
    return Objects.equals(deliveryAddress, other.deliveryAddress)
        && Objects.equals(driverLocation, other.driverLocation)
        && Objects.equals(passengerLocation, other.passengerLocation);
  }

  @Override
  public String toString() {
    return "Map [deliveryAddress=" + deliveryAddress + ", driverLocation=" + driverLocation
        + ", passengerLocation=" + passengerLocation + "]";
  }
}
