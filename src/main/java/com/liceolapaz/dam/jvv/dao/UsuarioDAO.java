package com.liceolapaz.dam.jvv.dao;

import com.liceolapaz.dam.jvv.model.Usuario;

public interface UsuarioDAO {

    Usuario login(String username, String password);

}
