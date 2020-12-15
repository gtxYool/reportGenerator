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
public class Operation {

	private String[] campos;
	boolean suma;
	private double[] totales;

	public Operation() {
	}

	public Operation(String[] campos) throws Exception {
		setCampos(campos);
		totales = new double[campos.length];
	}

	public void add(String campo, double valor) throws Exception {
		campo = notNull(campo);
		int index = getIndex(campo);
		if (index >= 0) {
			getTotales()[index] += valor;
		}
	}

	public String getValor(String campo) throws Exception {
		campo = notNull(campo);
		int index = getIndex(campo);
		if (index >= 0) {
			return "" + getTotales()[index];
		}
		return "0";
	}

	private int getIndex(String campo) throws Exception {
		campo = notNull(campo);
		for (int i = 0; i < getCampos().length; i++) {
			if (getCampos()[i].trim().equalsIgnoreCase(campo)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * @return the campos
	 */
	public String[] getCampos() {
		return campos;
	}

	/**
	 * @return the totales
	 */
	public double[] getTotales() {
		return totales;
	}

	/**
	 * @param campos the campos to set
	 */
	public void setCampos(String[] campos) {
		this.campos = campos;
	}

	/**
	 * @param totales the totales to set
	 */
	public void setTotales(double[] totales) {
		this.totales = totales;
	}

	private String notNull(String var) {
		return var != null ? var.trim() : "";
	}
}
