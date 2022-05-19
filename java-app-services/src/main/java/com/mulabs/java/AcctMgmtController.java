package com.mulabs.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/AcctMgmt")
public class AcctMgmtController extends BaseController {

    @RequestMapping(value = {"","/"})
    public String home() {
        return "Hello from WebAPI:home()";
    }

	@RequestMapping("/login")
	public String login() {
		return "Hello from login";
	}

	@RequestMapping("/gpg")
	public String pgp(HttpServletRequest request) {
		makeWebRequest("billing-services", "8080", "BillingServices/pgp", request);
		makeWebRequest("credit-service", "8080", "CreditService/pgp", request);
		return "Hello from purchaseGamePass";
	}

	@RequestMapping("/jg")
	public String jg(HttpServletRequest request) {
		makeWebRequest("credit-service", "8080", "CreditService/jg", request);
		return "Hello from jg";
	}


}