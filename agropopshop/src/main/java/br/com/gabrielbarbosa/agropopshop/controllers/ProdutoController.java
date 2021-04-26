package br.com.gabrielbarbosa.agropopshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.gabrielbarbosa.agropopshop.model.Produto;
import br.com.gabrielbarbosa.agropopshop.repositories.ProdutoRepository;

@Controller
@RequestMapping
public class ProdutoController {
	@Autowired
	ProdutoRepository produtoRepo;

	
	@GetMapping("/adicionarProduto")
	public ModelAndView adicionarPessoa() {
		ModelAndView mav = new ModelAndView("/produtos/adicionarProduto");
		mav.addObject(new Produto());
		return mav;
	}

	@PostMapping("/adicionarProduto")
	public String adicionarPessoa(Produto p) {
		this.produtoRepo.save(p);
		return "redirect:/listarProdutos";
	}
	
	@GetMapping("/listarProdutos")
	public ModelAndView listarProdutos() {
		List<Produto> lista = produtoRepo.findAll();
		ModelAndView mav = new ModelAndView("/produtos/listarProdutos");
		mav.addObject("produtos", lista);
		return mav;
	}
	
	@GetMapping("/produto/editar/{id}")
	public ModelAndView formEditarProduto(@PathVariable("id") long id) {
		Produto produto = produtoRepo.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
		
		ModelAndView modelAndView = new ModelAndView("/produtos/editarProduto");
		modelAndView.addObject(produto);
		return modelAndView;
	}

	@PostMapping("/produto/editar/{id}")
	public ModelAndView editarProduto(@PathVariable("id") long id, Produto produto) {
		this.produtoRepo.save(produto);
		return new ModelAndView("redirect:/listarProdutos");
	}
	
	@GetMapping("/produto/remover/{id}")
	public ModelAndView removerProduto(@PathVariable long id) {
		Produto aRemover = produtoRepo.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id)); 
		produtoRepo.delete(aRemover);
		return new ModelAndView("redirect:/listarProdutos");
	}

}