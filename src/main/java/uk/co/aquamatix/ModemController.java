package uk.co.aquamatix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uk.co.aquamatix.repository.ModemRepository;

@Controller
public class ModemController {

	@Autowired
	private ModemRepository modemRepository;

	@RequestMapping("/modem")
	public String modemDetais(
			Model model,
			@RequestParam(value = "id", required = false, defaultValue = "52360") String deviceID) {
		model.addAttribute("modem", modemRepository.getModem(deviceID));
		return "modem";
	}
}
