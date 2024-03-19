package idat.Proyecto.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private Boolean activo;
	
	
	public Categoria() {
		// TODO Auto-generated constructor stub
	}
	
	@OneToMany(mappedBy = "categoria")
	private Collection<Producto> productos;
	

	public Categoria(Integer id, String nombre, Boolean activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Boolean getActivo() {
		return activo;
	}


	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	
}
