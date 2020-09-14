package com.ict.weather;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import my.util.LoginUtil;


@Controller
public class KakaoLoginController {

	@RequestMapping(value = "test", produces = "application/json")
	public String kakaoLogin(@RequestParam("code") String code, HttpSession session) throws Exception {
		System.out.println("kokaoC : " + code + session);
		// 로그인시 임시 코드값 = code
		//System.out.println("code값" + code);
		
		ModelAndView mv = new ModelAndView();
		// 결과값을 node에 담아줌
		JsonNode node = LoginUtil.getAccessToken(code);
		// accessToken에 사용자의 로그인한 모든 정보가 들어있음
		JsonNode accessToken = node.get("access_token");

		// 세션에 담기위해 문자열로 바꿔줌
		String token = accessToken.toString();

		// 사용자의 정보
		JsonNode userInfo = LoginUtil.getKakaoUserInfo(accessToken);

		String kemail = null;
		String kname = null;
		String kgender = null;
		String kbirthday = null;
		String kage = null;
		String kimage = null;

		// 유저정보 카카오에서 가져오기 Get properties
		JsonNode properties = userInfo.path("properties");
		JsonNode kakao_account = userInfo.path("kakao_account");
		kemail = kakao_account.path("email").asText();
		kname = properties.path("nickname").asText();
		kimage = properties.path("profile_image").asText();
		kgender = kakao_account.path("gender").asText();
		kbirthday = kakao_account.path("birthday").asText();
		kage = kakao_account.path("age_range").asText();

		System.out.println(kakao_account);

		// 세션에 담아서 보관
		session.setAttribute("token", token);

		//유저 이름
		session.setAttribute("userName",kname);
				
		session.setAttribute("kemail", kemail);
		session.setAttribute("kname", kname);
		session.setAttribute("kimage", kimage);
		session.setAttribute("kgender", kgender);
		session.setAttribute("kbirthday", kbirthday);
		session.setAttribute("kage", kage);
		session.setAttribute("accessToken", accessToken);

		session.setAttribute("kimage", kimage);

		//mv.setViewName("home");
		return "redirect:/";
		//return mv;
	}

	

	
	@RequestMapping("logout")
	public String logout(HttpSession session) throws Exception{
			String access = session.getAttribute("token").toString();
			LoginUtil.Logout(access);
			//세션 초기화
			session.invalidate();
			return "redirect:/";
					
	}

/*	
	
	@RequestMapping(value = "logout", produces = "application/json")
	public String logout(HttpSession session) {

		// 세션의 토큰을 가져옴
		JsonNode node = LoginUtil.Logout(session.getAttribute("token").toString());

		System.out.println("로그인 후 반환 되는 아이디 : " + node.get("id"));

		return "redirect:/home";
	}
*/
}
