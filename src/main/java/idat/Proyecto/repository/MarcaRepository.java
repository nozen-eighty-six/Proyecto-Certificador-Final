package idat.Proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.Proyecto.entity.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {

}
