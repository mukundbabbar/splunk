package com.mulabs.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/LoanAPI")
public class LoanAPIController extends BaseController {

    @RequestMapping(value = {"","/"})
    public String home() {
        return "Hello from LocanAPI:home()";
    }

	@RequestMapping("/ua")
	public String ua(HttpServletRequest request) {
		makeWebRequest("create-account", "8001", "CreateAccount/ua", request);
		makeWebRequest("login", "8080", "LoginService/ua", request);
		return "Hello from ua";
	}

	@RequestMapping("/c")
	public String c(HttpServletRequest request) {
		makeWebRequest("create-account", "8001", "CreateAccount/c", request);
		return "Hello from c";
	}

	@RequestMapping("/gw")
	public String gw(HttpServletRequest request) {
		makeWebRequest("login", "8080", "LoginService/gw", request);
		return "Hello from gw";
	}


}