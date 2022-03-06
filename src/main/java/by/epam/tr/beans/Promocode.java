package by.epam.tr.beans;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class describing the promo codes for the trip
 */
public class Promocode implements Serializable {
  private static final long serialVersionUID = 3681718065019909653L;
  private String promocodeNum;
  private double discount;

  public Promocode(String promocodeNum, double discount) {
    this.promocodeNum = promocodeNum;
    this.discount = discount;
  }

  public String getPromocodeNum() {
    return promocodeNum;
  }

  public void setPromocodeNum(String promocodeNum) {
    this.promocodeNum = promocodeNum;
  }

  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }

  @Override
  public int hashCode() {
    return Objects.hash(discount, promocodeNum);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Promocode other = (Promocode) obj;
    return Double.doubleToLongBits(discount) == Double.doubleToLongBits(other.discount)
        && Objects.equals(promocodeNum, other.promocodeNum);
  }

  @Override
  public String toString() {
    return "Promocode [promocodeNum=" + promocodeNum + ", discount=" + discount + "]";
  }
}
