package org.tensorflow.lite.examples.detection.model;

public class Persona {
    private String uid;
    private String NombreCompleto;
    private String Correo;
    private String Fecha_infeccion;

    private Double ParasitosEncontrados;

    public Double getParasitosEncontrados() {
        return ParasitosEncontrados;
    }

    public void setParasitosEncontrados(Double parasitosEncontrados) {
        ParasitosEncontrados = parasitosEncontrados;
    }

    private String Nombre_ubicacion;
    private double Latitud;
    private double Longitud;

    public String getNombre_ubicacion() {
        return Nombre_ubicacion;
    }

    public void setNombre_ubicacion(String nombre_ubicacion) {
        Nombre_ubicacion = nombre_ubicacion;
    }

    public double getLatitud() {
        return Latitud;
    }

    public void setLatitud(double latitud) {
        Latitud = latitud;
    }

    public double getLongitud() {
        return Longitud;
    }

    public void setLongitud(double longitud) {
        Longitud = longitud;
    }

    public String getFecha_infeccion() {
        return Fecha_infeccion;
    }

    public void setFecha_infeccion(String fecha_infeccion) {
        Fecha_infeccion = fecha_infeccion;
    }

    public Persona() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        NombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    @Override
    public String toString() {
        return NombreCompleto;
    }

    public Persona(String uid, String NombreCompleto, String Correo, String Fecha_infeccion,
                   Double ParasitosEncontrados, String Nombre_ubicacion, double Latitud, double Longitud){
        this.uid=uid;
        this.NombreCompleto=NombreCompleto;
        this.Correo=Correo;
        this.Fecha_infeccion=Fecha_infeccion;

        this.ParasitosEncontrados=ParasitosEncontrados;

        this.Nombre_ubicacion=Nombre_ubicacion;
        this.Latitud=Latitud;
        this.Longitud=Longitud;
    }
}
