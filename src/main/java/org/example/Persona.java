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

    public Persona(String nombre,  int edad, String sexo) throws SQLException{
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
    }

    public void save() throws SQLException {
        try(Connection con = conexionBD.getConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO animal (nombre, especie, edad, sexo) VALUES (?, ?, ?, ?)", java.sql.Statement.RETURN_GENERATED_KEYS)
        ){
            stmt.setString(1, this.nombre);
            stmt.setInt(2, this.edad);
            stmt.setString(3, this.sexo);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                this.id = rs.getInt(1);
            }
        }catch(Exception e){
            throw new SQLException();
        }
    }

    public int delete() throws SQLException {
        try(Connection con = conexionBD.getConnection();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM animal WHERE id = ?")
        ){
            stmt.setInt(1, this.id);
            return stmt.executeUpdate();
         }
    }

    public int update() throws SQLException {
        try (Connection con = conexionBD.getConnection();
             PreparedStatement stmt = con.prepareStatement("UPDATE animales SET nombre =?, edad =?, sexo =? WHERE id = ?");
        ) {
            stmt.setString(1, this.nombre);
            stmt.setInt(2, this.edad);
            stmt.setString(3, this.sexo);
            stmt.setInt(4, this.id);
            return stmt.executeUpdate();
        }
    }

    public void saveAnimal(Animal animal) throws SQLException {
        try(Connection con = conexionBD.getConnection();
        PreparedStatement stmt = con.prepareStatement("INSERT INTO animal(nombre, tipo, persona_id) VALUES (?, ?,?,?)")){
            stmt.setString(1, animal.getNombre());
            stmt.setString(2, animal.getTipo());
            stmt.setInt(3, animal.getId());
            stmt.setInt(4, animal.getId());
        }
    }

   public void getAnimal() throws Exception {
       try (Connection con = conexionBD.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM animales WHERE persona_id = ?")) {
           stmt.setInt(1, id);
           ResultSet rs = stmt.executeQuery();
           animals = new ArrayList<Animal>();
           while (rs.next()) {
               this.animals.add(new Animal(rs.getInt("id"), rs.getString("nombre"), rs.getString("tipo"), rs.getInt("persona_id")));
       }
       }
   }

}
