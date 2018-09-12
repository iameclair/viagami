package co.uk.eclair.viagami.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.uk.eclair.viagami.services.FacebookService;


@RestController
@RequestMapping("/fb")
public class FbController
{
	private FacebookService facebookService;

	public FbController(final FacebookService facebookService)
	{
		this.facebookService = facebookService;
	}

	@GetMapping("/createFacebookAuthorization")
	public String createFacebookAuthorization(){
		return facebookService.createFacebookAuthorizationURL();
	}
	@GetMapping("/facebook")
	public String createFacebookAccessToken(@RequestParam("code") String code){
		return facebookService.createFacebookAccessToken(code);
	}
	@GetMapping("/getName")
	public String getNameResponse(){
		return facebookService.getName();
	}
}
