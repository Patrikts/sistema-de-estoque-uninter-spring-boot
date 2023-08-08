package br.com.springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springboot.bo.FornecedorBO;
import br.com.springboot.bo.NotaEntradaBO;
import br.com.springboot.bo.ProdutoBO;
import br.com.springboot.model.NotaEntrada;

@Controller
@RequestMapping("/nota-entrada")
public class NotaEntradaController {

	@Autowired
	private NotaEntradaBO notaEntradaBO;
	
	@Autowired
	private FornecedorBO fornecedorBO;
	
	@Autowired
	private ProdutoBO produtoBO;
	
	@RequestMapping(value="/novo", method=RequestMethod.GET)
	public ModelAndView novo(ModelMap model) {
		model.addAttribute("notaEntrada", new NotaEntrada());
		model.addAttribute("fornecedores", fornecedorBO.listaTodos());
		return new ModelAndView("/nota-entrada/formulario", model);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String salva(@Valid @ModelAttribute NotaEntrada notaEntrada,
			BindingResult result,
			RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "/nota-entrada/formulario";
		}
		
		if (notaEntrada.getId() == null) {
			notaEntradaBO.insere(notaEntrada);
			attr.addFlashAttribute("feedback", "A nota de entrada foi cadastrada com sucesso");
		} else {
			notaEntradaBO.atualiza(notaEntrada);
			attr.addFlashAttribute("feedback", "Os dados da nota de entrada foram atualizados com sucesso");
		}
		
		return "redirect:/nota-entrada";
	}
}
