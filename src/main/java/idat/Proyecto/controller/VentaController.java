package idat.Proyecto.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import idat.Proyecto.entity.Marca;
import idat.Proyecto.entity.Venta;
import idat.Proyecto.service.MarcaService;
import idat.Proyecto.service.UploadFileService;
import idat.Proyecto.service.UsuarioService;

@Controller
@RequestMapping("/ventas")
public class VentaController {


	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
	
	private static final String GET_SALES= "http://localhost:8000/ventas/listar-siguientes-5";

	@Autowired
    private WebClient webClient;
	@GetMapping("")
	public String show(Model model) {
		
		// Bloquea el hilo actual hasta que se complete la operación asincrónica
	    List<Venta> marcas = webClient.get()
	            .uri(GET_SALES)
	            .retrieve()
	            .bodyToMono(new ParameterizedTypeReference<List<Venta>>() {})
	            .block();
		model.addAttribute("bVentas", marcas);
		return "ventas/show";
	}
}
