package idat.Proyecto.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import idat.Proyecto.entity.Producto;
import idat.Proyecto.entity.Usuario;
import idat.Proyecto.service.CategoriaService;
import idat.Proyecto.service.MarcaService;
import idat.Proyecto.service.ProductoService;
import idat.Proyecto.service.UploadFileService;
import idat.Proyecto.service.UsuarioService;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import java.util.Arrays;

import org.springframework.http.*;
@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	private static final String GET_PRODUCTS = "http://localhost:8000/productos";
	private static final String GET_PRODUCT = "http://localhost:8000/productos/";
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
	static RestTemplate restTemplate = new RestTemplate();
	
	//Inyección
	@Autowired
	private ProductoService prs;
	
	@Autowired
	private CategoriaService cs;
	@Autowired
	private MarcaService ms;
	
	@Autowired
	private UsuarioService us;
	
	@Autowired
	private UploadFileService ups;
	@GetMapping("/listar")
	public String show(Model model) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<	Collection<Producto>> result = restTemplate.exchange(GET_PRODUCTS, HttpMethod.GET, entity, new ParameterizedTypeReference<Collection<Producto>>() {});
		
		LOGGER.info("productos",result);
		
		model.addAttribute("bProductos", result);
		return "productos/show";
	}
	@GetMapping("/modal")
	public String showModal(Model model) {
		Producto producto = new Producto();
		Optional<Producto> optionalProducto = prs.get(1);
		
		//Asignamos
		producto = optionalProducto.get();
		
		//Probamos
		LOGGER.info("Producto buscado: {}", producto);
		
		model.addAttribute("bMarcas", ms.findAll());
		LOGGER.info("marcas: {}", prs.findAll());
		model.addAttribute("bCategorias", cs.findAll());
		LOGGER.info("categorias: {}", prs.findAll());
		
		//Enviamos el modelo con el objeto a editar
		model.addAttribute("producto", producto);
		return "productos/create2";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("bMarcas", ms.findAll());
		LOGGER.info("marcas: {}", prs.findAll());
		model.addAttribute("bCategorias", cs.findAll());
		LOGGER.info("categorias: {}", prs.findAll());
		return "productos/create";
	}
	
	
	@PostMapping("/save")
	public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
		
		
		//Prueba
		LOGGER.info("Este es el objeto producto{}", producto);
		
		//Necesitamos un user
		Usuario u = us.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		
		//Guardamos
		producto.setUsuario(u);
		
		//Imagen
		if(producto.getId() == null) {
			String nombreImagen = ups.saveImage(file);
			//Asignamos
			producto.setImagen(nombreImagen);  
			
			
		}

		prs.save(producto);
			
		
		return "redirect:/administrador/admin";
	}
	
	@PostMapping("/imgSave")
	public void imgSave(@RequestPart(name = "img") MultipartFile file, HttpSession session) throws IOException {

			String nombreImagen = ups.saveImage(file);

	}
	
	@GetMapping("/editar/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Producto producto = new Producto();
		Optional<Producto> optionalProducto = prs.get(id);
		
		//Asignamos
		producto = optionalProducto.get();
		
		//Probamos
		LOGGER.info("Producto buscado: {}", producto);
		
		model.addAttribute("bMarcas", ms.findAll());
		LOGGER.info("marcas: {}", prs.findAll());
		model.addAttribute("bCategorias", cs.findAll());
		LOGGER.info("categorias: {}", prs.findAll());
		
		//Enviamos el modelo con el objeto a editar
		model.addAttribute("producto", producto);
		
		return "productos/edit";
	}
	
	@PostMapping("/update")
	public String update(Producto producto,@RequestParam("img") MultipartFile file) throws IOException {
		
		Producto p = new Producto();
		p=prs.get(producto.getId()).get();
		
		//Cuando editas otras propiedades, el parámetro file no recibe valor
		//Por tanto estará vacío al momento de la solicitud
		
				if(file.isEmpty()){
					
					producto.setImagen(p.getImagen());
				}
				else {
					
					//Si no es diferente, que no me lo elimine de mi directorio
					if(!p.getImagen().equals("default.jpg")) {
						ups.deleteImage(p.getImagen());
						
					}
					
					String nombreImagen = ups.saveImage(file);
					//Asignamos
					producto.setImagen(nombreImagen);  
					
				}
				producto.setUsuario(p.getUsuario());

		prs.update(producto);
		return "redirect:/productos";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		
		//Capturo el objeto
		Producto p = new Producto();
		p = prs.get(id).get();
		
		//Si no es diferente, que no me lo elimine de mi directorio
		if(!p.getImagen().equals("default.jpg")) {
			ups.deleteImage(p.getImagen());
			
		}
		
		
		prs.delete(id);
		return "redirect:/productos";
	}
	
	@GetMapping("/show")
	public String showProductoAdmin(@PathVariable Integer id) {
		

		return "productos/show";
	}

}
