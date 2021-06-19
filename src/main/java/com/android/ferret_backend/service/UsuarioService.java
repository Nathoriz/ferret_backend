package com.android.ferret_backend.service;

import com.android.ferret_backend.config.error.exceptions.BadRequest;
import com.android.ferret_backend.entity.Usuario;
import com.android.ferret_backend.repository.UsuarioRepository;
import com.android.ferret_backend.utils.MHelpers;
import com.android.ferret_backend.utils.dto.Login;
import com.android.ferret_backend.utils.dto.UsuarioInfo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;

    public List<Usuario> getUsuarios(){
        return repository.findAll();
    }

    public void addUsuario(Usuario usuario){
        Optional<Usuario> usuarioByEmail = Optional.ofNullable(repository.findByEmail(usuario.getEmail()));

        if (usuarioByEmail.isPresent()){
            throw new IllegalStateException("El email se encuentra registrado");
        }
        usuario.setEstado("Active");
        repository.save(usuario);
    }

    @Transactional
    public void updateUsuario(Long usuarioId, String name, String apellido, String dirección){
        Usuario usuario = repository.findById(usuarioId)
                .orElseThrow(()-> new IllegalStateException("El usuario con id " + usuarioId + " no existe"));

        if(name != null && name.length() > 0 && !Objects.equals(usuario.getNombre(), name)) {
            usuario.setNombre(name);
        }

        if(apellido != null && apellido.length() > 0 && !Objects.equals(usuario.getApellido(), apellido)) {
            usuario.setApellido(apellido);
        }

        if(dirección != null && dirección.length() > 0 && !Objects.equals(usuario.getDireccion(), dirección)) {
            usuario.setDireccion(dirección);
        }
    }

    @Transactional
    public void updatePassword(Long usuarioId, String contrasenia){
        Usuario usuario = repository.findById(usuarioId)
                .orElseThrow(()-> new IllegalStateException("El usuario con id " + usuarioId + " no existe"));
        if(contrasenia != null && contrasenia.length() > 0 && !Objects.equals(usuario.getContrasenia(), contrasenia)) {
            if(usuario.getContrasenia().equals(contrasenia)){
                throw new BadRequest("No puedes ingresar la misma contraseña que quieres modificar.");
            }else {
                usuario.setContrasenia(contrasenia);
            }
        }
    }

    @Transactional
    public  void deleteUsuario(Long usuarioId){
        Usuario usuario = repository.findById(usuarioId)
                .orElseThrow(()-> new IllegalStateException("El usuario con id " + usuarioId + " no existe"));
        usuario.setEstado("Inactive");
    }

    public ResponseEntity<?> Login(Login login){
        String email = login.getEmail();
        String contrasenia = login.getContrasenia();
        Map<String,Object> resp = new HashMap<>();
        if(email.isEmpty()) throw new BadRequest("Ingrese el nombre del usuario.");
        if(contrasenia.isEmpty()) throw new BadRequest("Ingrese la contraseña.");
        if(repository.existsUsuarioByEmailAndContrasenia(email,contrasenia)){
            Usuario usuario = repository.findByEmail(email);
            if(usuario.getEstado().equals("Inactive")){
                throw new BadRequest("Su usuario ha sido eliminado");
            }
            UsuarioInfo usuarioInfo = MHelpers.modelMapper().map(usuario,UsuarioInfo.class);
            resp.put("Mensaje","Valido");
            resp.put("Usuario", usuarioInfo);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }else {
            if(repository.existsByEmail(email)) throw new BadRequest("La contraseña es incorrecta");
            if(repository.existsByContrasenia(contrasenia)) throw new BadRequest("El email es incorrecto");
            if(!repository.existsByEmail(email) && !repository.existsByContrasenia(contrasenia)) {
                resp.put("Mensaje","El email y la contraseña ingresados son incorrectos");
            }
            return new ResponseEntity<>(resp,HttpStatus.UNAUTHORIZED);
        }
    }
}
