package org.example;

import java.rmi.ConnectIOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Animal {
    private int id;
    private String nombre;
    private String tipo;
    private int persona_id;

    public Animal(int id, String nombre, String tipo, int persona_id) throws Exception {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.persona_id = persona_id;
    }
    public Animal(String nombre, String tipo, int persona_id) throws Exception {
        this.nombre = nombre;
        this.tipo = tipo;
        this.persona_id = persona_id;
    }

    public void saveAnimal() throws Exception {
        try(Connection con = conexionBD.getConnection();
        PreparedStatement stmt = con.prepareStatement("INSERT INTO animal(nombre, tipo, persona_id) VALUES (?, ?, ?)", java.sql.Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, this.nombre);
            stmt.setString(2, this.tipo);
            stmt.setInt(3, this.persona_id);
            stmt.executeUpdate();
            ResultSet rs =  stmt.getGeneratedKeys();
            if(rs.next()){
                this.id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new SQLException();
        }
    }

    public int deleteAnimal() throws Exception {
        try (Connection con = conexionBD.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM animal WHERE id = ?")) {
            stmt.setInt(1, this.id);
            return stmt.executeUpdate();
        }
    }

    public int updateAnimal() throws SQLException {
        try(Connection con = conexionBD.getConnection();
        PreparedStatement stmt = con.prepareStatement("UPDATE animal SET nombre = ?, tipo = ?, persona_id = ? WHERE id = ?")) {
            stmt.setString(1, this.nombre);
            stmt.setString(2, this.tipo);
            stmt.setInt(3, this.persona_id);
            stmt.setInt(4, this.id);
            return stmt.executeUpdate();
        }

    }

    public static List<Animal> getAll() throws Exception {
        try(Connection con = conexionBD.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM animal ORDER BY nombre")){
            ResultSet rs = stmt.executeQuery();
            List <Animal> animals = new ArrayList<>();
            while(rs.next()){
                animals.add(new Animal(rs.getInt("id"), rs.getString("nombre"), rs.getString("tipo"), rs.getInt("persona_id")));
            }
            return animals;
        }
    }

    public static List<Animal> getAnimalsByName(String nombre) throws Exception {
        try(Connection con = conexionBD.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM animal WHERE nombre = ?")){
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            List<Animal> animals = new ArrayList<>();
            while(rs.next()){
                animals.add(new Animal(rs.getInt("id"), rs.getString("nombre"), rs.getString("tipo"), rs.getInt("persona_id")));
            }
            return animals;
        }
    }

    public static int deleteByName(String nombre) throws SQLException{
        try(Connection con = conexionBD.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM animal WHERE nombre = ?")){
            stmt.setString(1, nombre);
            return stmt.executeUpdate();

        }
    }

    public static Animal find(Integer id) throws Exception{
        try(Connection con = conexionBD.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT TOP 1 * FROM animal WHERE id = ?")){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Animal(id, rs.getString("nombre"), rs.getString("tipo"), rs.getInt("persona_id"));
            }
            return null;
        }
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getTipo(){
        return tipo;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public int getPersona_id(){
        return persona_id;
    }
    public void setPersona_id(int persona_id){
        this.persona_id = persona_id;
    }
}
