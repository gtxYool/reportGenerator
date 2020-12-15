/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guatex.sacod_reportgenerator.Objetos;

/**
 *
 * @author AHERNANDEZ
 */
public class Cliente {

	private String nombre;
	private String codigo;
	private String direccion;
	private String nit;
	private String banco;
	private String tipoCuenta;
	private String noCuenta;

	public Cliente(String codigo) {

	}

	public Cliente(String nombre, String codigo, String direccion) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.direccion = direccion;
		this.nit = "";
	}

	public Cliente(String nombre, String codigo, String direccion, String nit) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.direccion = direccion;
		this.nit = nit;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return notNull(nombre);
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return notNull(codigo);
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return notNull(direccion);
	}

	/**
	 * @return the nit
	 */
	public String getNit() {
		return notNull(nit);
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = notNull(nombre);
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = notNull(codigo);
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = notNull(direccion);
	}

	/**
	 * @param nit the nit to set
	 */
	public void setNit(String nit) {
		this.nit = notNull(nit);
	}

	private String notNull(String var) {
		return var != null ? var.trim() : "";
	}

	/**
	 * @return the banco
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * @return the noCuenta
	 */
	public String getNoCuenta() {
		return noCuenta;
	}

	/**
	 * @param banco the banco to set
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}

	/**
	 * @param tipoCuenta the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * @param noCuenta the noCuenta to set
	 */
	public void setNoCuenta(String noCuenta) {
		this.noCuenta = noCuenta;
	}
}
