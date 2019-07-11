package gerenciador_exemplares;

import java.util.List;

import com.itextpdf.text.pdf.PdfPCell;

public interface IImprimirResultadoConsulta {

	public boolean imprimirRegistrosExemplar(List<Exemplar> Lista);
	public String gerarArquivoImpressao(List<Exemplar> Lista);
	public PdfPCell formatarTabelaDados(String texto, int i);
	

}
