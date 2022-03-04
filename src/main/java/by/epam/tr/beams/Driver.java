import java.io.Serializable;
import java.util.Objects;

/**
 * Класс, описывающий водителя
 */
public class Driver extends User implements Serializable{
  private static final long serialVersionUID = 7372189831846491853L;
  private String legalEntityName;
  private Car car;

  public String getLegalEntityName() {
    return legalEntityName;
  }

  public Driver(String name, String surname, String legalEntityName) {
    super(name, surname);
    this.legalEntityName = legalEntityName;
  }

  public void setLegalEntityName(String legalEntityName) {
    this.legalEntityName = legalEntityName;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + Objects.hash(car, legalEntityName);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    Driver other = (Driver) obj;
    return Objects.equals(car, other.car) && Objects.equals(legalEntityName, other.legalEntityName);
  }

  @Override
  public String toString() {
    return "Driver [legalEntityName=" + legalEntityName + ", car=" + car + "]";
  }
}
