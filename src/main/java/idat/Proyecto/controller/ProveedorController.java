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
import idat.Proyecto.entity.Proveedor;
import idat.Proyecto.service.ProveedorService;
@Controller
@RequestMapping("/proveedores")
public class ProveedorController {
	

	private final Logger LOGGER = LoggerFactory.getLogger(ProveedorController.class);
	private static final String GET_PROVEEDOR = "http://localhost:8000/proveedores/listar";

	@Autowired
	private ProveedorService prs;
	@Autowired
    private WebClient webClient;
	
	@GetMapping("")
	public String show(Model model) {
		
		 // Bloquea el hilo actual hasta que se complete la operación asincrónica
	    List<Proveedor> proveedors = webClient.get()
	            .uri(GET_PROVEEDOR)
	            .retrieve()
	            .bodyToMono(new ParameterizedTypeReference<List<Proveedor>>() {})
	            .block();

		
		model.addAttribute("bProveedores", proveedors);
		return "proveedores/show";
	}
	
	@GetMapping("/create")
	public String create() {
		
		return "proveedores/create";
	}
	

	@PostMapping("/save")
	public String save(Proveedor Proveedor, HttpSession session) throws IOException {
		
		
		//Prueba
		LOGGER.info("Este es el objeto producto{}", Proveedor);
		
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

		prs.save(Proveedor);
			
		
		return "redirect:/Proveedors";
	}
	
	@GetMapping("/editar/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Proveedor Proveedor = new Proveedor();
		Optional<Proveedor> optionalProveedor= prs.get(id);
		
		//Asignamos
		Proveedor = optionalProveedor.get();
		
		//Probamos
		LOGGER.info("Producto buscado: {}", Proveedor);
		
		//Enviamos el modelo con el objeto a editar
		model.addAttribute("proveedor", Proveedor);
		
		return "proveedores/edit";
	}
	
	@PostMapping("/update")
	public String update(Proveedor Proveedor,@RequestParam("img") MultipartFile file) throws IOException {
		
		Proveedor m = new Proveedor();
		m=prs.get(Proveedor.getId()).get();
		
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
		return "redirect:/proveedores";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		
		//Capturo el objeto
		Proveedor m = new Proveedor();
		m = prs.get(id).get();
		
		//Si no es diferente, que no me lo elimine de mi directorio
		/*
		if(!p.getImagen().equals("default.jpg")) {
			ups.deleteImage(p.getImagen());
			
		}*/
		
		
		prs.delete(id);
		return "redirect:/proveedores";
	}
}
