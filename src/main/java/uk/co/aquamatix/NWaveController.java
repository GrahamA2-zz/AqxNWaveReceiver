package uk.co.aquamatix;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NWaveController {

	private final AtomicLong counter = new AtomicLong();
	private static Logger LOGGER;

	static Map<String, NWaveModem> modems = new HashMap<String, NWaveModem>();

	static {
		LOGGER = LoggerFactory.getLogger(NWaveController.class);
		modems.put("52360", new NWaveModem("52360"));
		modems.put("52507", new NWaveModem("52507"));
		modems.put("55979", new NWaveModem("55979"));
		modems.put("57290", new NWaveModem("57290"));
		modems.put("57290", new NWaveModem("57290"));
	}

	// http://yourdomain.com/inc?id={device_id}&time={message_time}&signal={signal}&station={station_id}&data={data}
	@RequestMapping("/inc")
	public NWaveResponse processVersion1(
			@RequestParam(value = "id", required = false, defaultValue = "No ID") String device_id,
			@RequestParam(value = "time", required = false, defaultValue = "No Time") String message_time,
			@RequestParam(value = "signal", required = false, defaultValue = "No siginal") String signal,
			@RequestParam(value = "station", required = false, defaultValue = "No station") String station_id,
			@RequestParam(value = "data", required = false, defaultValue = "") String data) {
		LOGGER.info(device_id + " " + message_time + " " + signal + " "
				+ station_id + " " + data);

		try {
			String modemID = device_id; // In case I need to use a composite key
										// later
			NWaveModem modem;
			if (modems.containsKey(modemID)) {
				modem = modems.get(modemID);
			} else {
				modem = new NWaveModem(device_id);
				modems.put(modemID, modem);
			}
			modem.addData(signal, station_id, message_time, data);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return new NWaveResponse(counter.incrementAndGet(), device_id,
				message_time, signal, station_id, data);
	}

	@RequestMapping(value = { "/data" })
	public String processVersion1(
			@RequestParam(value = "id", required = false, defaultValue = "No ID") String device_id) {
		LOGGER.info(device_id);
		NWaveModem modem = modems.get(device_id);

		Calendar calender = Calendar.getInstance();
		StringBuffer page = new StringBuffer();
		page.append("<!DOCTYPE html>");
		page.append("<html>");
		page.append("<body>");
		page.append("<h1>AquamatiX NWave/BCA Test</h1>");
		page.append("<p>Server Time: " + calender.getTime().toGMTString()
				+ "</p>");
		page.append("<h3>Modem: " + device_id + "</h3>");
		if (modem.getData().size() == 0) {
			page.append("No Data");
		} else {
			page.append("<table cellpadding='10'>");
			page.append("<tr><th>Signal</th><th>Station ID</th><th>Time</th><th>Value</th></tr>");
			for (NWaveData data : modem.getData().values()) {
				page.append(data.toString());
			}
			page.append("</table>");
		}
		page.append("</body>");
		page.append("</html>");
		return page.toString();
	}

	@SuppressWarnings("deprecation")
	@RequestMapping("/status")
	public String show(Model model) {
		Calendar calender = Calendar.getInstance();

		model.addAttribute(modems.values());
		model.addAttribute("RequestTime", calender.getTime().toGMTString());

		StringBuffer page = new StringBuffer();
		page.append("<!DOCTYPE html>");
		page.append("<html>");
		page.append("<body>");
		page.append("<h1>AquamatiX NWave/BCA Test</h1>");
		page.append("<p>Server Time: " + calender.getTime().toGMTString()
				+ "</p>");
		if (modems.size() == 0) {
			page.append("No Data");
		} else {

			page.append("<table cellpadding='10'>");
			page.append("<tr><th>Device ID</th><th>Messages</th></tr>");
			for (Map.Entry<String, NWaveModem> entry : modems.entrySet()) {
				page.append(entry.getValue().toString());
			}
			page.append("</table>");
		}
		page.append("</body>");
		page.append("</html>");
		return page.toString();

	}
}
