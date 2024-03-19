package idat.Proyecto.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "entradas")
public class Entrada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/*
	 * fecha de recepcion proveedor
	 *
	 */
	@OneToOne
	private Pedido pedidoEntrada;

	private String ubicacion;

	private String estado;

	public Entrada() {
		// TODO Auto-generated constructor stub
	}

	public Entrada(Integer id, String ubicacion, String estado) {
		super();
		this.id = id;
		this.ubicacion = ubicacion;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pedido getPedidoEntrada() {
		return pedidoEntrada;
	}

	public void setPedidoEntrada(Pedido pedidoEntrada) {
		this.pedidoEntrada = pedidoEntrada;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
