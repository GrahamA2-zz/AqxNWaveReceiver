package uk.co.aquamatix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.co.aquamatix.repository.ModemRepository;

@Controller
@RequestMapping("/")
public class SiteController {

	@Autowired
	ModemRepository modemRepository;

	@RequestMapping
	public String getModems(Model model) {
		model.addAttribute("modems", modemRepository.getAllModems());
		return "/home";
	}
}
