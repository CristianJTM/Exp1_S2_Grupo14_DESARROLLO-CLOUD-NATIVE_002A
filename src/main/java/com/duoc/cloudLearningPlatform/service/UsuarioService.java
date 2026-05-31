package com.duoc.cloudLearningPlatform.service;

import com.duoc.cloudLearningPlatform.dto.UsuarioDTO;
import com.duoc.cloudLearningPlatform.exception.ResourceNotFoundException;
import com.duoc.cloudLearningPlatform.model.Usuario;
import com.duoc.cloudLearningPlatform.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> findAll(){
        return usuarioRepository.findAll()
                .stream()
                .map(this::toDTO).toList();
    }

    public UsuarioDTO findById(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return toDTO(usuario);
    }

    public UsuarioDTO saveUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();

        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setContrasenia(usuarioDTO.getContrasenia());
        usuario.setRol(usuarioDTO.getRol());

        usuarioRepository.save(usuario);
        return toDTO(usuario);
    }

    public UsuarioDTO updateUsuario(Long id,UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setContrasenia(usuarioDTO.getContrasenia());
        usuario.setRol(usuarioDTO.getRol());

        usuarioRepository.save(usuario);
        return  toDTO(usuario);
    }

    public void deleteUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        usuarioRepository.delete(usuario);
    }

    private UsuarioDTO toDTO(Usuario usuario) {

        UsuarioDTO dto = new UsuarioDTO();

        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        dto.setRol(usuario.getRol());

        return dto;
    }
}
