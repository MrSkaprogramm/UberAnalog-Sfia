package by.epam.tr.beans;

import java.io.Serializable;

/**
 * A class describing a credit card
 */
public class CreditCard implements Serializable {
  private static final long serialVersionUID = -4111853358023702484L;
  private int creditCardNum;
  private String expiringDate;

  public CreditCard(int creditCardNum, String expiringDate) {
    this.creditCardNum = creditCardNum;
    this.expiringDate = expiringDate;
  }

  public int getCreditCardNum() {
    return creditCardNum;
  }

  public void setCreditCardNum(int creditCardNum) {
    this.creditCardNum = creditCardNum;
  }

  public String getExpiringDate() {
    return expiringDate;
  }

  public void setExpiringDate(String expiringDate) {
    this.expiringDate = expiringDate;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + creditCardNum;
    result = prime * result + ((expiringDate == null) ? 0 : expiringDate.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CreditCard other = (CreditCard) obj;
    if (creditCardNum != other.creditCardNum)
      return false;
    if (expiringDate == null) {
      if (other.expiringDate != null)
        return false;
    } else if (!expiringDate.equals(other.expiringDate))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "CreditCard [creditCardNum=" + creditCardNum + ", expiringDate=" + expiringDate + "]";
  }
}
