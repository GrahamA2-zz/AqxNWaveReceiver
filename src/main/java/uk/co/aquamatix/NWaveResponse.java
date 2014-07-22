package uk.co.aquamatix;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NWaveResponse {

	private final static Logger LOGGER = LoggerFactory.getLogger(NWaveResponse.class);
	private String device_id, message_time, signal, station_id, data;
    private long id;
	
    public NWaveResponse(long id, String device_id, String message_time, String signal, String station_id, String data ) {
         
        this.device_id = device_id;
        this.message_time = message_time;
        this.signal = signal;
        this.station_id = station_id;
        this.data = data;
        
        String illegal = "[^a-zA-Z0-9\\.\\-]";
		String tempmessage_time = message_time.replaceAll(illegal, "-");
		
		try {
			//TODO Append to the file
			PrintWriter out = new PrintWriter("./log/" + device_id + "_" + signal + "_" + station_id + ".csv");
			
			out.println(message_time + "," + data);
			out.flush();
            out.close();
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
        
    }

    public String getContent() {
        return id + " " + device_id + ":" + signal + ":" + station_id + "@" + message_time + " with " + data.length() + " chars of data";
    }
}
