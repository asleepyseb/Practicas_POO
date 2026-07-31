package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class plataformaStreaming {
    private ArrayList<cuentaUsuario> cuentas;

    public plataformaStreaming() {
        this.cuentas = new ArrayList<>();
    }

    public void registrarUsuario(cuentaUsuario cuenta){
        cuentas.add(cuenta); // guardar la cuenta
        String sql = "INSERT INTO cuentas_streaming (correo_electronico, meses_activo, plan) VALUES (?, ?, ?)";

    }
}
