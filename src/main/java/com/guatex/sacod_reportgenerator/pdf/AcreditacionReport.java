package com.guatex.sacod_reportgenerator.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.guatex.sacod_reportgenerator.Rutas;
import com.guatex.sacod_reportgenerator.TableReport;
import com.guatex.sacod_reportgenerator.Objetos.Cliente;
import com.guatex.sacod_reportgenerator.Objetos.EncabezadoColumna;
import com.guatex.sacod_reportgenerator.Objetos.Fila;
import com.guatex.sacod_reportgenerator.Objetos.Tabla;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class AcreditacionReport extends Pdf_Utils {
	private static Document document;
	LocalDateTime date = java.time.LocalDateTime.now();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss");
	public static final Logger logger = LogManager.getLogger(FacturaReport.class);
	TableReport tbl;

	public AcreditacionReport(String nombreArchivo) throws FileNotFoundException, DocumentException, Exception {
		try {
			document = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);
			document.setMargins(10, 10, 20, 10);
			String ruta = Rutas.getRuta(nombreArchivo);
			System.out.println(ruta);
			File file = new File(ruta);
			file.getParentFile().mkdirs();
			PdfWriter.getInstance(document, new FileOutputStream(ruta));
		} catch (Exception e) {
			System.err.println("Algo salio mal creando el archivo... err: " + e.getMessage());
			logger.info("Algo salio mal creando el archivo... err: " + e.getMessage());
		}
	}

	public boolean create(TableReport tbl, TableReport tbAjustes, Cliente cliente, String idACH, String totalisimo)
			throws DocumentException, BadElementException, IOException, Exception {
		try {
			this.tbl = tbl;
			Image logoGuatex = getImage(Rutas.getLogo(), document);
			int width = 94;
			if (tbl.getEncabezados().size() < 8) {
				document.setPageSize(PageSize.LETTER);
				logoGuatex.setAbsolutePosition(10, 710);
			} else {
				width = 90;
				logoGuatex.setAbsolutePosition(10, 510);
			}
			document.open();
			document.add(logoGuatex);
			document.add(new Paragraph(new Chunk(NEWLINE)));
			document.add(new Paragraph(new Chunk(NEWLINE)));
			document.add(new Paragraph(new Chunk(NEWLINE)));
			document.add(getEncabezado(tbl.getTitulo()));
			document.add(new Paragraph(new Chunk(NEWLINE)));
			document.add(getInfo(cliente, idACH));
			document.add(getTablaNoBordes(3, 90, "Detalle de acreditamiento: , , ".split(","), boldFontTitleBig));
			if (tbl != null) {
				document.add(getResumen(tbl, width, totalisimo));
				document.add(createTable(tbl, width));
			}

			if (tbAjustes != null) {
				document.add(getTablaNoBordes(3, 90, "Detalle de ajustes: , , ".split(","), boldFontTitleBig));
				document.add(getResumenAjs(tbAjustes, width));
				document.add(createTableAjs(tbAjustes, width));
			}
			return true;
		} catch (Exception e) {
			System.err.println("algo malio sal... err: " + e.getMessage());
			e.printStackTrace();
			logger.info("Algo salio mal... err: " + e.getMessage());
			return false;
		} finally {
			document.close();
			System.out.println("Cerrando el documento.");
			logger.info("Cerrando el documento ");
		}
	}

	public PdfPTable getResumen(Tabla tbl, int width, String totalisimo) throws Exception {
		float widths[] = { 1, 1, 0.7f, 1, 2, 0.5f, 1, 0.5f, 1, 0.5f, 1 };
		PdfPTable table = new PdfPTable(widths);
		for (EncabezadoColumna ec : tbl.getEncabezados()) {
			PdfPCell cTempEtiqueta = nuevaCelda(ec.getNombre(), 1, ec.isSumar() ? 2 : 1, boldFontNormal);
			cTempEtiqueta.setBorder(1);
			cTempEtiqueta.setBorderWidth(1.35f);
			table.addCell(cTempEtiqueta);
		}
		for (Fila fl : tbl.getFilas()) {
			for (EncabezadoColumna ec : tbl.getEncabezados()) {
				String name = ec.getAtributoName();
				if (tbl.getOperaciones() != null && ec.isSumar()) {
					tbl.getOperaciones().add(name, fl.getToDouble(name));
				}
			}
		}
		table.setWidthPercentage(width);
		table.setPaddingTop(10f);
		table.setSpacingAfter(30f);
		if (tbl.getOperaciones() != null) {
			for (EncabezadoColumna ec : tbl.getEncabezados()) {
				PdfPCell cValor;
				if (ec.isSumar()) {
					cValor = nuevaCelda("Q", 2, 1, normalFontTitleMedium);
					cValor.setBorder(2);
					cValor.setBorderWidth(1.35f);
					table.addCell(cValor);
					String valor = tbl.getOperaciones().getValor(ec.getAtributoName());
					if (ec.getAtributoName().equalsIgnoreCase("valorCred")) {
						cValor = nuevaCelda(numberFormat.format(Double.valueOf(totalisimo)), 2, 1,
								normalFontTitleMedium);
					} else {
						cValor = nuevaCelda(numberFormat.format(Double.valueOf(valor)), 2, 1, normalFontTitleMedium);
					}

					cValor.setBorder(2);
					cValor.setBorderWidth(1.35f);
					table.addCell(cValor);
				} else {
					cValor = nuevaCelda("	", 2, 1, normalFontTitleMedium);
					cValor.setBorder(2);
					cValor.setBorderWidth(1.35f);
					table.addCell(cValor);
				}

			}
		}
		table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		return table;
	}

	public PdfPTable createTable(Tabla tbl, int width) throws Exception {
		float widths[] = { 1, 1, 0.7f, 1, 2, 0.5f, 1, 0.5f, 1, 0.5f, 1 };
		PdfPTable table = new PdfPTable(widths);
		table.setWidthPercentage(width);
		table.setSpacingAfter(20f);
		for (EncabezadoColumna ec : tbl.getEncabezados()) {
			PdfPCell cellValue = nuevaCelda("	", 1, ec.isSumar() ? 2 : 1, normalFontSmall);
			cellValue.setBorder(2);
			table.addCell(cellValue);
		}
		for (Fila fl : tbl.getFilas()) {
			for (EncabezadoColumna ec : tbl.getEncabezados()) {
				String name = ec.getAtributoName();
				String value = fl.findValue(name);
				PdfPCell cellValue;
				if (tbl.getOperaciones() != null && ec.isSumar()) {
					table.addCell(nuevaCelda("Q", 2, 1, normalFontSmall));
					cellValue = new PdfPCell();
					Paragraph dato = new Paragraph(numberFormat.format(Double.valueOf(value)), normalFontSmallTbl);
					dato.setAlignment(Element.ALIGN_RIGHT);
					cellValue.addElement(dato);
					cellValue.setBorder(0);
					table.addCell(cellValue);
				} else {
					cellValue = nuevaCelda(value, Element.ALIGN_CENTER, normalFontSmall);
					table.addCell(cellValue);
				}
			}
		}
		for (EncabezadoColumna ec : tbl.getEncabezados()) {
			PdfPCell cellValue = nuevaCelda("	", 1, ec.isSumar() ? 2 : 1, normalFontSmallTbl);
			cellValue.setBorder(1);
			table.addCell(cellValue);
		}
		return table;
	}

	public PdfPTable getResumenAjs(Tabla tbl, int width) throws Exception {
		float widths[] = { 1, 1, 0.7f, 1 };
		PdfPTable table = new PdfPTable(widths);
		for (EncabezadoColumna ec : tbl.getEncabezados()) {
			PdfPCell cTempEtiqueta = nuevaCelda(ec.getNombre(), 1, ec.isSumar() ? 2 : 1, boldFontNormal);
			cTempEtiqueta.setBorder(1);
			cTempEtiqueta.setBorderWidth(1.35f);
			table.addCell(cTempEtiqueta);
		}
		for (Fila fl : tbl.getFilas()) {
			for (EncabezadoColumna ec : tbl.getEncabezados()) {
				String name = ec.getAtributoName();
				if (tbl.getOperaciones() != null && ec.isSumar()) {
					tbl.getOperaciones().add(name, fl.getToDouble(name));
				}
			}
		}
		table.setWidthPercentage(60);
		table.setPaddingTop(10f);
		table.setSpacingAfter(30f);
		if (tbl.getOperaciones() != null) {
			for (EncabezadoColumna ec : tbl.getEncabezados()) {
				PdfPCell cValor;
				if (ec.isSumar()) {
					cValor = nuevaCelda("Q", 2, 1, normalFontTitleMedium);
					cValor.setBorder(2);
					cValor.setBorderWidth(1.35f);
					table.addCell(cValor);
					String valor = tbl.getOperaciones().getValor(ec.getAtributoName());
					cValor = nuevaCelda("", 2, 1, normalFontTitleMedium);
					cValor.setBorder(2);
					cValor.setBorderWidth(1.35f);
					table.addCell(cValor);
				} else {
					cValor = nuevaCelda("	", 2, 1, normalFontTitleMedium);
					cValor.setBorder(2);
					cValor.setBorderWidth(1.35f);
					table.addCell(cValor);
				}

			}
		}
		table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		return table;
	}

	public PdfPTable createTableAjs(Tabla tbl, int width) throws Exception {
		float widths[] = { 1, 1, 0.7f, 1 };
		PdfPTable table = new PdfPTable(widths);
		table.setWidthPercentage(60);
		table.setSpacingAfter(20f);
		for (EncabezadoColumna ec : tbl.getEncabezados()) {
			PdfPCell cellValue = nuevaCelda("	", 1, ec.isSumar() ? 2 : 1, normalFontSmall);
			cellValue.setBorder(2);
			table.addCell(cellValue);
		}
		for (Fila fl : tbl.getFilas()) {
			for (EncabezadoColumna ec : tbl.getEncabezados()) {
				String name = ec.getAtributoName();
				String value = fl.findValue(name);
				PdfPCell cellValue;
				if (tbl.getOperaciones() != null && ec.isSumar()) {
					table.addCell(nuevaCelda("Q", 2, 1, normalFontSmall));
					cellValue = new PdfPCell();
					Paragraph dato = new Paragraph(numberFormat.format(Double.valueOf(value)), normalFontSmallTbl);
					dato.setAlignment(Element.ALIGN_RIGHT);
					cellValue.addElement(dato);
					cellValue.setBorder(0);
					table.addCell(cellValue);
				} else {
					cellValue = nuevaCelda(value, Element.ALIGN_CENTER, normalFontSmall);
					table.addCell(cellValue);
				}
			}
		}
		for (EncabezadoColumna ec : tbl.getEncabezados()) {
			PdfPCell cellValue = nuevaCelda("	", 1, ec.isSumar() ? 2 : 1, normalFontSmallTbl);
			cellValue.setBorder(1);
			table.addCell(cellValue);
		}
		return table;
	}

	public PdfPTable getInfo(Cliente cliente, String idACH) throws Exception {

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(94);
		table.setSpacingAfter(14f);

		PdfPCell cSpace = nuevaCelda("			", Element.ALIGN_CENTER, 2, normalFontTitleMedium);
		PdfPCell cInfo = nuevaCelda("INFORMACIÓN BANCARIA", Element.ALIGN_CENTER, 2, normalFontTitleMedium);
		PdfPCell cClient = nuevaCelda("CLIENTE: " + cliente.getNombre(), 0, 2, normalFontTitleMedium);
		PdfPCell cCodigo = nuevaCelda("CÓDIGO: " + cliente.getCodigo(), 0, 2, normalFontTitleMedium);
		PdfPCell cDireccion = nuevaCelda("DIRECCIÓN: " + cliente.getDireccion(), 0, 2, normalFontTitleMedium);
		PdfPCell cBanco = nuevaCelda("BANCO: " + cliente.getBanco(), 1, 2, normalFontTitleMedium);
		PdfPCell cNoCuenta = nuevaCelda("CUENTA: " + cliente.getNoCuenta(), 1, 2, normalFontTitleMedium);
		PdfPCell cTipoCuenta = nuevaCelda("TIPO: " + cliente.getTipoCuenta(), 1, 2, normalFontTitleMedium);

		cBanco.setBorder(1);
		cBanco.setBorderWidth(1.35f);
		table.addCell(cSpace);
		table.addCell(cSpace);
		table.addCell(cInfo);
		cInfo = nuevaCelda("ACREDITAMIENTO #: " + idACH, Element.ALIGN_CENTER, 2, normalFontTitleMedium);
		cInfo.setBorder(2);
		cInfo.setBorderWidth(1.35f);
		table.addCell(cClient);
		table.addCell(cSpace);
		table.addCell(cBanco);
		table.addCell(cCodigo);
		table.addCell(cSpace);
		table.addCell(cTipoCuenta);
		table.addCell(cDireccion);
		table.addCell(cSpace);
		table.addCell(cNoCuenta);
		table.addCell(cSpace);
		table.addCell(cSpace);
		table.addCell(cInfo);
		return table;
	}

	public PdfPTable getEncabezado(String title) throws Exception {
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(94);
		table.setSpacingAfter(10f);
		PdfPCell cEncabezado = nuevaCelda("Transporte, Empaque y Almacenaje, S.A.", 0, 2, normalFontTitleBig);
		PdfPCell cFecha = nuevaCelda("Emision: " + titleFechora.replace(" ", " Hora: "), 2, 2, normalFontSmall);
		PdfPCell cBlank = nuevaCelda("		", 1, 2, normalFontTitleMedium);
		PdfPCell cTitle = nuevaCelda(title, 0, 2, normalFontTitleBig);
		table.addCell(cEncabezado);
		table.addCell(cFecha);
		table.addCell(cTitle);
		table.addCell(cBlank);
		return table;
	}

}
