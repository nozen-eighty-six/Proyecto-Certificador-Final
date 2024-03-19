package idat.Proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.Proyecto.entity.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

}
