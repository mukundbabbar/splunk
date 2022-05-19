package com.mulabs.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/CreditService")
public class CreditServiceController extends BaseController {

    @RequestMapping(value = {"","/"})
    public String home() {
        return "Hello from CreditService:home()";
    }

	@RequestMapping("/pgp")
	public String purchaseGamePass(HttpServletRequest request) {
		makeWebRequest("address-check", "8080", "AddressCheck/pgp", request);
		return "Hello from pgp";
	}

	@RequestMapping("/jg")
	public String joinGame(HttpServletRequest request) {
		makeWebRequest("address-check", "8080", "AddressCheck/jg", request);
		return "Hello from jg";
	}

	@RequestMapping("/ua")
	public String updateAction(HttpServletRequest request) {
		makeWebRequest("address-check", "8080", "AddressCheck/ua", request);
		return "Hello from ua";
	}

	@RequestMapping("/gw")
	public String getWorld(HttpServletRequest request) {
		makeWebRequest("address-check", "8080", "AddressCheck/gw", request);
		return "Hello from gw";
	}
}