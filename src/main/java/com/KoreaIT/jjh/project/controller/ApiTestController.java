package com.KoreaIT.jjh.project.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApiTestController {
	
	@RequestMapping("usr/apiTest/test1")
	public String test1(Model model) throws IOException {
		
		StringBuilder urlBuilder = new StringBuilder("http://api.kcisa.kr/openapi/service/rest/convergence/conver8"); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=b75271d8-d105-42ad-923a-bc8626ca6a31"); /*서비스키*/
		urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")); /*세션당 요청레코드수*/
		urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")); /*페이지수*/
		urlBuilder.append("&" + URLEncoder.encode("keyword","UTF-8") + "=" + URLEncoder.encode("대전", "UTF-8")); /*검색어*/

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());

		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {

		rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		} else {

		rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));

		}

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {

		sb.append(line);

		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		
		model.addAttribute("line",line);
		
		return "usr/apiTest/test1";

	}

}
