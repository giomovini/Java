package aplicacao.impressao_carteirinha;

import com.itextpdf.text.pdf.PdfPCell;

import gerenciador_locatarios.Locatario;

public interface IImpressaoCarteirinha {
	
	public boolean imprimirCarteirinha(Locatario Loc);
	
	public String gerarModeloCarteirinha(Locatario Loc);
	
	public PdfPCell padronizarCelulas(String Texto, int Tipo);

}
