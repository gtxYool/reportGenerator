package com.guatex.sacod_reportgenerator;

public class Rutas {
	private static final String OS = System.getProperty("os.name").toLowerCase();
	private static final String PATHLinux = "/var/lib/sacod_reportgenerator/SACOD_reportes";
//	private static final String PATHWin = "C:\\TEMPORAL\\ReportesSACOD";
	private static final String PATHWin = "\\\\192.168.11.114\\Datos David\\Karla";

	private static final String logoLinux="/var/lib/sacod_reportgenerator/Guatex2.jpg";
	private static final String logoWin="C:\\TEMPORAL\\imagenes\\Guatex2.jpg";
	
	private static boolean isWindows() {
		System.out.println("hola, soy Windows...");
		return OS.contains("win");
	}

	/**
	 * @return the pATHWin
	 */
	public static String getPATH() {
		return isWindows()?PATHWin:PATHLinux;
	}

	/**
	 * @return the logoWin
	 */
	public static String getLogo() {
		return isWindows()?logoWin:logoLinux;
	}

	/**
	 * @return the ruta
	 */
	public static String getRuta(String nombreArchivo) {
		if(isWindows()) {
			return PATHWin+"\\"+nombreArchivo+".pdf";	
		}else {
			return PATHLinux+"/"+nombreArchivo+".pdf";
		}
		
	}

}
