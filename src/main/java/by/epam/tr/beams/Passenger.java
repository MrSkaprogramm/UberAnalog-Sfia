package by.epam.tr.beans;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс, описывающий пассажира
 */
public class Passenger extends User implements Serializable, Comparable<Passenger> {
  private static final long serialVersionUID = -8345915539780328557L;
  private String paymentType;
  private Payment payment;

  public Passenger(String name, String surname) {
    super(name, surname);
    this.payment = new Payment();
    this.paymentType = "Cash";
  }

  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + Objects.hash(payment, paymentType);
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
    Passenger other = (Passenger) obj;
    return Objects.equals(payment, other.payment) && Objects.equals(paymentType, other.paymentType);
  }

  @Override
  public String toString() {
    return "Passenger [paymentType=" + paymentType + ", payment=" + payment + "]";
  }

  public int compareTo(Passenger o) {
    return getMap().getPassengerLocation().compareTo(o.getMap().getPassengerLocation());
  }
}
