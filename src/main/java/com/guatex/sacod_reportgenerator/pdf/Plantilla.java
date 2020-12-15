/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guatex.sacod_reportgenerator.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.guatex.sacod_reportgenerator.Objetos.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.NumberFormat;

/**
 *
 * @author AHERNANDEZ
 */
public class Plantilla {

	public static final Logger logger = LogManager.getLogger(Plantilla.class);
	LocalDateTime date = java.time.LocalDateTime.now();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final String fechora = dtf.format(date).toString().trim();
    private final String titleFechora = dtf2.format(date).toString().trim();
	private final Font normalFontSmall = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 8, Font.ITALIC);
	private final Font boldFontNormal = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 5, Font.BOLD);
	private final Font normalFontTitle = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 4, Font.BOLDITALIC);
	private final Font boldFontTitle = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 7, Font.BOLD, BaseColor.BLACK);
	private final Font boldFontTitleBig = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 15, Font.BOLD);
	private final Font normalFontTitleBig = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 15, Font.NORMAL);
	private final Font normalFontTitleMedium = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 6, Font.BOLD);
	protected final String NEWLINE = "\n";
	protected final String BLANK_SPACE = "\n\n";
	private int numcol = 0;
	protected int indent = 15;
	NumberFormat nf = NumberFormat.getInstance();

	public Plantilla() {
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
	}

	public PdfPTable createTable(Tabla tbl, int width) throws Exception {

		PdfPTable table = new PdfPTable(tbl.getEncabezados().size());
		table.setWidthPercentage(width);
		table.setSpacingAfter(20f);
		float fntSize, lineSpacing;
		fntSize = 7f;
		lineSpacing = 8f;
		int i = 1;
		numcol = tbl.getEncabezados().size();
		for (EncabezadoColumna ec : tbl.getEncabezados()) {
			PdfPCell cellValue = new PdfPCell();
			cellValue.setBorder(2);
			cellValue.addElement(new Phrase(" ", FontFactory.getFont(FontFactory.COURIER, fntSize)));
			table.addCell(cellValue);
		}
		for (Fila fl : tbl.getFilas()) {
			for (EncabezadoColumna ec : tbl.getEncabezados()) {
				String name = ec.getAtributoName();
				String value = fl.findValue(name);
				PdfPCell cellValue = new PdfPCell();
				cellValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
				Paragraph dato;
				cellValue.setBorder(0);
				if (tbl.getOperaciones() != null && ec.isSumar()) {
					dato = new Paragraph("Q " + nf.format(Double.valueOf(value)),
							FontFactory.getFont(FontFactory.COURIER, fntSize));
					dato.setAlignment(Element.ALIGN_RIGHT);
					dato.setIndentationRight(width > 70 ? 260 / numcol : 90 / numcol);
					cellValue.addElement(dato);
					table.addCell(cellValue);
				} else {
					dato = new Paragraph(new Phrase(value, FontFactory.getFont(FontFactory.COURIER, fntSize)));
					dato.setAlignment(Element.ALIGN_CENTER);
					cellValue.addElement(dato);
					table.addCell(cellValue);
				}
			}
		}
		for (EncabezadoColumna ec : tbl.getEncabezados()) {
			PdfPCell cellValue = new PdfPCell();
			cellValue.setBorder(1);
			cellValue.addElement(new Phrase(" ", FontFactory.getFont(FontFactory.COURIER, fntSize)));
			table.addCell(cellValue);
		}
		return table;
	}

	public PdfPTable getInfo(Cliente cliente) throws Exception {
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(90);

		table.setSpacingAfter(30f);
		PdfPCell cSpace = new PdfPCell();
		cSpace.setBorder(0);
		cSpace.setHorizontalAlignment(Element.ALIGN_CENTER);
		cSpace.setColspan(1);
		cSpace.addElement(new Chunk("			", normalFontSmall));
		PdfPCell cClient = new PdfPCell();
		cClient.setBorder(1);
		cClient.setHorizontalAlignment(Element.ALIGN_CENTER);
		cClient.setColspan(2);
		cClient.addElement(new Chunk("Cliente: " + cliente.getNombre(), normalFontSmall));
		PdfPCell cCodigo = new PdfPCell();
		cCodigo.setBorder(0);
		cCodigo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cCodigo.setColspan(2);
		cCodigo.addElement(new Chunk("Código: " + cliente.getCodigo(), normalFontSmall));
		PdfPCell cDireccion = new PdfPCell();
		cDireccion.setBorder(2);
		cDireccion.setHorizontalAlignment(Element.ALIGN_CENTER);
		cDireccion.setColspan(2);
		cDireccion.addElement(new Chunk("Dirección: " + cliente.getDireccion(), normalFontSmall));
		PdfPCell cBanco = new PdfPCell();
		cBanco.setBorder(1);
		cBanco.setHorizontalAlignment(Element.ALIGN_CENTER);
		cBanco.setColspan(2);
		cBanco.addElement(new Chunk("Banco: " + cliente.getBanco(), normalFontSmall));
		PdfPCell cNoCuenta = new PdfPCell();
		cNoCuenta.setBorder(2);
		cNoCuenta.setHorizontalAlignment(Element.ALIGN_CENTER);
		cNoCuenta.setColspan(2);
		cNoCuenta.addElement(new Chunk("Cuenta: " + cliente.getNoCuenta(), normalFontSmall));
		PdfPCell cTipoCuenta = new PdfPCell();
		cTipoCuenta.setBorder(0);
		cTipoCuenta.setHorizontalAlignment(Element.ALIGN_CENTER);
		cTipoCuenta.setColspan(2);
		cTipoCuenta.addElement(new Chunk("Tipo: " + cliente.getTipoCuenta(), normalFontSmall));
		table.addCell(cClient);
		table.addCell(cSpace);
		table.addCell(cBanco);
		table.addCell(cCodigo);
		table.addCell(cSpace);
		table.addCell(cTipoCuenta);
		table.addCell(cDireccion);
		table.addCell(cSpace);
		table.addCell(cNoCuenta);
		return table;
	}

	public PdfPTable getEncabezado() throws Exception {
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(99);
		table.setSpacingAfter(30f);
		PdfPCell cEncabezado = new PdfPCell();
		cEncabezado.setBorder(0);
		cEncabezado.setHorizontalAlignment(Element.ALIGN_CENTER);
		cEncabezado.setColspan(2);
		cEncabezado.addElement(new Chunk("Transporte, Empaque y Almacenaje, S.A.", normalFontTitleMedium));
		PdfPCell cFecha = new PdfPCell();
		cFecha.setBorder(0);
		cFecha.setHorizontalAlignment(Element.ALIGN_CENTER);
		cFecha.setColspan(2);
		cFecha.addElement(new Chunk("Fecha: " + titleFechora.replace(" ", " Hora: "), normalFontTitleMedium));
		table.addCell(cEncabezado);
		table.addCell(cFecha);
		return table;
	}

	public PdfPTable getResumen(Tabla tbl, int width) throws Exception {
		PdfPTable table = new PdfPTable(tbl.getEncabezados().size());
		for (EncabezadoColumna ec : tbl.getEncabezados()) {
			PdfPCell cTempEtiqueta = new PdfPCell();
			cTempEtiqueta.setBorder(1);
			Paragraph dato = new Paragraph(new Phrase(ec.getNombre(), boldFontTitle));
			dato.setAlignment(Element.ALIGN_CENTER);
			cTempEtiqueta.addElement(dato);
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
		table.setSpacingAfter(10f);
		if (tbl.getOperaciones() != null) {
			for (EncabezadoColumna ec : tbl.getEncabezados()) {
				PdfPCell cValor = new PdfPCell();
				cValor.setBorder(2);
				if (ec.isSumar()) {
					String valor = tbl.getOperaciones().getValor(ec.getAtributoName());
					Paragraph dato = new Paragraph(new Phrase("Q " + nf.format(Double.valueOf(valor)), FontFactory.getFont(FontFactory.COURIER, 7f)));
                    dato.setAlignment(Element.ALIGN_CENTER);
                    cValor.addElement(dato);
					table.addCell(cValor);
				} else {
					cValor.addElement(new Phrase(" ", FontFactory.getFont(FontFactory.COURIER, 7f)));
					table.addCell(cValor);
				}
			}
		}
		table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		return table;
	}
}
