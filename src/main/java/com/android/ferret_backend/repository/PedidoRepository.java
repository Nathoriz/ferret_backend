package com.android.ferret_backend.repository;

import com.android.ferret_backend.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    public List<Pedido> findAllByUsuario_Id(Long id);
}
