package by.epam.tr.beans;

import java.io.Serializable;

/**
 * Класс, описывающий тарифы такси
 */
public enum Rate implements Serializable {
  MINIMAL(1.1, 0.25, 0.1), MIDDLE(1.5, 0.3, 0.15), MIDDLE_PLUS(3, 0.4, 0.2), LUX(5, 0.5, 0.3);
	
	private double waitingRate;
	private double tripRate;
	private double tripTimeRate;
	
    private Rate(double tripRate, double waitingRate, double tripTimeRate) {
      this.setTripRate(tripRate);
      this.setTripTimeRate(tripTimeRate);
      this.setWaitingRate(waitingRate);
    }

	public double getWaitingRate() {
		return waitingRate;
	}

	public void setWaitingRate(double waitingRate) {
		this.waitingRate = waitingRate;
	}

	public double getTripRate() {
		return tripRate;
	}

	public void setTripRate(double tripRate) {
		this.tripRate = tripRate;
	}

	public double getTripTimeRate() {
		return tripTimeRate;
	}

	public void setTripTimeRate(double tripTimeRate) {
		this.tripTimeRate = tripTimeRate;
	}
}
