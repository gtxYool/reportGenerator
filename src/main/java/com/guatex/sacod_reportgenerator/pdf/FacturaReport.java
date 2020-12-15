/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guatex.sacod_reportgenerator.pdf;
import com.itextpdf.text.BadElementException;
import com.guatex.sacod_reportgenerator.Objetos.*;
import com.guatex.sacod_reportgenerator.TableReport;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.guatex.sacod_reportgenerator.Rutas;

/**
 *
 * @author AHERNANDEZ
 */
public class FacturaReport extends Pdf_Utils {

	private static Document document;
	LocalDateTime date = java.time.LocalDateTime.now();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss");
	public static final Logger logger = LogManager.getLogger(FacturaReport.class);
	TableReport tbl;

	public FacturaReport(String nombreArchivo) throws FileNotFoundException, DocumentException, Exception {
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

	public boolean create(TableReport tbl, Cliente cliente, String nofact)
			throws DocumentException, BadElementException, IOException, Exception {
		try {
			this.tbl = tbl;
			Image logoGuatex = getImage(Rutas.getLogo(), document);

			int width = 80;
			if (tbl.getEncabezados().size() < 7) {
				document.setPageSize(PageSize.LETTER);
				logoGuatex.setAbsolutePosition(10, 710);
			} else {
				width = 90;
				logoGuatex.setAbsolutePosition(10, 510);
			}
			document.open();
			document.add(logoGuatex);
			document.add(newSimpleParagraph(NEWLINE));
			document.add(newSimpleParagraph(NEWLINE));
			document.add(newSimpleParagraph(NEWLINE));
			document.add(getEncabezado(tbl.getTitulo()));
			document.add(new Paragraph(new Chunk(NEWLINE)));
			document.add(getInfo(cliente));
			document.add(getTablaNoBordes(3, 80,
					("Factura: " + nofact + ", , ,Detalle de facturación: , , ").split(","), boldFontTitleMedium));
			if (tbl != null) {
				document.add(getResumen(tbl, width));
				document.add(createTable(tbl, width));
				if (tbl.getOperaciones() != null && tbl.getOperaciones().getCampos() != null
						&& tbl.getOperaciones().getCampos().length > 0) {
					document.add(new Paragraph(new Chunk(NEWLINE)));
					document.add(new Paragraph(new Chunk(NEWLINE)));
				}
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

	public PdfPTable getResumen(Tabla tbl, int width) throws Exception {
		float widths[] = { 1, 1, 0.5f, 1, 0.5f, 0.65f, 0.5f, 1 };
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
				PdfPCell cValor = new PdfPCell();
				if (ec.isSumar()) {
					String valor = tbl.getOperaciones().getValor(ec.getAtributoName());
					cValor = nuevaCelda("Q", 2, 1, normalFontTitleMedium);
					cValor.setBorder(2);
					cValor.setBorderWidth(1.35f);
					table.addCell(cValor);
					cValor = nuevaCelda(numberFormat.format(Double.valueOf(valor)), 2, 1, normalFontTitleMedium);
					cValor.setBorder(2);
					cValor.setBorderWidth(1.35f);
					table.addCell(cValor);
				} else {
					cValor.addElement(new Phrase(" ", normalFontTitleMedium));
					cValor.setBorder(2);
					cValor.setBorderWidth(1.35f);
					table.addCell(cValor);
				}
			}
		}
		return table;
	}

	public PdfPTable createTable(Tabla tbl, int width) throws Exception {
		float widths[] = { 1, 1, 0.5f, 1, 0.5f, 0.65f, 0.5f, 1 };
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
				System.out.println("|" + name + "|");
				String value = fl.findValue(name);
				PdfPCell cellValue;
				if (tbl.getOperaciones() != null && ec.isSumar()) {
					table.addCell(nuevaCelda("Q", 2, 1, normalFontSmallTbl));
					cellValue = nuevaCelda(numberFormat.format(Double.valueOf(value)), 2, normalFontSmallTbl);
					table.addCell(cellValue);
				} else {
					cellValue = nuevaCelda(value, Element.ALIGN_CENTER, normalFontSmallTbl);
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

	public PdfPTable getInfo(Cliente cliente) throws Exception {
		float widths[] = { 3, 1 };
		PdfPTable table = new PdfPTable(widths);
		table.setWidthPercentage(95);
		table.setSpacingAfter(20f);
		PdfPCell cFecha = nuevaCelda("Emision: " + titleFechora.replace(" ", " Hora: "), 2, 1, normalFontSmall);
		PdfPCell cSpace = nuevaCelda("			", 1, 1, normalFontSmall);
		PdfPCell cClient = nuevaCelda("CLIENTE: " + cliente.getNombre(), 0, 1, normalFontTitleMedium);
		PdfPCell cCodigo = nuevaCelda("CÓDIGO: " + cliente.getCodigo(), 0, 1, normalFontTitleMedium);
		PdfPCell cDireccion = nuevaCelda("DIRECCIÓN: " + cliente.getDireccion(), 0, 1, normalFontTitleMedium);

		table.addCell(cClient);
		table.addCell(cFecha);
		table.addCell(cCodigo);
		table.addCell(cSpace);
		table.addCell(cDireccion);
		table.addCell(cSpace);
		return table;
	}

	public PdfPTable getEncabezado(String title) throws Exception {
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(95);
		table.setSpacingAfter(14f);
		PdfPCell cEncabezado = nuevaCelda("Transporte, Empaque y Almacenaje, S.A.", 0, 1, normalFontTitleBig);
		PdfPCell cBlank = nuevaCelda("		", 1, 1, normalFontTitleMedium);
		PdfPCell cTitle = nuevaCelda(title, 0, 2, normalFontTitleBig);
		table.addCell(cEncabezado);
		table.addCell(cBlank);
		table.addCell(cTitle);
		return table;
	}

}
