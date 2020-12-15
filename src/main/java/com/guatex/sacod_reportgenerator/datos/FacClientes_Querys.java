package com.guatex.sacod_reportgenerator.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.guatex.sacod_reportgenerator.Objetos.Cliente;

public class FacClientes_Querys extends Conexion {

	private Connection con;
	private ResultSet rs;
	private PreparedStatement pr;
	public static final Logger logger = LogManager.getLogger(FacClientes_Querys.class);

	public Cliente getCliente(String codigo) {
		try {
			con = getConnection();
			System.out.println("a buscar el codigo: |" + codigo + "|");
			String query = "Select NOMBRE, DIRENVIOS1, NIT from FACCLIENTES where CODIGO=? ";
			pr = con.prepareStatement(query);
			pr.setString(1, codigo);
			rs = pr.executeQuery();
			if (rs.next()) {
				Cliente cli = new Cliente(rs.getString("NOMBRE"), codigo, rs.getString("DIRENVIOS1"),
						rs.getString("NIT"));
				System.out.println("Encontre al cliente");
				return getDataBanc(cli);
			}
		} catch (Exception e) {
			logger.info(e.getStackTrace());
			e.printStackTrace();
		} finally {
			CloseAll(con, pr, rs);
		}
		return null;
	}

// CONSULTA A TABLA REGCUENTASBANC
	public Cliente getDataBanc(Cliente cliente) {
		try {
			con = getConnection();
			String query = "Select NOCUENTA, BANNOMBRE, TIPOCUENTA from REGCUENTASBANC where CODCLIENTE=? AND PREDETERMINADO = 'S' ";
			pr = con.prepareStatement(query);
			pr.setString(1, cliente.getCodigo());
			System.out.println("buscando la cuenta de: " + cliente.getCodigo());
			rs = pr.executeQuery();
			if (rs.next()) {
				cliente.setNoCuenta(rs.getString(1));
				cliente.setBanco(rs.getString(2));
				cliente.setTipoCuenta(rs.getString(3));
				System.out.println("Encontre al cliente");
			}
			return cliente;
		} catch (Exception e) {
			logger.info(e.getStackTrace());
			e.printStackTrace();
		} finally {
			CloseAll(con, pr, rs);
		}
		System.out.println("No encontre al cliente");
		return null;
	}
}
