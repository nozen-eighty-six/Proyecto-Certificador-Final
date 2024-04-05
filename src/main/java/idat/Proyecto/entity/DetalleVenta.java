package idat.Proyecto.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @ManyToOne
	    private Venta venta;

	    @ManyToOne
	    private Producto producto;

	    @Column(name = "talla")
	    private String talla;


	    @Column(name = "precio_unitario")
	    private double precioUnitario;

	    @Column(name = "cantidad")
	    private int cantidad;
	    
	    public DetalleVenta() {
			// TODO Auto-generated constructor stub
		}

		public DetalleVenta(Integer id, Venta venta,String talla, double precioUnitario,
				int cantidad) {
			super();
			this.id = id;
			this.venta = venta;
			this.talla = talla;
			this.precioUnitario = precioUnitario;
			this.cantidad = cantidad;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Venta getVenta() {
			return venta;
		}

		public void setVenta(Venta venta) {
			this.venta = venta;
		}

		public Producto getProducto() {
			return producto;
		}

		public void setProducto(Producto producto) {
			this.producto = producto;
		}

		public String getTalla() {
			return talla;
		}

		public void setTalla(String talla) {
			this.talla = talla;
		}

		public double getPrecioUnitario() {
			return precioUnitario;
		}

		public void setPrecioUnitario(double precioUnitario) {
			this.precioUnitario = precioUnitario;
		}

		public int getCantidad() {
			return cantidad;
		}

		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
	    

	    

}
