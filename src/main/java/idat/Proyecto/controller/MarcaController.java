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

import idat.Proyecto.entity.Marca;
import idat.Proyecto.entity.Producto;
import idat.Proyecto.entity.Usuario;
import idat.Proyecto.service.MarcaService;
import idat.Proyecto.service.ProductoService;
import idat.Proyecto.service.UploadFileService;
import idat.Proyecto.service.UsuarioService;

@Controller
@RequestMapping("/marcas")
public class MarcaController {
	

	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
	
	private static final String GET_BRANDS = "http://localhost:8000/marcas/listar-siguientes-5";

	//Inyección
	@Autowired
	private MarcaService prs;
	
	@Autowired
	private UsuarioService us;
	
	@Autowired
	private UploadFileService ups;
	@Autowired
    private WebClient webClient;
	@GetMapping("")
	public String show(Model model) {
		
		// Bloquea el hilo actual hasta que se complete la operación asincrónica
	    List<Marca> marcas = webClient.get()
	            .uri(GET_BRANDS)
	            .retrieve()
	            .bodyToMono(new ParameterizedTypeReference<List<Marca>>() {})
	            .block();
		model.addAttribute("bMarcas", marcas);
		return "marcas/show";
	}

	@GetMapping("/create")
	public String create() {
		
		return "marcas/create";
	}
	
	
	@PostMapping("/save")
	public String save(Marca marca, HttpSession session) throws IOException {
		
		
		//Prueba
		LOGGER.info("Este es el objeto producto{}", marca);
		
		//Necesitamos un user
		//Usuario u = us.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		
		//Guardamos
		//producto.setUsuario(u);
		
		//Imagen
		/*
		if(producto.getId() == null) {
			String nombreImagen = ups.saveImage(file);
			//Asignamos
			producto.setImagen(nombreImagen);  
			
			
		}*/

		prs.save(marca);
			
		
		return "redirect:/marcas";
	}
	
	@GetMapping("/editar/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Marca marca = new Marca();
		Optional<Marca> optionalMarca= prs.get(id);
		
		//Asignamos
		marca = optionalMarca.get();
		
		//Probamos
		LOGGER.info("Producto buscado: {}", marca);
		
		//Enviamos el modelo con el objeto a editar
		model.addAttribute("marca", marca);
		
		return "marcas/edit";
	}
	
	@PostMapping("/update")
	public String update(Marca marca,@RequestParam("img") MultipartFile file) throws IOException {
		
		Marca m = new Marca();
		m=prs.get(marca.getId()).get();
		
		//Cuando editas otras propiedades, el parámetro file no recibe valor
		//Por tanto estará vacío al momento de la solicitud
		/*
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
*/
		prs.update(m);
		return "redirect:/marcas";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		
		//Capturo el objeto
		Marca m = new Marca();
		m = prs.get(id).get();
		
		//Si no es diferente, que no me lo elimine de mi directorio
		/*
		if(!p.getImagen().equals("default.jpg")) {
			ups.deleteImage(p.getImagen());
			
		}*/
		
		
		prs.delete(id);
		return "redirect:/marcas";
	}

}
