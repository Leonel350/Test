package com.test.ws.rest.clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {

	// Librería de MySQL
    public String driver = "com.mysql.cj.jdbc.Driver";

    // Nombre de la base de datos
    public String database = "database";

    // Host
    public String hostname = "localhost";

    // Puerto
    public String port = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
   

    // Nombre de usuario
    public String username = "root";

    // Clave de usuario
    public String password = "123456789";

    public Connection conectarMySQL() {
    	String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection conn = null;

        try {
            Class.forName(driver);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
        	conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

        return conn;
    }

}