package idat.Proyecto.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import idat.Proyecto.entity.Categoria;
import idat.Proyecto.entity.Categoria;
import idat.Proyecto.entity.Usuario;
import idat.Proyecto.service.CategoriaService;
import idat.Proyecto.service.CategoriaService;
import idat.Proyecto.service.UploadFileService;
import idat.Proyecto.service.UsuarioService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {


	private final Logger LOGGER = LoggerFactory.getLogger(CategoriaController.class);
	private static final String GET_CATEGORIES = "http://localhost:8000/categorias/listar";

	
	//Inyección
	@Autowired
	private CategoriaService prs;
	
	@Autowired
	private UsuarioService us;
	
	@Autowired
	private UploadFileService ups;
	
	@Autowired
    private WebClient webClient;
	@GetMapping("")
	public String show(Model model) {
		List<Categoria> categorias = webClient.get()
	            .uri(GET_CATEGORIES)
	            .retrieve()
	            .bodyToMono(new ParameterizedTypeReference<List<Categoria>>() {})
	            .block();
		
		model.addAttribute("bCategorias", categorias);
		return "categorias/show";
	}

	@GetMapping("/create")
	public String create() {
		
		return "categorias/create";
	}
	
	
	@PostMapping("/save")
	public String save(Categoria categoria, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
		
		
		//Prueba
		LOGGER.info("Este es el objeto Categoria{}", categoria);
		
		//Necesitamos un user
		/*Usuario u = us.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		
		//Guardamos
		Categoria.setUsuario(u);
		
		//Imagen
		if(Categoria.getId() == null) {
			String nombreImagen = ups.saveImage(file);
			//Asignamos
			Categoria.setImagen(nombreImagen);  
			
			
		}
*/
		prs.save(categoria);
			
		
		return "redirect:/categorias";
	}
	
	@GetMapping("/editar/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Categoria Categoria = new Categoria();
		Optional<Categoria> optionalCategoria = prs.get(id);
		
		//Asignamos
		Categoria = optionalCategoria.get();
		
		//Probamos
		LOGGER.info("Categoria buscado: {}", Categoria);
		
		//Enviamos el modelo con el objeto a editar
		model.addAttribute("categoria", Categoria);
		
		return "categorias/edit";
	}
	
	@PostMapping("/update")
	public String update(Categoria Categoria,@RequestParam("img") MultipartFile file) throws IOException {
		
		Categoria c = new Categoria();
		c=prs.get(Categoria.getId()).get();
		
		//Cuando editas otras propiedades, el parámetro file no recibe valor
		//Por tanto estará vacío al momento de la solicitud
		/*
				if(file.isEmpty()){
					
					Categoria.setImagen(p.getImagen());
				}
				else {
					
					//Si no es diferente, que no me lo elimine de mi directorio
					if(!p.getImagen().equals("default.jpg")) {
						ups.deleteImage(p.getImagen());
						
					}
					
					String nombreImagen = ups.saveImage(file);
					//Asignamos
					Categoria.setImagen(nombreImagen);  
					
				}
				Categoria.setUsuario(p.getUsuario());
*/
		prs.update(c);
		return "redirect:/categorias";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		
		//Capturo el objeto
		Categoria p = new Categoria();
		p = prs.get(id).get();
		
		//Si no es diferente, que no me lo elimine de mi directorio
		/*if(!p.getImagen().equals("default.jpg")) {
			ups.deleteImage(p.getImagen());
			
		}
		*/
		
		prs.delete(id);
		return "redirect:/categorias";
	}
}
