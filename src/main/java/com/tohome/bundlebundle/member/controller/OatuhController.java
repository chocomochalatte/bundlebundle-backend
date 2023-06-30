package com.tohome.bundlebundle.member.controller;

import com.tohome.bundlebundle.member.service.OauthService;
import com.tohome.bundlebundle.member.vo.MemberVO;
import com.tohome.bundlebundle.member.vo.OauthTokenVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;

@Log4j2
@Controller
public class OatuhController {

	private OauthService OauthService;

	@PostMapping(value = "api/member/oauth/token/{id}")
	@ResponseBody
	public ResponseEntity<OauthTokenVO> inquireCart1(@PathVariable String id){
		System.out.println("token from Android: " + id);
		OauthTokenVO token = new OauthTokenVO();
		token.setToken(id);

		MemberVO result = OauthService.loginOauthService(token);
		return new ResponseEntity<OauthTokenVO>(token, HttpStatus.OK);

	}

}
