package tvtracker.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tvtracker.domain.User;
import tvtracker.services.SubscriptionService;
import tvtracker.services.UserService;

@CrossOrigin
@RestController
public class SubscriptionController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@RequestMapping(value="/subscriptions", method = RequestMethod.GET)
	public Map<String, Object> subscribed(Principal principal) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		User user = userService.findByEmail(principal.getName());
		res.put("shows", subscriptionService.read(user));
		
		return res;
	}

	@RequestMapping(value="/subscriptions", method = RequestMethod.POST)
	public Map<String, Object> subscribe(
			Principal principal,
			@RequestParam(value="tvmazeId", required=true) int tvmazeId
	) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		User user = userService.findByEmail(principal.getName());
		subscriptionService.create(tvmazeId, user);
		
		res.put("shows", subscriptionService.read(user));
		
		return res;
	}
	
	@RequestMapping(value="/subscriptions/{id}", method = RequestMethod.DELETE)
	public Map<String, Object> unsubscribe(
			Principal principal,
			@PathVariable int id
	) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		User user = userService.findByEmail(principal.getName());
		subscriptionService.delete(id, user);
		
		res.put("shows", subscriptionService.read(user));
		
		return res;
	}
}
