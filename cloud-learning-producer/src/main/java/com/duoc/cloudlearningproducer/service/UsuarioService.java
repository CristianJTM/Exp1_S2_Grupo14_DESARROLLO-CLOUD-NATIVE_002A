package com.duoc.cloudlearningproducer.service;

import com.duoc.cloudlearningproducer.dto.UsuarioDTO;
import com.duoc.cloudlearningproducer.dto.UsuarioResumenDTO;
import com.duoc.cloudlearningproducer.exception.ResourceNotFoundException;
import com.duoc.cloudlearningproducer.model.Usuario;
import com.duoc.cloudlearningproducer.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioResumenDTO> findAll(){
        return usuarioRepository.findAll()
                .stream()
                .map(this::toDTO).toList();
    }

    public UsuarioResumenDTO findById(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return toDTO(usuario);
    }

    public UsuarioResumenDTO saveUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();

        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setContrasenia(usuarioDTO.getContrasenia());
        usuario.setRol(usuarioDTO.getRol());

        usuarioRepository.save(usuario);
        return toDTO(usuario);
    }

    public UsuarioResumenDTO updateUsuario(Long id,UsuarioDTO usuarioDTO){
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

    private UsuarioResumenDTO toDTO(Usuario usuario) {

        UsuarioResumenDTO dto = new UsuarioResumenDTO();

        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        dto.setRol(usuario.getRol());

        return dto;
    }
}
