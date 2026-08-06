package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Persona {
    private int id;
    private String nombre;
    private int edad;
    private String sexo;
    private List<Animal> animals;

    private Persona(int id, String nombre, int edad, String sexo) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
    }

    public static List<Persona> getAllbyName(String name) throws Exception {
        try(Connection con = conexionBD.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from dueño where nombre = ?;");
        ){
            List<Persona> list = new ArrayList<>();
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Persona(rs.getInt("id"), rs.getString("nombre"), rs.getInt("edad"), rs.getString("sexo")));
            }
            return list;
        }
    }

    public void saveAnimal(Animal animal) throws Exception {
        try(Connection con = conexionBD.getConnection();
        PreparedStatement stmt = con.prepareStatement("INSERT INTO animal (nombre, especie, edad, sexo) VALUES (?, ?, ?, ?)")
        ){
            stmt.setString(1, animal.getNombre());
            stmt.setString(2, animal.getTipo());
            stmt.executeUpdate();
        }
    }

}
