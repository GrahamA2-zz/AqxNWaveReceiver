package uk.co.aquamatix;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class SiteController {

	@RequestMapping(method = RequestMethod.GET)
	public String getModems(Model model) {
		model.addAttribute("modems", NWaveController.modems.values());
		return "home";
	}
}
