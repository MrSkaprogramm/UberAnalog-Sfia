package by.epam.tr.beans;

import java.io.Serializable;
import java.util.Objects;

/**
 * A class describing the payment system of the application
 */
public class Payment implements Serializable {
  private static final long serialVersionUID = -5728209432672649984L;
  private double paymentSum;
  private CreditCard creditCard;
  private Promocode promocode;

  public Payment() {
    this.creditCard = null;
    this.promocode = null;
  }

  public double getPaymentSum() {
    return paymentSum;
  }

  public void setPaymentSum(double paymentSum) {
    this.paymentSum = paymentSum;
  }

  public CreditCard getCreditCard() {
    return creditCard;
  }

  public void setCreditCard(CreditCard creditCard) {
    this.creditCard = creditCard;
  }

  public Promocode getPromocode() {
    return promocode;
  }

  public void setPromocode(Promocode promocode) {
    this.promocode = promocode;
  }

  @Override
  public int hashCode() {
    return Objects.hash(creditCard, paymentSum, promocode);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Payment other = (Payment) obj;
    return Objects.equals(creditCard, other.creditCard)
        && Double.doubleToLongBits(paymentSum) == Double.doubleToLongBits(other.paymentSum)
        && Objects.equals(promocode, other.promocode);
  }

  @Override
  public String toString() {
    return "Payment [paymentSum=" + paymentSum + ", creditCard=" + creditCard + ", promocode="
        + promocode + "]";
  }
}
