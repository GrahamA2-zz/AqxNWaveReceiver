package uk.co.aquamatix;

import java.util.HashMap;
import java.util.Map;
import java.util.Collections;


public class NWaveModem {

	
	private String device_id, signal, station_id;

	private Map<Long, Double> data = new HashMap<Long, Double>();
	
	
	public NWaveModem(String device_id, String signal, String station_id) {
		super();
		this.device_id = device_id;
		this.signal = signal;
		this.station_id = station_id;
	}
	
    public void addData(String time, String value) {
    	data.put(Long.parseLong(time),Double.parseDouble(value));
    }
    
    public Map<Long, Double> getData(){
    	return Collections.unmodifiableMap(data);
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return "<tr><td>" + device_id + "</td><td>" + signal + "</td><td>" + station_id  + "</td><td>" + data.size() + "</td></tr>" ;
    }

}
