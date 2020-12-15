/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guatex.sacod_reportgenerator.Objetos;

import java.lang.reflect.Field;



/**
 *
 * @author DylanYool
 * @since 7 de septiembre, 2020.
 * @see java.lang.reflect.Field
 */
public class Celda {

    private Field atributo;
    private String atributoName;
    private String atributoValue;

    public Celda(Field atributo)  throws Exception{
        this.atributo = atributo;
        this.atributoName = notNull(atributo.getName());
        this.atributo.setAccessible(true);
    }

    public Celda(String name, String value)  throws Exception{
        this.atributoName = notNull(name);
        this.atributoValue = notNull(value);
    }

    /**
     * @return the atributoName
     */
    public String getAtributoName() {
        return atributoName;
    }

    /**
     * @param atributoName the atributoName to set
     */
    public void setAtributoName(String atributoName) {
        this.atributoName = notNull(atributoName);
    }

    /**
     * @return the atributoValue
     */
    public String getAtributoValue() {
        return notNull(atributoValue);
    }

    /**
     *
     * @param instance instancia del objeto a mapear
     * @throws IllegalArgumentException Error al leer el atributo
     * @throws IllegalAccessException Error al acceder al atributo
     */
    public void setAtributoValue(Object instance) throws IllegalArgumentException, IllegalAccessException {
        this.atributoValue = notNull(this.atributo.get(instance).toString());
    }

    /**
     * @return the atributo
     */
    public Field getAtributo() {
        return atributo;
    }

    /**
     * @param atributo the atributo to set
     */
    public void setAtributo(Field atributo) {
        this.atributo = atributo;
    }

	private String notNull(String var) {
		return var != null ? var.trim() : "";
	}

}
