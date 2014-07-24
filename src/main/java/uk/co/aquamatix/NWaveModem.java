package uk.co.aquamatix;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class NWaveModem {

	private String deviceID;

	public String getDeviceID() {
		return deviceID;
	}

	private Map<Double, NWaveData> data = new HashMap<Double, NWaveData>();

	public NWaveModem(String device_id) {
		this.deviceID = device_id;

	}

	public int getMessageCount() {
		return data.size();
	}

	public void addData(String signal, String station_id, String time,
			String value) {
		double timeVal = Double.parseDouble(time);
		data.put(timeVal, new NWaveData(signal, station_id, timeVal, value));
	}

	public Map<Double, NWaveData> getData() {
		return Collections.unmodifiableMap(data);
	}

	@Override
	public String toString() {
		return "<tr><td><a href=/data?id=" + deviceID + ">" + deviceID
				+ "</a></td><td>" + data.size() + "</td></tr>";
	}

}
