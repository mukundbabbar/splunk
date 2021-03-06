package com.mulabs.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/WebFrontEnd")
public class WebFrontEndController extends BaseController {

    @RequestMapping(value = {"","/"})
    public String home() {
        return "Hello from WebFrontEnd:home()";
    }

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		makeWebRequest("auth-services", "8080", "AuthServices/login", request);
		makeWebRequest("acct-mgmt", "8080", "AcctMgmt/login", request);
		return "Hello from login";
	}

	@RequestMapping("/pgp")
	public String pgp(HttpServletRequest request) {
		makeWebRequest("acct-mgmt", "8080", "AcctMgmt/pgp", request);
		return "Hello from purchaseGamePass";
	}

	@RequestMapping("/jg")
	public String jg(HttpServletRequest request) {
		makeWebRequest("acct-mgmt", "8080", "AcctMgmt/jg", request);
		return "Hello from jg";
	}


}