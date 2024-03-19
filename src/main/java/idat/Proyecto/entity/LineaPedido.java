package idat.Proyecto.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "lineas_pedido")
public class LineaPedido  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto productos;

    private Integer cantidad;
    
    
    public LineaPedido() {
		// TODO Auto-generated constructor stub
	}


	public LineaPedido(Integer id, Pedido pedido, Producto productos, Integer cantidad) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.productos = productos;
		this.cantidad = cantidad;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Pedido getPedido() {
		return pedido;
	}


	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}


	public Producto getProductos() {
		return productos;
	}


	public void setProductos(Producto productos) {
		this.productos = productos;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
    
    

    // ... (otros métodos y atributos)

    // Constructores, getters y setters
}