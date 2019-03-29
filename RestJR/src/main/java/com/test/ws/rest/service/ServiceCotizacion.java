package com.test.ws.rest.service;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;
import com.test.ws.rest.clases.*;;


@Path("/Cotizacion")
public class ServiceCotizacion {
	@GET
	@Path("/Dolar")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String valorDolar() {
		Moneda moneda= new Moneda(new Dolar());
		JSONObject nuevo=new JSONObject();
		nuevo.accumulate("compra", String.valueOf(moneda.getCompra()));
		nuevo.accumulate("venta", String.valueOf(moneda.getVenta()));
		return nuevo.toString();
	}
	
	@GET
	@Path("/Euro")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String valorEuro() {
		return new Moneda(new Euro()).getCompra();
	}
	
	
	
	@GET
	@Path("/Real")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String valorReal() {
		return new Moneda(new Real()).getCompra();
	}
	
}
