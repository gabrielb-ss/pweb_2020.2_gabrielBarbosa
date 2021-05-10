package br.com.gabrielbarbosa.agropopshop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.gabrielbarbosa.agropopshop.model.Cliente;
import br.com.gabrielbarbosa.agropopshop.model.Dependente;
import br.com.gabrielbarbosa.agropopshop.repositories.ClienteRepository;
import br.com.gabrielbarbosa.agropopshop.repositories.DependenteRepository;

@Controller
@RequestMapping("/")
public class ClienteController {
	@Autowired
	ClienteRepository clienteRepo;
	@Autowired 
	DependenteRepository dependenteRepo;

	@GetMapping
	public String index() {
		return "index.html";
	}
	
	@GetMapping("/adicionarCliente")
	public ModelAndView adicionarCliente() {
		ModelAndView mav = new ModelAndView("/clientes/adicionarCliente");
		mav.addObject(new Cliente());
		return mav;
	}

	@PostMapping("/adicionarCliente")
	public String adicionarCliente(Cliente p) {
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
	public ModelAndView formeditarCliente(@PathVariable("id") long id) {
		Cliente cliente = clienteRepo.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
		
		ModelAndView mav = new ModelAndView("/clientes/editarCliente");
		
		List<Dependente> lista = dependenteRepo.findAll();
		List<Dependente> depend = new ArrayList<>();
		for (int i = 0; i < lista.size(); ++i) {
			if(lista.get(i).getIdPai() == id) {
				depend.add(lista.get(i));
			}
		}
		
		mav.addObject(cliente);
		mav.addObject("dependentes", depend);

		
		return mav;
	}

	@PostMapping("/editar/{id}")
	public ModelAndView editarCliente(@PathVariable("id") long id, Cliente cliente) {
		this.clienteRepo.save(cliente);
		return new ModelAndView("redirect:/editar/{id}") ;
	}
	
	@GetMapping("/remover/{id}")
	public ModelAndView removerCliente(@PathVariable long id) {
		Cliente aRemover = clienteRepo.findById(id)	
			.orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id)); 
		clienteRepo.delete(aRemover);
		return new ModelAndView("redirect:/listarClientes");
	}

}