package com.android.ferret_backend.repository;

import com.android.ferret_backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findByEmail(String email);
    Boolean existsUsuarioByEmailAndContrasenia(String username, String password);
    Boolean existsByEmail(String username);
    Boolean existsByContrasenia(String password);
}
