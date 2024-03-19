package idat.Proyecto.service;

import java.util.List;
import java.util.Optional;

import idat.Proyecto.entity.Categoria;

public interface CategoriaService {

	public abstract Categoria save(Categoria categoria);
	public abstract Optional<Categoria> get(Integer id);
	public abstract void update(Categoria categoria);
	public abstract void delete(Integer id);
	public abstract List<Categoria> findAll();
}
