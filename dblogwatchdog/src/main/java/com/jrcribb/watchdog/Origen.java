package com.jrcribb.watchdog;

public class Origen {
    private int id;
    private String origen;
    private String ubicacion;

    // Constructor, getters y setters

    public Origen(int id, String origen, String ubicacion) {
        this.id = id;
        this.origen = origen;
        this.ubicacion = ubicacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Origen{" +
                "id=" + id +
                ", origen='" + origen + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                '}';
    }
}
