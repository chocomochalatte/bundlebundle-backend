package com.tohome.bundlebundle.member.controller;

import com.tohome.bundlebundle.member.service.OauthService;
import com.tohome.bundlebundle.member.util.JwtTokenUtils;
import com.tohome.bundlebundle.member.vo.LoginTokenVO;
import com.tohome.bundlebundle.member.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Log4j2
@Controller
@RequestMapping("api/member")
public class OatuhController {

	private final JwtTokenUtils jwtTokenUtils;
	private final OauthService OauthService;

	public OatuhController(JwtTokenUtils jwtTokenUtils, com.tohome.bundlebundle.member.service.OauthService oauthService) {
		this.jwtTokenUtils = jwtTokenUtils;
		OauthService = oauthService;
	}


	@PostMapping(value = "/oauth/token/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, String>> oauthTokenLogin(@PathVariable String id) throws Exception {
		System.out.println("token from Android: " + id);

		MemberVO user = OauthService.loginOauthService(id);
		String token = jwtTokenUtils.generateJwtToken(user);

		Map<String, String> loginTokenVO = new HashMap<>();
		loginTokenVO.put("token", token);

		return ResponseEntity.ok(loginTokenVO);
	}

}
