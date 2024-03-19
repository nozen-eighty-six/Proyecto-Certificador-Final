package idat.Proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.Proyecto.entity.Proveedor;
import idat.Proyecto.repository.ProveedorRepository;

@Service
public class ProveedorServiceImpl implements ProveedorService {

	@Autowired
	private ProveedorRepository repository;
	@Override
	public Proveedor save(Proveedor proveedor) {
		// TODO Auto-generated method stub
		return repository.save(proveedor);
	}

	@Override
	public Optional<Proveedor> get(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public void update(Proveedor proveedor) {
		// TODO Auto-generated method stub
		repository.save(proveedor);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public List<Proveedor> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
