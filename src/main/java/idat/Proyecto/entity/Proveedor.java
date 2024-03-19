package idat.Proyecto.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "proveedores")
public class Proveedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	private String nombreProveedor;
	private String direccion;
	private String correo;
	private Integer telefono;
	
	
	@ManyToMany(mappedBy = "itemsProveedor")
	private Set<Producto> itemsProducto = new HashSet<>();
	
	@OneToMany(mappedBy = "proveedor")
	private Collection<Pedido> itemsPedido = new ArrayList<>();
	
	
	 
	public Proveedor() {
		// TODO Auto-generated constructor stub
	}

	public Proveedor(Integer id, String nombreProveedor, String direccion, String correo, Integer telefono) {
		super();
		this.id = id;
		this.nombreProveedor = nombreProveedor;
		this.direccion = direccion;
		this.correo = correo;
		this.telefono = telefono;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public Set<Producto> getItemsProducto() {
		return itemsProducto;
	}

	public void setItemsProducto(Set<Producto> itemsProducto) {
		this.itemsProducto = itemsProducto;
	}

	public Collection<Pedido> getItemsPedido() {
		return itemsPedido;
	}

	public void setItemsPedido(Collection<Pedido> itemsPedido) {
		this.itemsPedido = itemsPedido;
	}



}
