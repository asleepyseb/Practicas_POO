package org.example;

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

    private Animal(int id, String nombre, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public List<Animal> getAnimals() {
        List<Animal> animals = new ArrayList<>();

    }
}
