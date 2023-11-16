package com.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.MemberDTO;
import com.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	@GetMapping("/login")
	public String showLoginPage() {
		return "login"; // login.jsp로 연결
	}

	@PostMapping("/loginA")

	public String handleLogin(@RequestParam String mail1, @RequestParam String mail2, @RequestParam String passwd,
			HttpSession session) {
		String email = mail1 + "@" + mail2;

		// LoginService를 사용하여 사용자 인증
		boolean isAuthenticated = loginService.authenticateUser(email, passwd);

		if (isAuthenticated == true) {
			// 로그인 한 회원의 dto 받기
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("email", email);
			map.put("passwd", passwd);
			MemberDTO login = loginService.nicknameByLogin(map);
			session.setAttribute("login", login);

			// 로그인 성공 시 다른 페이지로 이동
			return "redirect:/";
		} else {
			// 인증 실패
			return "momo/loginFail"; // 실패 시 loginFail.jsp로 리디렉트
		}

	}
	
	// 로그아웃하면 세션을 삭제
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션에서 로그인 정보 삭제
		return "redirect:/";
	}
	
	// 로그인 실패했을 때 페이지 이동
	@GetMapping("/loginFail")
	public String loginFail() {
		return "checkLogin";
	}
	
	// 비밀번호 찾기
	@GetMapping("/findpassword")
    public String showFindpasswordPage() {
    	return "findpassword"; // findpassword.jsp로 연결
    }
	
	@Autowired
	private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @GetMapping("/SendAuthentication")
    @ResponseBody
    public String SendAuthentication(@RequestParam String mail1, @RequestParam String mail2,@RequestParam int ran) {
    	String email = mail1 + "@" + mail2;
    	SimpleMailMessage message = new SimpleMailMessage();
    	MemberDTO dto=loginService.findEmail(email);
    	
    	if(dto != null) {
    		message.setFrom("momoditteam06@gmail.com");
    		message.setTo(email);
    		message.setSubject("MoMo 비밀번호 찾기 인증번호 입니다.");
    		message.setText("인증번호는 "+ran+" 입니다.");
    		mailSender.send(message);
    		return "success";
    		
    	}else {
    		
    		return  "fail";
    	}
    }
    
    @PostMapping("/SendPassword")
    public String SendPassword(@RequestParam String mail1, @RequestParam String mail2,@RequestParam int ran) {
    	String email = mail1 + "@" + mail2;
    	SimpleMailMessage message = new SimpleMailMessage();
    	message.setFrom("momoditteam06@gmail.com");
    	message.setTo(email);
    	message.setSubject("MoMo 새로운 비밀번호 입니다.");
    	message.setText("새로운 비밀번호는 "+ran+" 입니다.");
    	int n = loginService.changePassword(email, ran);

    	mailSender.send(message);
    	return "redirect:login";
    }

}