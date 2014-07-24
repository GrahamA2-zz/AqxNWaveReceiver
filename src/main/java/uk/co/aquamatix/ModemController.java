package uk.co.aquamatix;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModemController {

	@RequestMapping("/modem")
	public String modemDetais(
			Model model,
			@RequestParam(value = "id", required = false, defaultValue = "52360") String deviceID) {
		model.addAttribute("modem", NWaveController.modems.get(deviceID));
		model.addAttribute("chartData",
				"[['Time', 'Value'],[2013,  1000],[2014,  1170],[2020,  660],[2021,  1030]]");
		return "modem";
	}
}
