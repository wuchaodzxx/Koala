package com.wuchao.test.verifycode;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class VerifyCodeTestController {
    @RequestMapping("/verifyCodeTest")
    public String test(Model model) {
    	
        return "/test/verifyCodeTest";
    }
}
