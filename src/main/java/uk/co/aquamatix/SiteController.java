package uk.co.aquamatix;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SiteController {

	@RequestMapping
	public String getModems(Model model) {
		model.addAttribute("modems", NWaveController.modems.values());
		return "home";
	}
}
