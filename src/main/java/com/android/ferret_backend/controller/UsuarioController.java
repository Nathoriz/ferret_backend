package com.android.ferret_backend.controller;

import com.android.ferret_backend.entity.Usuario;
import com.android.ferret_backend.service.UsuarioService;
import com.android.ferret_backend.utils.dto.Login;
import com.android.ferret_backend.utils.dto.UsuarioInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping("/listar")
    public List<Usuario> listarUsuarios(){ return service.getUsuarios();}

    @GetMapping("/{id}")
    public ResponseEntity<?> ObtnerUsuario(@PathVariable("id") Long id){ return service.getUsuario(id);}


    @PostMapping("/crearCuenta")
    public void crearCuentaDeUsuario(@RequestBody Usuario usuario){ service.addUsuario(usuario);}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login){ return service.Login(login);}

    @PutMapping("/modificar/{id}")
    public void cambiarDatos(@PathVariable("id") Long id, @RequestParam String nombre, @RequestParam String appellido, @RequestParam String direccion){
        service.updateUsuario(id,nombre,appellido,direccion);
    }

    @PutMapping("/password/{id}")
    public void cambiarContrasenia(@PathVariable("id") Long id, @RequestParam String contrasenia){
        service.updatePassword(id,contrasenia);
    }

    @PutMapping("/eliminar/{id}")
    public void eliminarUsuario(@PathVariable("id") Long id){
        service.deleteUsuario(id);
    }
}
