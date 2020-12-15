/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guatex.sacod_reportgenerator.Objetos;


import java.lang.reflect.Field;
import java.util.LinkedList;
import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import java.util.List;

/**
 *
 * @author DylanYool
 * @since 7 de septiembre, 2020.
 * @see easyreport.objects.Celda
 * @see java.lang.reflect.Field
 * @see java.util.List
 * @see java.util.LinkedList
 */
public class Fila {
	public static final Logger logger = LogManager.getLogger(Fila.class);
	private List<Celda> celdas;
	Field[] atributos;

	public Fila(Object object) throws Exception {
		celdas = new LinkedList<Celda>();
		atributos = object.getClass().getDeclaredFields();
		generarCeldas(object);
	}

	public Fila(JSONObject object)  throws Exception{
		celdas = new LinkedList<Celda>();
		generarCeldas(object);
	}

	private void generarCeldas(JSONObject object) throws Exception {
		JSONArray jsonArray = object.names();
		int length = jsonArray.length();
		try {
			for (int i = 0; i < length; i++) {
				String name = jsonArray.getString(i);
				String value = notNull(object.get(name).toString());
				Celda nuevaCelda = new Celda(name, value);
				celdas.add(nuevaCelda);
			}
		} catch (Exception e) {
			logger.info("algo salio mal generando las celdas desde el Json... err: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void generarCeldas(Object instancia)  throws Exception{
		try {
			for (Field cell : atributos) {
				Celda nuevaCelda = new Celda(cell);
				nuevaCelda.setAtributoValue(instancia);
				celdas.add(nuevaCelda);
			}
		} catch (IllegalArgumentException ex) {
			logger.info(ex);
		} catch (IllegalAccessException ex) {
			logger.info(ex);}
	}

//-------------------------------------------------GETTERS AND SETTERS--------------------------------------------
	/**
	 * @return the celdas
	 */
	public List<Celda> getCeldas() {
		return celdas;
	}

	public String findValue(String atributoName)  throws Exception{
		atributoName = notNull(atributoName);
		String value = "";
		
		for (Celda cel : celdas) {
			if (cel.getAtributoName().equalsIgnoreCase(atributoName)) {
				value = cel.getAtributoValue();
				break;
			}
		}
		return value != null ? value : "";
	}

	public double getToDouble(String atributoName)  throws Exception{
		String value = findValue(notNull(atributoName));
		try {
			double valor = Double.valueOf(notNull(value));
			return valor;
		} catch (Exception e) {
			e.getMessage();
			return 0;
		}

	}

	/**
	 * @param celdas the celdas to set
	 */
	public void setCeldas(List<Celda> celdas) {
		this.celdas = celdas;
	}

	private String notNull(String var) {
		return var != null ? var.trim() : "";
	}

}
