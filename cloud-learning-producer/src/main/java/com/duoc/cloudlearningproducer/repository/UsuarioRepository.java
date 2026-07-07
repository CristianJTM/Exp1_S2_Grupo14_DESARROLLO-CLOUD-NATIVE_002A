package com.duoc.cloudlearningproducer.repository;

import com.duoc.cloudlearningproducer.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
