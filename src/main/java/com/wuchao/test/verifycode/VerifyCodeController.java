package com.wuchao.test.verifycode;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuchao.utils.security.VerifyCodeUtil;

@Controller
public class VerifyCodeController {
    @RequestMapping("/getVerifyCode")
    public void test(HttpServletResponse response,HttpSession session) throws IOException {
        response.setContentType("img/jpeg");  
        response.setCharacterEncoding("utf-8");  
        
    	String s = VerifyCodeUtil.generateVerifyCode(4);
    	OutputStream os = response.getOutputStream();
    	VerifyCodeUtil.outputImage(400, 200, os, s);
        //return "verifyCodeTest";
    }
}
