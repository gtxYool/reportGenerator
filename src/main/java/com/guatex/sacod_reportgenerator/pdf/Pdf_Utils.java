package com.guatex.sacod_reportgenerator.pdf;

import com.itextpdf.text.BadElementException;
import java.time.format.DateTimeFormatter;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.IOException;
import java.time.LocalDateTime;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pdf_Utils {
	protected LocalDateTime date = java.time.LocalDateTime.now();
	protected DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	protected DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	protected final String fechora = dtf.format(date).toString().trim();
	protected final String titleFechora = dtf2.format(date).toString().trim();
	protected final Font normalFontSmall = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 8, Font.NORMAL);
	protected final Font normalFontSmallTbl = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 9, Font.NORMAL);
	protected final Font boldFontNormal = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 10, Font.BOLD);
	protected final Font normalFontTitle = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 4, Font.BOLDITALIC);
	protected final Font boldFontTitle = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 7, Font.BOLD,
			BaseColor.BLACK);
	protected final Font boldFontTitleBig = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 11, Font.BOLD);
	protected final Font boldFontTitleMedium = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 11, Font.BOLD);
	protected final Font normalFontTitleBig = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 14, Font.NORMAL);
	protected final Font normalFontTitleMedium = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 10, Font.NORMAL);
	protected final String NEWLINE = "\n";
	protected final String BLANK_SPACE = "\n\n";
	protected int indent = 15;
	protected NumberFormat numberFormat = NumberFormat.getInstance();
	protected Paragraph newLine = new Paragraph(new Chunk(NEWLINE));
	protected Paragraph blank_SPACE = new Paragraph(new Chunk(BLANK_SPACE));

	public Pdf_Utils() {
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setMinimumFractionDigits(2);
	}

	public PdfPCell nuevaCelda(String value, int alineacion, Font font) {
		PdfPCell cellValue = new PdfPCell();
		cellValue.setHorizontalAlignment(alineacion);
		cellValue.setBorder(0);
		Paragraph dato = new Paragraph(new Phrase(value, font));
		dato.setAlignment(alineacion);
		cellValue.addElement(dato);
		return cellValue;

	}

	public PdfPCell nuevaCelda(String value, int alineacion, int colspan, Font font) {
		PdfPCell cellValue = nuevaCelda(value, alineacion, font);
		cellValue.setColspan(colspan);
		return cellValue;

	}

	public PdfPTable getTablaNoBordes(int col, int porcentaje, String[] datos, Font style) throws Exception {
		return getTablaNoBordes(col, porcentaje, new ArrayList<String>(Arrays.asList(datos)), style);
	}

	public PdfPTable getTablaNoBordes(int col, int porcentaje, List<String> datos, Font style) throws Exception {
		PdfPTable table = new PdfPTable(col);
		table.setWidthPercentage(porcentaje);
		table.setSpacingAfter(10f);
		for (String g : datos) {
			table.addCell(nuevaCelda(g, 0, style));
		}
		return table;
	}

	public Paragraph newSimpleParagraph(String data) {
		return new Paragraph(new Chunk(data));
	}

	public Image getImage(String logoPath, Document document) throws BadElementException, IOException {
		Image newImage = Image.getInstance(logoPath);
		newImage.scalePercent(getScaler(document, newImage));
		return newImage;
	}

	private float getScaler(Document document, Image image) {
		float scaler = ((document.getPageSize().getWidth() - document.leftMargin() + 10 - document.rightMargin() - 0)
				/ image.getWidth()) * 18;
		return scaler;
	}
}
