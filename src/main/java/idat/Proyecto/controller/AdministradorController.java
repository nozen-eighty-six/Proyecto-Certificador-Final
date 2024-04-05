package idat.Proyecto.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import idat.Proyecto.entity.Categoria;
import idat.Proyecto.entity.Marca;
import idat.Proyecto.entity.Orden;
import idat.Proyecto.entity.Producto;
import idat.Proyecto.entity.Proveedor;
import idat.Proyecto.entity.Usuario;
import idat.Proyecto.service.CategoriaService;
import idat.Proyecto.service.MarcaService;
import idat.Proyecto.service.OrdenService;
import idat.Proyecto.service.ProductoService;
import idat.Proyecto.service.UsuarioService;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {
	private static final String GET_PRODUCTS = "http://localhost:8000/productos/listar-siguientes-5";
	private static final String GET_SALES = "http://localhost:8000/ventas/listar-siguientes-5";

	private static final String GET_CATEGORIES = "http://localhost:8000/categorias/listar";
	private static final String GET_BRANDS = "http://localhost:8000/marcas/listar";
	private static final String GET_PROVEEDOR = "http://localhost:8000/proveedores/listar";
	Logger log = LoggerFactory.getLogger(AdministradorController.class);
	static RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private ProductoService prs;

	@Autowired
	private UsuarioService us;

	@Autowired
	private CategoriaService cs;
	@Autowired
	private MarcaService ms;

	@Autowired
	private OrdenService os;
	
	@Autowired
    private WebClient webClient;

	@GetMapping("")
	public String home_GET(Model model) {
		List<Producto> productos = prs.findAll();
		model.addAttribute("productos", productos);
		return "administrador/home";
	}

	@GetMapping("/navegacion")
	public String home_admin(Model model, HttpSession session) {
		// Lista de usuarios
		if (session.getAttribute("idusuario") == null) {
			return "redirect:/usuario/cerrar";
		}
		else {
			Optional<Usuario> usuario = us.findById(Integer.parseInt(session.getAttribute("idusuario").toString()));
			
			model.addAttribute("usuario", usuario.get());
		}
		
		return "administrador/admin_copia";
	}

	@GetMapping("/productos")
	public String getProductos(Model model, HttpSession session) {
		//Lista de usuarios
		if(session.getAttribute("idusuario") == null) {
			return "redirect:/usuario/cerrar";
		}
		
		
		 // Bloquea el hilo actual hasta que se complete la operación asincrónica
	    List<Marca> marcas = webClient.get()
	            .uri(GET_BRANDS)
	            .retrieve()
	            .bodyToMono(new ParameterizedTypeReference<List<Marca>>() {})
	            .block();
		model.addAttribute("bMarcas", marcas);
		//LOGGER.info("marcas: {}", prs.findAll());
		List<Categoria> categorias = webClient.get()
	            .uri(GET_CATEGORIES)
	            .retrieve()
	            .bodyToMono(new ParameterizedTypeReference<List<Categoria>>() {})
	            .block();
		
		model.addAttribute("bCategorias", categorias);
		
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

		return "productos/show";
	}

	// Probando código de navegación
	/*
	@GetMapping("/navegacion")
	public String home_nav(Model model) {
		return "administrador/admin_copia";
	}*/

	@GetMapping("/usuarios")
	public String usuario(Model model, HttpSession session) {

		log.info("usuarios: {}", us.findAll());
		model.addAttribute("usuarios", us.findAll());
		return "administrador/usuarios";
	}

	@GetMapping("/ordenes")
	public String ordens(Model model) {
		model.addAttribute("ordene", os.findAll());
		log.info("ordenes: {}", os.findAll());
		return "administrador/ordenes";
	}

	@GetMapping("/detalle/{id}")
	public String detalle(@PathVariable Integer id, Model model) {
		log.info("El id de la orden:  {}", id);
		Orden orden = os.findById(id).get();

		model.addAttribute("detalles", orden.getDetalle());
		return "administrador/detalleorden";
	}

	@GetMapping("/cerrar")
	public String cerrarSesion(HttpSession session) {
		log.info(session.getAttribute("idusuario").toString());
		session.removeAttribute("idusuario");

		return "redirect:/usuario/login";
	}

}
