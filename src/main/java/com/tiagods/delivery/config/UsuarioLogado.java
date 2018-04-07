package com.tiagods.delivery.config;

import com.tiagods.delivery.model.Usuario;

public class UsuarioLogado {
    private static UsuarioLogado instance;
    private static Usuario usuario;

    public static UsuarioLogado getInstance() {
        if (instance == null) instance = new UsuarioLogado();
        return instance;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) { usuario = usuario;}
}
