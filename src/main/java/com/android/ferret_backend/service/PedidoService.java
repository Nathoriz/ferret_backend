package com.android.ferret_backend.service;

import com.android.ferret_backend.entity.Pedido;
import com.android.ferret_backend.entity.Producto;
import com.android.ferret_backend.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PedidoService {
    private final PedidoRepository repository;
    private final ProductoService productoService;

    public List<Pedido> getPedidos(){ return repository.findAll();}

    public List<Pedido> getPedidosByUsuario(Long id){ return repository.findAllByUsuario_Id(id);}

    public Optional<Pedido> getPedido(Long id){return repository.findById(id);}

    public void addPedido(Pedido pedido){
        Optional<Producto> producto = productoService.getProducto(pedido.getProducto().getId());
        if(producto.isPresent()){
            productoService.updateVentaProducto(pedido.getProducto().getId(),pedido.getCantidad());
        }
        Double total =producto.get().getPrecio() * pedido.getCantidad();
        pedido.setTotal(total);
        repository.save(pedido);}
}
