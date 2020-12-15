/**
 * @author DylanYool
 */
package com.guatex.sacod_reportgenerator.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Conexion {
	Connection con;
	CallableStatement cs;
	ResultSet rs;
	public static final Logger logger = LogManager.getLogger(Conexion.class);

	public Conexion() {

	}

	public Connection getConnection() {
		final String url = "jdbc:sqlserver://serverdb;DatabaseName=OperacionesPrueba";
		final String user = "operaciones";
		final String pass = "gtxgtx01";
		con = null;
		try {
			con = DriverManager.getConnection(url, user, pass);
			logger.info("\n***Conexión exitosa***\n");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("\n Conexión fallida, Algo malió sal... err: " + e.getMessage() + "\n");
		}
		return con;
	}

	public void CloseAll(Connection con, PreparedStatement pr, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pr != null) {
				pr.close();
				pr = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("\\n No se pudo cerrar todo, Algo malió sal... err: " + e.getMessage() + "\n");
		}
	}

	public String quitaNulo(String val) {
		return val != null ? val.trim() : "";
	}

}
