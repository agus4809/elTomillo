package com.elTomillo.demo.Mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.elTomillo.demo.DTOs.UsuarioDTO;
import com.elTomillo.demo.Entidades.Usuario;

@Component
public class UsuarioMapper {

    private ModelMapper mapper = new ModelMapper();

    public UsuarioDTO EntidadaDTO(Usuario usuario) {

        UsuarioDTO UsuarioDTO = mapper.map(usuario, UsuarioDTO.class);
        return UsuarioDTO;
    }

    public Usuario DTOaModel(UsuarioDTO UsuarioDTO) {
        Usuario usuario = mapper.map(UsuarioDTO, Usuario.class);
        return usuario;
    }

    public List<UsuarioDTO> listUsuariosaDTO(List<Usuario> list) {
        return list.stream().map(usuario -> mapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }
}
