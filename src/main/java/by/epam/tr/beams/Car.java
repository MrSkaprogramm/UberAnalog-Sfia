package by.epam.tr.beans;
import java.io.Serializable;

/**
 * Класс, осписывающий автомобиль
 */
public class Car implements Serializable{
  private static final long serialVersionUID = 9077308550998853813L;
  private String carModel;
	private String carNumber;
    private Rate rate;
	
    public Car(String carModel, String carNumber, Rate carRate) {
		this.carModel = carModel;
		this.carNumber = carNumber;
        this.rate = carRate;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

    public Rate getRate() {
      return rate;
    }

    public void setRate(Rate rate) {
      this.rate = rate;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carModel == null) ? 0 : carModel.hashCode());
		result = prime * result + ((carNumber == null) ? 0 : carNumber.hashCode());
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
		Car other = (Car) obj;
		if (carModel == null) {
			if (other.carModel != null)
				return false;
		} else if (!carModel.equals(other.carModel))
			return false;
		if (carNumber == null) {
			if (other.carNumber != null)
				return false;
		} else if (!carNumber.equals(other.carNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Car [carModel=" + carModel + ", carNumber=" + carNumber + "]";
	}
}
