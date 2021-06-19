package com.android.ferret_backend.service;

import com.android.ferret_backend.entity.Producto;
import com.android.ferret_backend.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductoService {
    private final ProductoRepository repository;

    public List<Producto> getProductos(){ return repository.findAll(); }

    public Optional<Producto> getProducto(Long id){return  repository.findById(id);}

    public List<Producto> getProductosByNombreOrMarca(String nombre, String marca){ return repository.findAllByNombreContainsOrMarcaContains(nombre,marca); }

    public void updateVentaProducto(Long id,int cantDisminuir){
        Producto producto = repository.findById(id).orElseThrow(()-> new IllegalStateException("El producto con el id" + id +" no existe"));
        if(cantDisminuir!=0){
            producto.setStock(producto.getStock() - cantDisminuir);
        }
    }
}
