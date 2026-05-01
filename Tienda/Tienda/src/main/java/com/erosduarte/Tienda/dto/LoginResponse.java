package com.erosduarte.Tienda.dto;

public class LoginResponse {
    public String message;
    public Integer codigoUsuario;
    public String nombreUsuario;
    public String correoUsuario;

    public LoginResponse(String message, Integer codigoUsuario, String nombreUsuario, String correoUsuario) {
        this.message = message;
        this.codigoUsuario = codigoUsuario;
        this.nombreUsuario = nombreUsuario;
        this.correoUsuario = correoUsuario;
    }
}