package cn.xdf.selfStudyRoom.web.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
    public String login(){
        return "/framework/login";
    }
	
	
}
