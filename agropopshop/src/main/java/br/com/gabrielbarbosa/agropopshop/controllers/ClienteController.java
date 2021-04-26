package br.com.gabrielbarbosa.agropopshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.gabrielbarbosa.agropopshop.model.Cliente;
import br.com.gabrielbarbosa.agropopshop.repositories.ClienteRepository;

@Controller
@RequestMapping("/")
public class ClienteController {
	@Autowired
	ClienteRepository clienteRepo;

	@GetMapping
	public String index() {
		return "index.html";
	}
	
	@GetMapping("/adicionarCliente")
	public ModelAndView adicionarPessoa() {
		ModelAndView mav = new ModelAndView("/clientes/adicionarCliente");
		mav.addObject(new Cliente());
		return mav;
	}

	@PostMapping("/adicionarCliente")
	public String adicionarPessoa(Cliente p) {
		this.clienteRepo.save(p);
		return "redirect:/listarClientes";
	}
	
	@GetMapping("/listarClientes")
	public ModelAndView listarClientes() {
		List<Cliente> lista = clienteRepo.findAll();
		ModelAndView mav = new ModelAndView("/clientes/listarClientes");
		mav.addObject("clientes", lista);
		return mav;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView formEditarPessoa(@PathVariable("id") long id) {
		Cliente cliente = clienteRepo.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
		
		ModelAndView modelAndView = new ModelAndView("/clientes/editarCliente");
		modelAndView.addObject(cliente);
		return modelAndView;
	}

	@PostMapping("/editar/{id}")
	public ModelAndView editarPessoa(@PathVariable("id") long id, Cliente cliente) {
		this.clienteRepo.save(cliente);
		return new ModelAndView("redirect:/listarClientes");
	}
	
	@GetMapping("/remover/{id}")
	public ModelAndView removerPessoa(@PathVariable long id) {
		Cliente aRemover = clienteRepo.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id)); 
		clienteRepo.delete(aRemover);
		return new ModelAndView("redirect:/listarClientes");
	}

}