package br.com.zup.aula.Security.usuario.DTOs;

import br.com.zup.aula.Security.usuario.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDTO {

    private String email;
    private String nomeCompleto;

    public UsuarioDTO(String email, String nomeCompleto) {
        this.email = email;
        this.nomeCompleto = nomeCompleto;
    }

    public UsuarioDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public static UsuarioDTO converterModelParaDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getEmail(), usuario.getNomeCompleto());
        return usuarioDTO;
    }

    public static List<UsuarioDTO> converterListaDeModelParaDTO(List<Usuario> usuarios){
        //Converte a Lista de Usuario em uma Lista de DTO usando o map da lista usuarios
        List<UsuarioDTO> usuarioDTOS = usuarios.stream()
                .map(objeto -> converterModelParaDTO(objeto)).collect(Collectors.toList());

        return usuarioDTOS;
    }

}
