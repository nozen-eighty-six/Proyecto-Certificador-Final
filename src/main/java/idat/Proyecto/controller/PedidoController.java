package idat.Proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import idat.Proyecto.entity.Pedido;
import idat.Proyecto.entity.Producto;
import idat.Proyecto.entity.Proveedor;
import idat.Proyecto.service.PedidoService;
import idat.Proyecto.service.ProveedorService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
@Controller
@RequestMapping("/pedidos")
public class PedidoController {
	
	private static final String GET_PRODUCTS = "http://localhost:8000/productos/listar-siguientes-5";
	private static final String GET_PROVEEDOR = "http://localhost:8000/proveedores/listar";
	private static final String GET_PEDIDOS = "http://localhost:8000/pedidos/listar-siguientes-5";
	private static final String GET_PEDIDOS_LAST_ID = "http://localhost:8000/pedidos/ultimoid";


	@Autowired
	private PedidoService prs;

	@Autowired
    private WebClient webClient;
	@GetMapping("")
	public String show(Model model) {
		
		List<Pedido> pedidos= webClient.get()
	            .uri(GET_PEDIDOS)
	            .retrieve()
	            .bodyToMono(new ParameterizedTypeReference<List<Pedido>>() {})
	            .block();
		
		model.addAttribute("bPedidos", pedidos);
		
		List<Proveedor> proveedores = webClient.get()
	            .uri(GET_PROVEEDOR)
	            .retrieve()
	            .bodyToMono(new ParameterizedTypeReference<List<Proveedor>>() {})
	            .block();
		
		model.addAttribute("bProvee",proveedores);
		//LOGGER.info("categorias: {}", prs.findAll());
		//model.addAttribute("bProductos", prs.findAll());
		
	    // Bloquea el hilo actual hasta que se complete la operación asincrónica
	    List<Producto> productos = webClient.get()
	            .uri(GET_PRODUCTS)
	            .retrieve()
	            .bodyToMono(new ParameterizedTypeReference<List<Producto>>() {})
	            .block();

	    // Añade la lista de productos al modelo
	    model.addAttribute("bProductos", productos);
	    
	    String pedidoId= webClient.get()
	            .uri(GET_PEDIDOS_LAST_ID)
	            .retrieve()
	            .bodyToMono(String.class)
	            .block();
	    
	    model.addAttribute("idPedidoGenerado", pedidoId);
		

		return "pedidos/show";
	}
	
	@GetMapping("/create")
	public String create() {
		
		return "pedidos/create";
	}
	
}
