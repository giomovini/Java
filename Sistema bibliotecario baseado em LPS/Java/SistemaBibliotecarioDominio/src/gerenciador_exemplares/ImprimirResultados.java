package gerenciador_exemplares;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ImprimirResultados implements IImprimirResultadoConsulta {

	@Override
	public boolean imprimirRegistrosExemplar(List<Exemplar> Lista) {

		boolean sucess = false;
		String caminho = gerarArquivoImpressao(Lista);
		FileInputStream arquivo = null;

		try {
			arquivo = new FileInputStream(caminho);
			PrintService impressora = PrintServiceLookup.lookupDefaultPrintService();
			PrintRequestAttributeSet impressoraPropriedades = new HashPrintRequestAttributeSet();
			DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			HashDocAttributeSet docAttributeSet = new HashDocAttributeSet();
			Doc doc = new SimpleDoc(arquivo, docFlavor, docAttributeSet);

			PrintService[] impressoraServicos = PrintServiceLookup.lookupPrintServices(docFlavor, docAttributeSet);
			if (impressoraServicos != null && impressoraServicos.length > 0 && impressora != null) {
				PrintService service = ServiceUI.printDialog(null, 320, 240, impressoraServicos, impressora, docFlavor,impressoraPropriedades);
				if( impressoraServicos.length > 0)
					impressora = service;
			}
			if (impressora != null) {
				DocPrintJob job = impressora.createPrintJob();
				job.print(doc, impressoraPropriedades);
			}
			sucess = true;
		} catch (Exception e) {
			e.printStackTrace();
			sucess = false;
		} finally {
			try {
				arquivo.close();
			} catch (Exception lE) {
				lE.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(null, sucess ? "Registros impressos com sucesso" : "Houve falha na impressão");
		return sucess;
	}

	@Override
	public String gerarArquivoImpressao(List<Exemplar> Lista) {
		String caminho = "C:\\Users\\Uchiha\\Desktop\\UTFPR\\exemplares.pdf";
		Document document = new Document();
		try {
			PdfWriter.getInstance(document,
					new FileOutputStream(caminho));
			document.open();

			PdfPTable tabela = new PdfPTable(10);
			tabela.setTotalWidth(new float[] { 130, 110, 40, 25, 45, 30, 35, 60, 35, 25 });
			tabela.setLockedWidth(true);
			tabela.setHorizontalAlignment(Element.ALIGN_CENTER);

			tabela.addCell(formatarTabelaDados("Titulo", -1));
			tabela.addCell(formatarTabelaDados("Autor", -1));
			tabela.addCell(formatarTabelaDados("Categ.", -1));
			tabela.addCell(formatarTabelaDados("Ed.", -1));
			tabela.addCell(formatarTabelaDados("Idioma", -1));
			tabela.addCell(formatarTabelaDados("Lanc.", -1));
			tabela.addCell(formatarTabelaDados("Classif.", -1));
			tabela.addCell(formatarTabelaDados("Cod.", -1));
			tabela.addCell(formatarTabelaDados("Loc.", -1));
			tabela.addCell(formatarTabelaDados("Disp.", -1));

			for (int i = 0; i < Lista.size(); i++) {
				tabela.addCell(formatarTabelaDados(Lista.get(i).getTitulo(), i));
				tabela.addCell(formatarTabelaDados(
						Lista.get(i).getAutor().getNome() + " (" + Lista.get(i).getAutor().getWebSite() + ")", i));
				tabela.addCell(formatarTabelaDados(Lista.get(i).getCategoria(), i));
				tabela.addCell(formatarTabelaDados(String.valueOf(Lista.get(i).getEdicao()), i));
				tabela.addCell(formatarTabelaDados(Lista.get(i).getIdioma(), i));
				tabela.addCell(formatarTabelaDados(String.valueOf(Lista.get(i).getAnoLancamento()), i));
				tabela.addCell(formatarTabelaDados("+" + String.valueOf(Lista.get(i).getClassificacaoIdade()), i));
				tabela.addCell(formatarTabelaDados(Lista.get(i).getCodBarras(), i));
				tabela.addCell(formatarTabelaDados(Lista.get(i).getLocalizacao(), i));
				tabela.addCell(formatarTabelaDados(String.valueOf(Lista.get(i).getQtdeDisponivel()), i));
			}

			document.add(tabela);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			document.close();
		}
		return caminho;
	}

	@Override
	public PdfPCell formatarTabelaDados(String texto, int i) {
		Font f = new Font();
		f.setColor(BaseColor.BLACK);
		f.setStyle( i == -1 ? Font.BOLD : Font.NORMAL);
		f.setSize(8);
		
		PdfPCell cell = new PdfPCell(new Phrase(texto, f));
		cell.setBackgroundColor( i % 2 == 0 ? new BaseColor(230, 230, 230) : BaseColor.WHITE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

		return cell;
	}

}
