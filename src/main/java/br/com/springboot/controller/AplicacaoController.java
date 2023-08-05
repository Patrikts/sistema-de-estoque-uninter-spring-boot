package br.com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AplicacaoController {
	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String index() {
		return "index";
	}
}
