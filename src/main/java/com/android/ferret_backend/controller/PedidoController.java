package com.android.ferret_backend.controller;

import com.android.ferret_backend.entity.Pedido;
import com.android.ferret_backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoService service;

    @GetMapping("/listar")
    public List<Pedido> listarTodo(){return service.getPedidos();}

    @GetMapping("/usuario/{id}")
    public List<Pedido> listarPedidos(@PathVariable("id") Long id){return service.getPedidosByUsuario(id);}

    @GetMapping("/{id}")
     public Optional<Pedido> obtenerPedido(@PathVariable("id") Long id){return service.getPedido(id);}

    @PostMapping("/crear")
    public void crearPedido(@RequestBody Pedido pedido){service.addPedido(pedido);}
}
