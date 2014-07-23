package uk.co.aquamatix;

import java.sql.Date;

import java.util.concurrent.atomic.AtomicLong;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NWaveController {


    private final AtomicLong counter = new AtomicLong();
    private static Logger LOGGER;
    
   static {
    	LOGGER = LoggerFactory.getLogger(NWaveController.class);
    }
	
	
    //http://yourdomain.com/inc?id={device_id}&time={message_time}&signal={signal}&station={station_id}&data={data} 
    @RequestMapping(value = {"/inc","/"})
    public NWaveResponse processVersion1(@RequestParam(value="id", required=false, defaultValue="No ID") String device_id,
                             @RequestParam(value="time", required=false, defaultValue="No Time") String message_time,
                             @RequestParam(value="signal", required=false, defaultValue="No siginal") String signal,
                             @RequestParam(value="station", required=false, defaultValue="No station") String station_id,
                             @RequestParam(value="data", required=false, defaultValue="") String data){
		LOGGER.info(device_id + " " + message_time + " " + signal + " " + station_id + " " + data );
		return new NWaveResponse(counter.incrementAndGet(),device_id, message_time, signal, station_id , data );
    }
    	
	@RequestMapping("/status")
    public NWaveStatusResponse status() {
		return new NWaveStatusResponse();
    }
	
	
	
	
}
