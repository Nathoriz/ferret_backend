package com.android.ferret_backend.controller;

import com.android.ferret_backend.entity.MetodoPago;
import com.android.ferret_backend.service.MetodoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/metodopago")
public class MetodoPagoController {
    @Autowired
    private MetodoPagoService service;

    @GetMapping("/listar")
    public List<MetodoPago> listarMetodosDePago(){ return service.getMetodosPagos();}

    @GetMapping("/obtener/{id}")
    public Optional<MetodoPago> obtenerMetodoDePago(@PathVariable("id") Long id){ return service.getMetodoPago(id);}
}
