package br.com.gabrielbarbosa.agropopshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.gabrielbarbosa.agropopshop.model.Dependente;
import br.com.gabrielbarbosa.agropopshop.repositories.DependenteRepository;

@Controller
@RequestMapping
public class DependenteController {
	@Autowired
	DependenteRepository dependenteRepo;

	@GetMapping("/adicionarDependente")
	public ModelAndView adicionarDependente() {
		ModelAndView mav = new ModelAndView("/dependentes/adicionarDependente");
		mav.addObject(new Dependente());
		return mav;
	}
	
	@PostMapping("/adicionarDependente")
	public String adicionarDependente(Dependente p) {

		System.out.println("\nantes ID pai: " + p.getIdPai() + "\nNome: " + p.getNome());
		this.dependenteRepo.save(p);
		System.out.println("\ndepois ID pai: " + p.getIdPai() + "\nNome: " + p.getNome());
		return "redirect:/listarClientes";
	}
	
	@GetMapping("/listarDependentes")
	public ModelAndView listarDependentes() {
		List<Dependente> lista = dependenteRepo.findAll();
		ModelAndView mav = new ModelAndView("/dependentes/listarDependentes");
		mav.addObject("dependentes", lista);
		return mav;
	}
	
	@GetMapping("/dependente/editar/{id}")
	public ModelAndView formeditarDependente(@PathVariable("id") long id) {
		Dependente dependente = dependenteRepo.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
		
		ModelAndView modelAndView = new ModelAndView("/dependentes/editarDependente");
		modelAndView.addObject(dependente);
		return modelAndView;
	}

	@PostMapping("/dependente/editar/{id}")
	public ModelAndView editarDependente(@PathVariable("id") long id, Dependente dependente) {
		this.dependenteRepo.save(dependente);
		return new ModelAndView("redirect:/listarClientes");
	}
	
	@GetMapping("/dependente/remover/{id}")
	public ModelAndView removerDependente(@PathVariable long id) {
		Dependente aRemover = dependenteRepo.findById(id)	
			.orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id)); 
		dependenteRepo.delete(aRemover);
		return new ModelAndView("redirect:/listarClientes");
	}

}