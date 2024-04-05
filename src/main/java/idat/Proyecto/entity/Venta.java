package idat.Proyecto.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "ventas")
public class Venta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@DateTimeFormat(pattern="yyyy-MM-dd",iso=ISO.DATE)
	@Column(name = "fecha_venta")
	private Date fechaVenta;
	
	@Column(name = "total_venta")
	private double totalVenta;

	@ManyToOne
	private Usuario usuario;
	
	@OneToMany(mappedBy = "venta")
	public Collection<DetalleVenta> detalleVenta;
	/*
	 * @Column(name = "metodo_pago") private String metodoPago;
	 */

	/*
	 * @Column(name = "estado_venta") private String estadoVenta;
	 */
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "empleado_id") private Empleado empleado;
	 */

	public Venta() {
		// TODO Auto-generated constructor stub
	}

	public Venta(Integer id, Date fechaVenta, double totalVenta) {
		super();
		this.id = id;
		this.fechaVenta = fechaVenta;
		this.totalVenta = totalVenta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Collection<DetalleVenta> getDetalleVenta() {
		return detalleVenta;
	}

	public void setDetalleVenta(Collection<DetalleVenta> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}

	public double getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(double totalVenta) {
		this.totalVenta = totalVenta;
	}
	

}
