package uk.co.aquamatix;

import java.util.Date;

import org.joda.time.DateTime;

public class NWaveData {

	private String signal, station_id, value;
	private DateTime time;

	public NWaveData(String signal, String station_id, double time, String value) {
		super();
		this.signal = signal;
		this.station_id = station_id;
		this.time = new DateTime(new Date((long) (time * 1000)));
		this.value = value;
	}

	@Override
	public String toString() {
		return "<tr><td>" + signal + "</td><td>" + station_id + "</td><td>"
				+ time.toString() + "</td><td>" + value + "</td></tr>";
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

	public DateTime getTime() {
		return time;
	}

}
