package com.example.rafaelgarciaexamen;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Entity(tableName = "contacto")
public class Agenda implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "apellidos")
    private String apellidos;

    @ColumnInfo(name = "telefono")
    private int telefono;

    @ColumnInfo(name = "fecNac")
    private String fecNac;

    @ColumnInfo(name = "calle")
    private String calle;

    @ColumnInfo(name = "numero")
    private int numero;

    public Agenda( String nombre, String apellidos, int telefono, String fecNac, String calle, int numero) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fecNac = fecNac;
        this.calle = calle;
        this.numero = numero;
    }

    public Agenda(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getFecNac() {
        return fecNac;
    }

    public void setFecNac(String fecNac) {
        this.fecNac = fecNac;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }


    @Override
    public String toString() {
        return "Agenda{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono=" + telefono +
                ", fecNac=" + fecNac +
                ", calle='" + calle + '\'' +
                ", numero=" + numero +
                '}';
    }
}
