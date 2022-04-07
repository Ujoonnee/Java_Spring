package co.micol.prj;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping("/")
	public String home(Locale locale, Model model) {
		
		return "home/home";
	}
	
	@RequestMapping("/home.do")
	public String home() {
		return "home/home";
	}

	@RequestMapping("/home.do")
	public ModelAndView home(ModelAndView mv) {
		int a = 0;
		mv.addObject("message", "환영");
		
		if (a == 0) mv.setViewName("home/home");
		else mv.setViewName("home/home2");
		
		
		return mv;
	}
	
}
