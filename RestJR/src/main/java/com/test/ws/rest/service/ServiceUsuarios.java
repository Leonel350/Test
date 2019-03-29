package com.test.ws.rest.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import com.test.ws.rest.clases.ConexionMySQL;

@Path("/Usuarios")
public class ServiceUsuarios {
	
	private Connection conectardb() {
		ConexionMySQL miconeccion = new ConexionMySQL();
		miconeccion.database="testdb";
		miconeccion.hostname="192.168.0.3";
		miconeccion.password="";
		miconeccion.port="3306";
		miconeccion.username="DESKTOP-VVO4DH0";
		return miconeccion.conectarMySQL();
	}
	
	@GET
	@Path("/verUsuarios")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String verusuarios() {
		
		try {
			Connection conexion= conectardb();
			PreparedStatement ps= conexion.prepareStatement("SELECT * FROM usuarios");
			ResultSet rs= ps.executeQuery();
			JSONObject usuarios= new JSONObject();
			while (rs.next()) {
				JSONArray usuario = new JSONArray();
				usuario.put(rs.getString("nombre"));
				usuario.put(rs.getString("apellido"));
				usuario.put(rs.getString("email"));
				usuario.put(rs.getString("password"));
				usuarios.accumulate(rs.getString("idusuarios"), usuario);
			}
			if(!usuarios.isEmpty()) {
				return usuarios.toString(1);
			}
			return "no hay usuarios";
			
		} catch (SQLException e) {
			return "error al buscar usuarios";
		}
		
		
	}
	
	@GET
	@Path("/crearUsuario/{name}/{lastname}/{email}/{password}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String crearusuario(
			@PathParam("name")String name,
			@PathParam("lastname")String lastname,
			@PathParam("email")String email, 
			@PathParam("password")String password) {
		
		try {
			Connection conexion= conectardb();
		      String query = " insert into usuarios (nombre, apellido, email, password)"
		        + " values (?, ?, ?, ?)";
		      PreparedStatement preparedStmt = conexion.prepareStatement(query);
		      preparedStmt.setString (1,name);
		      preparedStmt.setString (2, lastname);
		      preparedStmt.setString (3, email);
		      preparedStmt.setString (4, password);
		      preparedStmt.execute();
			return "usuario "+name+" ingresado correctamente";
		} catch (SQLException e) {
			return e.toString();
		}
	}
	
	@GET
	@Path("/modificarUsuario/{id}/{name}/{lastname}/{email}/{password}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String modificarUsuario(
			@PathParam("id")int id,
			@PathParam("name")String name,
			@PathParam("lastname")String lastname,
			@PathParam("email")String email, 
			@PathParam("password")String password) {
		try {
			Connection conexion= conectardb();
		      String query = " UPDATE usuarios SET nombre = ?, apellido = ?, email = ?, password = ? " + 
		      		"WHERE idusuarios=?;";
		      PreparedStatement preparedStmt = conexion.prepareStatement(query);
		      preparedStmt.setString (1,name);
		      preparedStmt.setString (2, lastname);
		      preparedStmt.setString (3, email);
		      preparedStmt.setString (4, password);
		      preparedStmt.setInt(5, id);
		      preparedStmt.execute();
		      int num= preparedStmt.getUpdateCount();
		      if(num==1) {
		    	  return "usuario "+name+" modificado correctamente";
		      }
		      else {
		    	  return "no se modificó";
		      }
			
		} catch (SQLException e) {
			return e.toString();
		}
	}
	
	@GET
	@Path("/eliminarUsuario/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String eliminarUsuario(
			@PathParam("id")int id ){
		//-1 no existe, 0 exceptionsql, 1 correcto
		
		try {
			Connection conexion= conectardb();
			  PreparedStatement preparedStmt = conexion.prepareStatement("SELECT nombre FROM Usuarios WHERE idusuarios=?");
			  preparedStmt.setInt(1,id);
			  ResultSet rs= preparedStmt.executeQuery();
			  if(!rs.next()) {
				  return "-1";
			  }
		      String query = " delete from usuarios where idusuarios=?";
		      preparedStmt = conexion.prepareStatement(query);
		      preparedStmt.setInt(1,id);
		      preparedStmt.execute();
		      return "1";
		} catch (SQLException e) {
			return e.toString();
		}
	}
	

}
