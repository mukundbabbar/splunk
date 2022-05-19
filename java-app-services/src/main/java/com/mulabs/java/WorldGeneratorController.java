package com.mulabs.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/AddressCheck")
public class WorldGeneratorController extends BaseController {

    @RequestMapping(value = {"","/"})
    public String home() {
        return "Hello from AddressCheck:home()";
    }

	@RequestMapping("/pgp")
	public String purchaseGamePass() {
		return "Hello from pgp";
	}

	@RequestMapping("/jg")
	public String joinGame(HttpServletRequest request) {
		return "Hello from jg";
	}

	@RequestMapping("/ua")
	public String updateAction(HttpServletRequest request) {
		return "Hello from ua";
	}

	@RequestMapping("/gw")
	public String getWorld(HttpServletRequest request) {
		return "Hello from gw";
	}


}