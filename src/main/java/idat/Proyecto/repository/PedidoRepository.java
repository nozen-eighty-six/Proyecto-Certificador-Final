package idat.Proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.Proyecto.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, String> {

}
