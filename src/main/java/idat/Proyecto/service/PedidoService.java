package idat.Proyecto.service;

import java.util.List;
import java.util.Optional;

import idat.Proyecto.entity.Pedido;

public interface PedidoService {

	public abstract Pedido save(Pedido pedido);
	public abstract Optional<Pedido> get(String id);
	public abstract void update(Pedido pedido);
	public abstract void delete(String id);
	public abstract List<Pedido> findAll();
}
