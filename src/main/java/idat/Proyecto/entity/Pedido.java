package idat.Proyecto.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	
	private String id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd",iso=ISO.DATE)
	private Date fPedido;

	@DateTimeFormat(pattern="yyyy-MM-dd",iso=ISO.DATE)
	private Date fEntrega;
	@ManyToOne
	private Proveedor proveedor;
	
	@ManyToMany
	private Set<Producto> itemsProducto = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LineaPedido> lineasPedido = new HashSet<>();
	
	private Double total;
	private String estado;
	@PrePersist
	public void prePersist() {
		fPedido = new Date();
	}
	
	
	
	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	

	public Pedido(String id, Date fPedido, Date fEntrega, Integer cantidad, Double total, String estado) {
		super();
		this.id = id;
		this.fPedido = fPedido;
		this.fEntrega = fEntrega;
		this.total = total;
		this.estado = estado;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public Date getfPedido() {
		return fPedido;
	}

	public void setfPedido(Date fPedido) {
		this.fPedido = fPedido;
	}

	
	public Date getfEntrega() {
		return fEntrega;
	}



	public void setfEntrega(Date fEntrega) {
		this.fEntrega = fEntrega;
	}



	public Proveedor getProveedor() {
		return proveedor;
	}



	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}





	public Double getTotal() {
		return total;
	}



	public void setTotal(Double total) {
		this.total = total;
	}



	public Set<Producto> getItemsProducto() {
		return itemsProducto;
	}

	public void setItemsProducto(Set<Producto> itemsProducto) {
		this.itemsProducto = itemsProducto;
	}
}
