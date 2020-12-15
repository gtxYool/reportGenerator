/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guatex.sacod_reportgenerator;

import com.guatex.sacod_reportgenerator.Objetos.Tabla;
import java.util.List;

/**
 *
 * @author DylanYool
 * @since 7 de septiembre, 2020.
 */
public class TableReport extends Tabla {
	private String titulo;
	private String subTitulo;
	private String Descripcion;

	// ----------------------------- METODOS -----------------------------------//
	// ------------------------------CONTRUCTORES------------------------------//
	/**
	 * Crea un objeto TableReport
	 *
	 * @param objetos listado de objetos a reportar.
	 * @param titulo  titulo que se mostrará al inicio del reporte.
	 *
	 */
	public TableReport(List objetos, String titulo) throws Exception {
		super(objetos);
		this.titulo = titulo;
		this.subTitulo = "";
		this.Descripcion = "";
	}

	/**
	 * Crea un objeto TableReport
	 *
	 * @param JsonObjects json de objetos a reportar.
	 * @param titulo      titulo que se mostrará al inicio del reporte.
	 *
	 */
//    public TableReport(String JsonObjects, String titulo) {
//        this.tabla = new Tabla(JsonObjects);
//        this.titulo = titulo;
//        this.subTitulo = "";
//        this.Descripcion = "";
//    }
	/**
	 * Crea un objeto TableReport
	 *
	 * @param encabezado listado de nombres a mostrar en las columnas (si el objeto
	 *                   no tiene un atributo con ese nombre se quedará vacia la
	 *                   celda al momento de llenar la tabla.)
	 * @param objetos    listado de objetos a reportar.
	 * @param titulo     titulo que se mostrará al inicio del reporte.
	 *
	 */
	public TableReport(String[] encabezado, List objetos, String titulo) throws Exception {
		super(encabezado, objetos);
		this.titulo = titulo;
		this.subTitulo = "";
		this.Descripcion = "";
	}

	/**
	 *
	 * Crea un objeto TableReport
	 *
	 * @param encabezado listado de nombres a mostrar en las columnas (si el objeto
	 *                   no tiene un atributo con ese nombre se quedará vacia la
	 *                   celda al momento de llenar la tabla.)
	 * @param objetos    listado de objetos a reportar.
	 * @param titulo     titulo que se mostrará al inicio del reporte.
	 *
	 */
	public TableReport(List<String> encabezado, List objetos, String titulo) throws Exception {
		super(encabezado, objetos);
		this.titulo = titulo;
		this.subTitulo = "";
		this.Descripcion = "";
	}

	/**
	 *
	 * Crea un objeto TableReport
	 *
	 * @param encabezado listado de nombres a mostrar en las columnas.
	 * @param atributos  listado de atributos asociados a dichas columnas(si el
	 *                   objeto no cuenta con atributo con dicho nombre quedará
	 *                   vacia la celda al momento de llenar la tabla)
	 * @param objetos    listado de objetos a reportar.
	 * @param titulo     titulo que se mostrará al inicio del reporte.
	 *
	 */
	public TableReport(String[] encabezado, String[] atributos, List objetos, String titulo) throws Exception {
		super(encabezado, atributos, objetos);
		this.titulo = titulo;
		this.subTitulo = "";
		this.Descripcion = "";
	}

	/**
	 *
	 * Crea un objeto TableReport
	 *
	 * @param encabezado listado de nombres a mostrar en las columnas.
	 * @param atributos  listado de atributos asociados a dichas columnas(si el
	 *                   objeto no cuenta con atributo con dicho nombre quedará
	 *                   vacia la celda al momento de llenar la tabla)
	 * @param objetos    listado de objetos a reportar.
	 * @param titulo     titulo que se mostrará al inicio del reporte.
	 *
	 */
	public TableReport(String[] encabezado, String[] atributos, String JsonObjects, String titulo) throws Exception {
		super(encabezado, atributos, JsonObjects);
		this.titulo = titulo;
		this.subTitulo = "";
		this.Descripcion = "";
	}

	/**
	 *
	 * Crea un objeto TableReport
	 *
	 * @param encabezado listado de nombres a mostrar en las columnas.
	 * @param atributos  listado de atributos asociados a dichas columnas(si el
	 *                   objeto no cuenta con atributo con dicho nombre quedará
	 *                   vacia la celda al momento de llenar la tabla)
	 * @param objetos    listado de objetos a reportar.
	 * @param titulo     titulo que se mostrará al inicio del reporte.
	 *
	 */
	public TableReport(List<String> encabezado, List<String> atributos, List objetos, String titulo) throws Exception {
		super(encabezado, atributos, objetos);
		this.titulo = titulo;

		this.subTitulo = "";
		this.Descripcion = "";
	}

	/**
	 * Crea un objeto TableReport
	 *
	 * @param objetos     listado de objetos a reportar.
	 * @param titulo      titulo que se mostrará al inicio del reporte.
	 * @param subTitulo   subtitulo del reporte
	 *
	 * @param Descripcion Descripción del reporte
	 */
	public TableReport(List objetos, String titulo, String subTitulo, String Descripcion) throws Exception {
		super(objetos);
		this.titulo = titulo;
		this.subTitulo = subTitulo;
		this.Descripcion = Descripcion;
	}

	/**
	 * Crea un objeto TableReport
	 *
	 * @param encabezado  listado de nombres a mostrar en las columnas.
	 * @param objetos     listado de objetos a reportar.
	 * @param titulo      titulo que se mostrará al inicio del reporte.
	 * @param subTitulo   subtitulo del reporte
	 *
	 * @param Descripcion Descripción del reporte
	 */
	public TableReport(String[] encabezado, List objetos, String titulo, String subTitulo, String Descripcion)
			throws Exception {
		super(encabezado, objetos);
		this.titulo = titulo;
		this.subTitulo = subTitulo;
		this.Descripcion = Descripcion;
	}

	/**
	 * Crea un objeto TableReport
	 *
	 * @param encabezado  listado de nombres a mostrar en las columnas.
	 * @param objetos     listado de objetos a reportar.
	 * @param titulo      titulo que se mostrará al inicio del reporte.
	 * @param subTitulo   subtitulo del reporte
	 *
	 * @param Descripcion Descripción del reporte
	 */
	public TableReport(List<String> encabezado, List objetos, String titulo, String subTitulo, String Descripcion)
			throws Exception {
		super(encabezado, objetos);
		this.titulo = titulo;
		this.subTitulo = subTitulo;
		this.Descripcion = Descripcion;
	}

	/**
	 * Crea un objeto TableReport
	 *
	 * @param encabezado  listado de nombres a mostrar en las columnas.
	 * @param atributos   listado de atributos asociados a dichas columnas(si el
	 *                    objeto no cuenta con atributo con dicho nombre quedará
	 *                    vacia la celda al momento de llenar la tabla)
	 * @param objetos     listado de objetos a reportar.
	 * @param titulo      titulo que se mostrará al inicio del reporte.
	 * @param subTitulo   subtitulo del reporte
	 *
	 * @param Descripcion Descripción del reporte
	 */
	public TableReport(String[] encabezado, String[] atributos, List objetos, String titulo, String subTitulo,
			String Descripcion) throws Exception {
		super(encabezado, atributos, objetos);
		this.titulo = titulo;
		this.subTitulo = subTitulo;
		this.Descripcion = Descripcion;
	}

	/**
	 * Crea un objeto TableReport
	 *
	 * @param encabezado  listado de nombres a mostrar en las columnas.
	 * @param atributos   listado de atributos asociados a dichas columnas(si el
	 *                    objeto no cuenta con atributo con dicho nombre quedará
	 *                    vacia la celda al momento de llenar la tabla)
	 * @param JsonObject  JSON con los objetos a reportar.
	 * @param titulo      titulo que se mostrará al inicio del reporte.
	 * @param subTitulo   subtitulo del reporte
	 *
	 * @param Descripcion Descripción del reporte
	 */
	public TableReport(String[] encabezado, String[] atributos, String JsonObject, String titulo, String subTitulo,
			String Descripcion) throws Exception {
		super(encabezado, atributos, JsonObject);
		this.titulo = titulo;
		this.subTitulo = subTitulo;
		this.Descripcion = Descripcion;
	}

	/**
	 *
	 * Crea un objeto TableReport
	 *
	 * @param encabezado  listado de nombres a mostrar en las columnas.
	 * @param atributos   listado de atributos asociados a dichas columnas(si el
	 *                    objeto no cuenta con atributo con dicho nombre quedará
	 *                    vacia la celda al momento de llenar la tabla)
	 * @param objetos     listado de objetos a reportar.
	 * @param titulo      titulo que se mostrará al inicio del reporte.
	 * @param subTitulo   subtitulo del reporte
	 *
	 * @param Descripcion Descripción del reporte
	 */
	public TableReport(List<String> encabezado, List<String> atributos, List objetos, String titulo, String subTitulo,
			String Descripcion) throws Exception {
		super(encabezado, atributos, objetos);
		this.titulo = titulo;
		this.subTitulo = subTitulo;
		this.Descripcion = Descripcion;
	}

	/**
	 * Crea un objeto TableReport
	 *
	 * @param encabezado  listado de nombres a mostrar en las columnas(si el objeto
	 *                    no cuenta con atributo con dicho nombre quedará vacia la
	 *                    celda al momento de llenar la tabla).
	 * @param objetos     listado de objetos a reportar.
	 * @param titulo      titulo que se mostrará al inicio del reporte.
	 *
	 * @param Descripcion Descripción del reporte
	 */
	public TableReport(String[] encabezado, List objetos, String titulo, String Descripcion) throws Exception {
		super(encabezado, objetos);
		this.titulo = titulo;
		this.subTitulo = "";
		this.Descripcion = Descripcion;
	}

	/**
	 * Crea un objeto TableReport
	 *
	 * @param encabezado  listado de nombres a mostrar en las columnas(si el objeto
	 *                    no cuenta con atributo con dicho nombre quedará vacia la
	 *                    celda al momento de llenar la tabla).
	 * @param objetos     listado de objetos a reportar.
	 * @param titulo      titulo que se mostrará al inicio del reporte.
	 *
	 * @param Descripcion Descripción del reporte
	 */
	public TableReport(List<String> encabezado, List objetos, String titulo, String Descripcion) throws Exception {
		super(encabezado, objetos);
		this.titulo = titulo;
		this.subTitulo = "";
		this.Descripcion = Descripcion;
	}

	/**
	 *
	 * Crea un objeto TableReport
	 *
	 * @param encabezado  listado de nombres a mostrar en las columnas.
	 * @param atributos   listado de atributos asociados a dichas columnas(si el
	 *                    objeto no cuenta con atributo con dicho nombre quedará
	 *                    vacia la celda al momento de llenar la tabla)
	 * @param objetos     listado de objetos a reportar.
	 * @param titulo      titulo que se mostrará al inicio del reporte.
	 *
	 * @param Descripcion Descripción del reporte
	 */
	public TableReport(String[] encabezado, String[] atributos, List objetos, String titulo, String Descripcion)
			throws Exception {
		super(encabezado, atributos, objetos);
		this.titulo = titulo;
		this.subTitulo = "";
		this.Descripcion = Descripcion;
	}

	/**
	 *
	 * Crea un objeto TableReport
	 *
	 * @param encabezado  listado de nombres a mostrar en las columnas.
	 * @param atributos   listado de atributos asociados a dichas columnas(si el
	 *                    objeto no cuenta con atributo con dicho nombre quedará
	 *                    vacia la celda al momento de llenar la tabla)
	 * @param objetos     listado de objetos a reportar.
	 * @param titulo      titulo que se mostrará al inicio del reporte.
	 *
	 * @param Descripcion Descripción del reporte
	 */
	public TableReport(List<String> encabezado, List<String> atributos, List objetos, String titulo, String Descripcion)
			throws Exception {
		super(encabezado, atributos, objetos);
		this.titulo = titulo;
		this.subTitulo = "";
		this.Descripcion = Descripcion;
	}

	// ------------------------------------------------GETTERS AND
	// SETTERS----------------------------------------------//
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the subTitulo
	 */
	public String getSubTitulo() {
		return subTitulo;
	}

	/**
	 * @param subTitulo the subTitulo to set
	 */
	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}

	/**
	 * @return the Descripcion
	 */
	public String getDescripcion() {
		return Descripcion;
	}

	/**
	 * @param Descripcion the Descripcion to set
	 */
	public void setDescripcion(String Descripcion) {
		this.Descripcion = Descripcion;
	}

}
