package com.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.dao.CheckedNicknameDAO;
import com.dto.MemberDTO;
import com.dto.RegistDTO;
import com.service.LoginService;
import com.service.RegistService;

@Controller
public class RegistController {

	@Autowired
	private RegistService registService;

	@Autowired
	private CheckedNicknameDAO dao;
	private boolean idCheckExecuted = false;

	@GetMapping("/regist")
	public String showRegistPage() {
		return "regist"; // 회원가입 페이지 이름을 가정합니다.
	}

	@RequestMapping(value = "/login/idcheck.action", method = { RequestMethod.GET })
	@ResponseBody
	public int idcheck(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String nickname) {
		int result = dao.idcheck(nickname);
		List<String> result2 = registService.Deleteid_list(nickname);
		result+=result2.size();
		return result;
	}

	@PostMapping("/registA")
	public String saveUserData(RegistDTO registDTO,
			@RequestParam("profile_img_input") CommonsMultipartFile profile_img) {

		// registDTO에 email = mail1 + "@" + mail2 설정
		registDTO.setEmail(registDTO.getMail1() + "@" + registDTO.getMail2());

		String passwd = registDTO.getPasswd();
		String passwd2 = registDTO.getPasswd2();

/////////////////////////////////////////////////////////////////////////////////////////////
		try {

			if (!profile_img.isEmpty()) {
				// 파일 이름 생성 (파일명 중복 방지를 위해 uuid 삽입)
				UUID uuid = UUID.randomUUID();
				String fileName = uuid.toString() + "_" + profile_img.getOriginalFilename();

				final String endPoint = "https://kr.object.ncloudstorage.com";
				final String regionName = "kr-standard";

				// S3 client
				final AmazonS3 s3 = AmazonS3ClientBuilder.standard() // credentials.properties로 키 등록
						.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
						.withCredentials(
								new ClasspathPropertiesFileCredentialsProvider("com/config/credentials.properties"))
						.build();

				String bucketName = "team6-image";
				String objectName = "profile_img/" + fileName;

				// 파일 업로드
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(profile_img.getContentType()); // 파일 타입에 따라 적절한 값을 설정
				metadata.setContentLength(profile_img.getSize()); // 파일의 길이 (바이트)
				PutObjectRequest request;

				try {
					request = new PutObjectRequest(bucketName, objectName, profile_img.getInputStream(), metadata)
							.withCannedAcl(CannedAccessControlList.PublicRead);
					s3.putObject(request);
					registDTO.setProfile_img(fileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else { // 파일이 없을 때 기본값 설정
				registDTO.setProfile_img("profile_img_default.png");
			}

			if (!passwd.equals(passwd2)) {
				// 비밀번호가 일치하지 않는 경우 - 오류 페이지로 리다이렉트 또는 적절한 처리
				return "momo/checkedPasswd"; // 비밀번호 불일치를 나타내는 페이지로 리다이렉트
			}
			
			if (passwd.length() <= 9
					|| !passwd.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^!&*])[A-Za-z\\d@#$%^!&*]{9,}$")) {
				// 비밀번호가 9자 이하이거나 조건에 맞지 않을 때 처리
				return "momo/passwdFail";
			}
			
			/////////////////////////////////////////

			registService.saveUser(registDTO);

			return "redirect:login"; // 회원가입이 성공했을 때 로그인 페이지로 리다이렉트
		} catch (Exception e) {

			// 예외 처리

			if (e.getMessage().contains("ORA-00001")) {
				// 고유 제약 조건 위반으로 인한 예외라면 idcheck 페이지를 표시
				return "momo/idcheck2";
			} else {
				// 다른 예외의 경우, 적절히 처리하거나 리디렉션합니다
				System.out.println(e.getMessage());
				return "momo/registFail"; // 오류 페이지로 리디렉션하거나 예외를 적절히 처리합니다
			}
		}
	}
	@Autowired
	private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    @Autowired
    LoginService loginService;
    @Autowired
    RegistService registservice;

    @GetMapping("/emailAuthentication")
    @ResponseBody
    public String emailAuthentication(@RequestParam String mail1, @RequestParam String mail2,@RequestParam int ran) {
    	String email = mail1 + "@" + mail2;
    	SimpleMailMessage message = new SimpleMailMessage();
    	MemberDTO dto=loginService.findEmail(email);
    	List<String> emailList=registservice.Deleteid_list_byEmail(email);
    	
    	if(dto==null&&emailList.size()==0) {
    		message.setFrom("momoditteam06@gmail.com");
    		message.setTo(email);
    		message.setSubject("MoMo 회원가입 인증번호 입니다.");
    		message.setText("인증번호는 "+ran+" 입니다.");
    		mailSender.send(message);
    		return "success";
    		
    	}else {
    		
    		return  "fail";
    	}
    }
    

}