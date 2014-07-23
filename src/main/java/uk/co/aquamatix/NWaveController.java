package uk.co.aquamatix;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NWaveController {



    private final AtomicLong counter = new AtomicLong();
    private static Logger LOGGER;
    private static Calendar calender = Calendar.getInstance();
    
    private static Map<String, NWaveModem> modems = new HashMap<>();
    
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
		
		
		try {
			NWaveModem modem;
			if (modems.containsKey(device_id)) {
				modem = modems.get(device_id);
			} else {
				modem = new NWaveModem(device_id,signal,station_id );
				modems.put(device_id, modem);
			}
			modem.addData(message_time, data);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return new NWaveResponse(counter.incrementAndGet(),device_id, message_time, signal, station_id , data );
    }
    	
	
    @SuppressWarnings("deprecation")
	@RequestMapping(value = "/status" , method = RequestMethod.GET)
    public String show(Model model) {
        StringBuffer page = new StringBuffer();
        page.append("<!DOCTYPE html>");
        page.append("<html>");
        page.append("<body>");
        page.append("<h1>AquamatiX NWave/BCA Test</h1>");
        page.append("<p>Server Time: " + calender.getTime().toGMTString() + "</p>");
        if (modems.size() == 0) {
        	page.append("No Data");
        } else {
        	page.append("<table cellpadding='10'>");
        	page.append("<tr><th>Device ID</th><th>Signal</th><th>Station ID</th><th>Messages</th></tr>");
        	for (Map.Entry<String, NWaveModem> entry : modems.entrySet())
        	{
        		page.append(entry.getValue().toString());
        	}
        	page.append("</table>");
        }
        page.append("</body>");
        page.append("</html>");
    	return page.toString();
    }
}
