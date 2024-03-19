package idat.Proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import idat.Proyecto.entity.Categoria;
import idat.Proyecto.entity.Entrada;

@Controller
@RequestMapping("/entradas")
public class EntradaController {

	private static final String GET_ENTRIES=  "http://localhost:8000/entradas/listar-siguientes-5";
	@Autowired
    private WebClient webClient;
	
	public EntradaController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("")
	public String show(Model model) {
		List<Entrada> entradas = webClient.get()
	            .uri(GET_ENTRIES)
	            .retrieve()
	            .bodyToMono(new ParameterizedTypeReference<List<Entrada>>() {})
	            .block();
		
		model.addAttribute("bEntradas", entradas);
		return "entradas/show";
	}
	
	
}
