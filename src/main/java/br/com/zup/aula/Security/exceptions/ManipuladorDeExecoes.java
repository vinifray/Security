package br.com.zup.aula.Security.exceptions;

import br.com.zup.aula.Security.exceptions.erros.ObjetoDeErro;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ManipuladorDeExecoes {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ObjetoDeErro> errosDeValidacao(MethodArgumentNotValidException erro){
        // Converte os erros de validacao em erros mensagens de maneira resumida
        List<ObjetoDeErro> erros = erro.getBindingResult().getFieldErrors().stream()
                .map(objeto -> new ObjetoDeErro(
                        objeto.getField(),
                        objeto.getDefaultMessage())
                ).collect(Collectors.toList());

        return erros;
    }
}
