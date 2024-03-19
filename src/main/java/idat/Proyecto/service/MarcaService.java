package idat.Proyecto.service;

import java.util.List;
import java.util.Optional;

import idat.Proyecto.entity.Marca;

public interface MarcaService {

	public abstract Marca save(Marca marca);
	public abstract Optional<Marca> get(Integer id);
	public abstract void update(Marca marca);
	public abstract void delete(Integer id);
	public abstract List<Marca> findAll();
}
