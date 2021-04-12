package br.com.gabrielbarbosa.cadpessoas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.gabrielbarbosa.cadpessoas.model.Pessoa;
import br.com.gabrielbarbosa.cadpessoas.repositories.PessoaRepository;

@Controller
@RequestMapping("/")
public class PessoaController {
	@Autowired
	PessoaRepository pessoaRepo;

	@GetMapping
	public String index() {
		return "index.html";
	}
	
	@GetMapping("/adicionarPessoa")
	public ModelAndView adicionarPessoa() {
		ModelAndView mav = new ModelAndView("adicionarPessoa");
		mav.addObject(new Pessoa());
		return mav;
	}

	@PostMapping("/adicionarPessoa")
	public String adicionarPessoa(Pessoa p) {
		this.pessoaRepo.save(p);
		return "redirect:/listarPessoas";
	}

	@GetMapping("/listarPessoas")
	public ModelAndView listarPessoas() {
		List<Pessoa> lista = pessoaRepo.findAll();
		ModelAndView mav = new ModelAndView("listarPessoas");
		mav.addObject("pessoas", lista);
		return mav;
	}

}