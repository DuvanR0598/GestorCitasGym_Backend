package com.udea.energym.util.reportes;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.udea.energym.dto.Usuario;

public class PdfUsuario {
	
	private List<Usuario> listaUsuarios;

	public PdfUsuario(List<Usuario> listaUsuarios) {
		super();
		this.listaUsuarios = listaUsuarios;
	}
	
	/**
	 * Se encarga de crear la cabecera de la tabla
	 * @param tabla
	 */
	private void escribirCabeceraTabla(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();
		celda.setBackgroundColor(Color.GRAY);
		celda.setPadding(5);
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.WHITE);
		fuente.setSize(10);
		
		celda.setPhrase(new Phrase("Cedula", fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Nombre", fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Apellido", fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Genero", fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Email", fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Celular", fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Altura", fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Peso", fuente));
		tabla.addCell(celda);
	}
	
	/**
	 * Trae los datos de la base de datos y los agrega a una celda 
	 * @param tabla
	 */
	private void escribirDatosTabla(PdfPTable tabla) {
		for(Usuario usuario: listaUsuarios) {
			tabla.addCell(String.valueOf(usuario.getCedula()));
			tabla.addCell(usuario.getNombre());
			tabla.addCell(usuario.getApellido());
			tabla.addCell(String.valueOf(usuario.getGenero()));
			tabla.addCell(usuario.getEmail());
			tabla.addCell(usuario.getCelular());
			tabla.addCell(String.valueOf(usuario.getAltura()));
			tabla.addCell(String.valueOf(usuario.getPeso()));
		}
	}
	
	public void exportarPDF(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());
		
		documento.open();
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setColor(Color.BLACK);
		fuente.setSize(18);
		
		Paragraph titulo = new Paragraph("Lista de usuarios", fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);
		
		PdfPTable tabla = new PdfPTable(8);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[] {1.6f, 3.5f, 3f, 1f, 5f, 3f, 1.3f, 1.2f});
		tabla.setWidthPercentage(110);
		
		escribirCabeceraTabla(tabla);
		escribirDatosTabla(tabla);
		
		documento.add(tabla);
		documento.close();
	}
 
}
