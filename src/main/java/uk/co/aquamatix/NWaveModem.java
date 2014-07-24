package uk.co.aquamatix;

import java.util.HashMap;
import java.util.Map;
import java.util.Collections;


public class NWaveModem {

	
	private String device_id;

	private Map<Double, NWaveData> data = new HashMap<Double, NWaveData>();
	
	
	public NWaveModem(String device_id ) {
		this.device_id = device_id;

	}
	
    public void addData(String signal, String station_id,String time, String value) {
    	double timeVal = Double.parseDouble(time);
    	data.put(timeVal, new NWaveData(signal, station_id, timeVal , value));
    }
    
    public Map<Double, NWaveData> getData(){
    	return Collections.unmodifiableMap(data);
    }
    
    @Override
    public String toString() {
    	return "<tr><td><a href=/data?id="+device_id +">" + device_id + "</a></td><td>" + data.size() + "</td></tr>" ;
    }

}
