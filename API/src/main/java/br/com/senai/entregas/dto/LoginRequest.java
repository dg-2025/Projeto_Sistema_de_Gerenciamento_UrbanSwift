package br.com.senai.entregas.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;

    private String senha;

}
