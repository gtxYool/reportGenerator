/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guatex.sacod_reportgenerator.Objetos;

/**
 *
 * @author DylanYool
 * @since 7 de septiembre, 2020.
 */
public class EncabezadoColumna {

	private String Nombre;
	private String atributoName;
	private boolean sumar = false;

	/**
	 * Crea un objeto de tipo EncabezadoColumna
	 *
	 * @param titulo Titulo que se mostrará en e encabezado de la columna.
	 */
	public EncabezadoColumna(String titulo) throws Exception {
		titulo = titulo.trim();
		this.Nombre = notNull(titulo);
		this.atributoName = notNull(titulo);
	}

	/**
	 * Crea un objeto de tipo EncabezadoColumna
	 *
	 * @param titulo       Titulo que se mostrará en e encabezado de la columna.
	 * @param atributoName nombre del atributo que se insertara en la columna.
	 */
	public EncabezadoColumna(String titulo, String atributoName)  throws Exception{
		titulo = titulo.trim();
		this.Nombre = notNull(titulo);
		this.atributoName = notNull(atributoName);
	}

	// -------------------------------------- GETTERS AND SETTERS
	// -------------------------------------------//
	/**
	 * @return the Nombre
	 */
	public String getNombre() {
		return notNull(Nombre);
	}

	/**
	 * @param Nombre the Nombre to set
	 */
	public void setNombre(String Nombre) {
		this.Nombre = notNull(Nombre);
	}

	/**
	 * @return the sumar
	 */
	public boolean isSumar() {
		return sumar;
	}

	/**
	 * @param sumar the sumar to set
	 */
	public void setSumar(boolean sumar) {
		this.sumar = sumar;
	}

	/**
	 * @return the atributoName
	 */
	public String getAtributoName() {
		return notNull(atributoName);
	}

	/**
	 * @param atributoName the atributoName to set
	 */
	public void setAtributoName(String atributoName) {
		this.atributoName = notNull(atributoName);
	}

	private String notNull(String var) {
		return var != null ? var.trim() : "";
	}
}
