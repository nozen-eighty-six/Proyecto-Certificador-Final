package idat.Proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.Proyecto.entity.Categoria;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria, Integer>{

}
