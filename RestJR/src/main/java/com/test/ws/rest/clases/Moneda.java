package com.test.ws.rest.clases;

public class Moneda {
    private Comportamiento comportamiento;
    private String compra;
    private String venta;

    public Moneda(Comportamiento comportamiento){
        this.comportamiento=comportamiento;
        compra= comportamiento.compra();
        venta= comportamiento.venta();

    }

    public void nuevoComportamiento(Comportamiento comportamiento){
        this.comportamiento=comportamiento;
        compra= comportamiento.compra();
        venta= comportamiento.venta();
    }
    public Comportamiento getComportamiento(){
        return this.comportamiento;
    }

    public String getCompra(){
        return compra;

    }
    public String getVenta(){
        return venta;
    }

}
