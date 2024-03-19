package idat.Proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.Proyecto.entity.Categoria;
import idat.Proyecto.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	@Override
	public Categoria save(Categoria categoria) {
		// TODO Auto-generated method stub
		return repository.save(categoria);
	}

	@Override
	public Optional<Categoria> get(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public void update(Categoria categoria) {
		// TODO Auto-generated method stub
		repository.save(categoria);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public List<Categoria> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
