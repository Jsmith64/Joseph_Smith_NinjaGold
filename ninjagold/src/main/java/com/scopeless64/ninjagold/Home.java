package com.scopeless64.ninjagold;

import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class Home {

	@RequestMapping("")
	public String displayGame(HttpSession session) {
		if(session.getAttribute("gold") == null) {
			session.setAttribute("gold", 0);
		}
		if(session.getAttribute("activities") == null) {
			ArrayList<String> act = new ArrayList<String>();
			session.setAttribute("activities", act);
		}
		return "ninjagold.jsp";
	}
	
	@RequestMapping(path="process/{id}", method=RequestMethod.POST)
	public String process(HttpSession session, @PathVariable int id) {
		Random r = new Random();
		Date now = new Date();
		if(id == 1) {
			Integer newgold = r.nextInt(10)+10;
			Integer currentgold = (Integer) session.getAttribute("gold");
			String newact = "Found " + newgold + " at the farm at " + now;
			ArrayList<String> acts = (ArrayList<String>) session.getAttribute("activities");
			acts.add(newact);
			session.setAttribute("activities", acts);
			session.setAttribute("gold", currentgold+newgold);
		}
		else if(id == 2) {
			Integer newgold = r.nextInt(5)+5;
			Integer currentgold = (Integer) session.getAttribute("gold");
			String newact = "Found " + newgold + " at the cave at " + now;
			ArrayList<String> acts = (ArrayList<String>) session.getAttribute("activities");
			acts.add(newact);
			session.setAttribute("activities", acts);
			session.setAttribute("gold", currentgold+newgold);
		}
		else if(id == 3) {
			Integer newgold = r.nextInt(3)+2;
			Integer currentgold = (Integer) session.getAttribute("gold");
			session.setAttribute("gold", currentgold+newgold);
			String newact = "Found " + newgold + " at the house at " + now;
			ArrayList<String> acts = (ArrayList<String>) session.getAttribute("activities");
			acts.add(newact);
			session.setAttribute("activities", acts);
		}
		else if(id == 4) {
			Integer chance = r.nextInt(2)+1;
			Integer currentgold = (Integer) session.getAttribute("gold");
			Integer newgold = r.nextInt(50)+0;
			if(chance == 1) {
				session.setAttribute("gold", currentgold+newgold);
				String newact = "Found " + newgold + " at the casino at " + now;
				ArrayList<String> acts = (ArrayList<String>) session.getAttribute("activities");
				acts.add(newact);
				session.setAttribute("activities", acts);
			}
			else if(chance == 2 ) {
				session.setAttribute("gold", currentgold-newgold);
				String newact = "Lost " + newgold + " at the casino at " + now;
				ArrayList<String> acts = (ArrayList<String>) session.getAttribute("activities");
				acts.add(newact);
				session.setAttribute("activities", acts);
			}
		}
		return "redirect:/";
	}
	
	@RequestMapping("/reset")
	public String reset(HttpSession session) {
		session.setAttribute("gold", null);
		session.setAttribute("activities", null);
		return "redirect:/";
	} 
}
