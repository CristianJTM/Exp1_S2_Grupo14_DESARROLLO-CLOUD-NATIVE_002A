package com.duoc.cloudlearningplatform.repository;

import com.duoc.cloudlearningplatform.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
