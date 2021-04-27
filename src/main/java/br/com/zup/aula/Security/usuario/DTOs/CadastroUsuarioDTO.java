package br.com.zup.aula.Security.usuario.DTOs;


import br.com.zup.aula.Security.usuario.Usuario;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CadastroUsuarioDTO {

    @CPF(message = "{cpf.invalido}")
    private String cpf;
    @Size(min = 10, max = 100, message = "{nome_completo.tamanho}")
    private String nomeCompleto;
    @Email(message = "{email.invalido}")
    private String email;
    @Size(min = 6, max = 10, message = "{senha.invalida}")
    private String senha;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario converterDTOParaModel(){
        Usuario usuario = new Usuario();
        usuario.setCpf(this.cpf);
        usuario.setEmail(this.email);
        usuario.setNomeCompleto(this.nomeCompleto);
        usuario.setSenha(this.senha);

        return usuario;
    }
}
