package com.android.ferret_backend.service;

import com.android.ferret_backend.entity.MetodoPago;
import com.android.ferret_backend.repository.MetodoPagoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MetodoPagoService {
    private final MetodoPagoRepository repository;

    public List<MetodoPago> getMetodosPagos(){ return repository.findAll(); }

    public Optional<MetodoPago> getMetodoPago(Long id){ return repository.findById(id); }

}
