package uk.co.aquamatix;

public class NWaveData {

	private String signal, station_id, value;
	private double time;

	public NWaveData(String signal, String station_id, double time, String value) {
		super();
		this.signal = signal;
		this.station_id = station_id;
		this.time = time;
		this.value = value;
	}

	@Override
	public String toString() {
		return "<tr><td>" + signal + "</td><td>" + station_id + "</td><td>"
				+ time + "</td><td>" + value + "</td></tr>";
	}

	public String getSignal() {
		return signal;
	}

	public String getStationID() {
		return station_id;
	}

	public String getValue() {
		return value;
	}

	public double getTime() {
		return time;
	}

}
