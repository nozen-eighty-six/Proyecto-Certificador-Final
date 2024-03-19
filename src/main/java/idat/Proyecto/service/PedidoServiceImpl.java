package idat.Proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.Proyecto.entity.Pedido;
import idat.Proyecto.repository.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	@Override
	public Pedido save(Pedido pedido) {
		// TODO Auto-generated method stub
		return repository.save(pedido);
	}

	@Override
	public Optional<Pedido> get(String id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public void update(Pedido pedido) {
		// TODO Auto-generated method stub
		repository.save(pedido);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public List<Pedido> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
