package uk.co.aquamatix;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.co.aquamatix.repository.ModemRepository;

@RestController
public class NWaveController {

	private final AtomicLong counter = new AtomicLong();
	private static Logger LOGGER;

	@Autowired
	private ModemRepository modemRepository;

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
			String deviceID = device_id; // In case I need to use a composite
											// key
											// later
			NWaveModem modem;
			if (modemRepository.containsModem(deviceID)) {
				modem = modemRepository.getModem(deviceID);
			} else {
				modem = new NWaveModem(device_id);
				modemRepository.addModem(modem);
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
		// Old Data page replaced by Modem Controller
		LOGGER.info(device_id);
		NWaveModem modem = modemRepository.getModem(device_id);

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
			for (NWaveData data : modem.getData()) {
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
		// Old version of the status page - replaced by a SiteController
		Calendar calender = Calendar.getInstance();

		model.addAttribute(modemRepository.getAllModems());
		model.addAttribute("RequestTime", calender.getTime().toGMTString());

		StringBuffer page = new StringBuffer();
		page.append("<!DOCTYPE html>");
		page.append("<html>");
		page.append("<body>");
		page.append("<h1>AquamatiX NWave/BCA Test</h1>");
		page.append("<p>Server Time: " + calender.getTime().toGMTString()
				+ "</p>");
		if (modemRepository.getAllModems().size() == 0) {
			page.append("No Data");
		} else {

			page.append("<table cellpadding='10'>");
			page.append("<tr><th>Device ID</th><th>Messages</th></tr>");
			for (NWaveModem entry : modemRepository.getAllModems()) {
				page.append(entry);
			}
			page.append("</table>");
		}
		page.append("</body>");
		page.append("</html>");
		return page.toString();

	}
}
