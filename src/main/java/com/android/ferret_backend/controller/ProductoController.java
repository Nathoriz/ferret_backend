package com.android.ferret_backend.controller;

import com.android.ferret_backend.entity.Producto;
import com.android.ferret_backend.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoService service;

    @GetMapping("/listar")
    public List<Producto> listarProductos(){return service.getProductos();}

    @GetMapping("/buscar")
    public List<Producto> buscarProducto(String datos){ return service.getProductosByNombreOrMarca(datos,datos); }

    @GetMapping("/detalle/{id}")
    public Optional<Producto> detalleProducto(@PathVariable("id") Long id){ return service.getProducto(id);}

}
