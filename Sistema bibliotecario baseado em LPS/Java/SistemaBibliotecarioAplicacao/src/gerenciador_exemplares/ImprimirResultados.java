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
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.CopiesSupported;
import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ImprimirResultados implements IImprimirResultadoConsulta{

	@Override
	public boolean imprimirRegistrosExemplar(List<Exemplar> Lista) {
		
		boolean sucess = false;
		String caminho = gerarArquivoImpressao(Lista);
		
		FileInputStream fis = null;

		try {

			fis = new FileInputStream(caminho);
			
			PrintService impressora = PrintServiceLookup.lookupDefaultPrintService();

			PrintRequestAttributeSet printAttributes = new HashPrintRequestAttributeSet();

			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			HashDocAttributeSet docAttributeSet = new HashDocAttributeSet();
			Doc doc = new SimpleDoc(fis, flavor, docAttributeSet);

			// Localiza todas as impressoras.
			PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, docAttributeSet);
			if (services != null && services.length > 0 && impressora != null) {
				PrintService service = ServiceUI.printDialog(null, 320, 240, services, impressora, flavor,
						printAttributes);
				impressora = (service != null && services.length > 0) ? service : null;
				
				if(service != null) {
					// Informando a quantidade de copias na marra.
					CopiesSupported copSupp = (CopiesSupported) service.getSupportedAttributeValues(Copies.class, null,
							null);
					if (copSupp != null && copSupp.contains(5)) {
						printAttributes.add(new Copies(5));
					}
				}
				
			}

			if (impressora != null) {
				DocPrintJob job = impressora.createPrintJob();
				job.print(doc, printAttributes);
			}

			sucess = true;
		} catch (Exception e) {
			e.printStackTrace();
			
			sucess = false;
		} finally {

			try {
				fis.close();
			} catch (Exception lE) {
			}

		}
		JOptionPane.showMessageDialog(null, sucess ? "Registros impressos com sucesso" : "Houve falha na impressão");
		return sucess;
	}

	@Override
	public String gerarArquivoImpressao(List<Exemplar> Lista) {
		
		String caminho = "C:\\Users\\Uchiha\\AppData\\Local\\Temp\\exemplares.pdf";
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Uchiha\\AppData\\Local\\Temp\\exemplares.pdf"));
			document.open();

			
			// cria a tabela com 2 colunas
			PdfPTable tabela = new PdfPTable(10);
			// largura de cada coluna
			tabela.setTotalWidth(new float[] { 130, 110 , 40 , 25 , 45 , 30 , 35 , 60 , 35 , 25  });
			tabela.setLockedWidth(true);

			// alinhamento da tabela na pagina
			tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			
			tabela.addCell(formatacaoTexto("Titulo", -1));
			tabela.addCell(formatacaoTexto("Autor", -1));
			tabela.addCell(formatacaoTexto("Categ.", -1));
			tabela.addCell(formatacaoTexto("Ed.", -1));
			tabela.addCell(formatacaoTexto("Idioma", -1));
			tabela.addCell(formatacaoTexto("Lanc.", -1));
			tabela.addCell(formatacaoTexto("Classif.", -1));
			tabela.addCell(formatacaoTexto("Cod.", -1));
			tabela.addCell(formatacaoTexto("Loc.",-1));
			tabela.addCell(formatacaoTexto("Disp.", -1));

			// percorre a lista adicionando os dados a tabela
			for (int i = 0; i < Lista.size(); i++) {
				tabela.addCell(formatacaoTexto(Lista.get(i).getTitulo(), i));
				tabela.addCell(formatacaoTexto(Lista.get(i).getAutor().getNome()+" ("+Lista.get(i).getAutor().getWebSite()+")", i));
				tabela.addCell(formatacaoTexto(Lista.get(i).getCategoria(), i));
				tabela.addCell(formatacaoTexto(String.valueOf(Lista.get(i).getEdicao()), i));
				tabela.addCell(formatacaoTexto(Lista.get(i).getIdioma(), i));
				tabela.addCell(formatacaoTexto(String.valueOf(Lista.get(i).getAnoLancamento()), i));
				tabela.addCell(formatacaoTexto("+"+String.valueOf(Lista.get(i).getClassificacaoIdade()), i));
				tabela.addCell(formatacaoTexto(Lista.get(i).getCodBarras(), i));
				tabela.addCell(formatacaoTexto(Lista.get(i).getLocalizacao(), i));
				tabela.addCell(formatacaoTexto(String.valueOf(Lista.get(i).getQtdeDisponivel()), i));

			}

			document.add(tabela);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}finally {
			// fecha o documento
			document.close();
		}

		return caminho;
	}
	
	@Override
	public PdfPCell formatacaoTexto(String texto, int i) {

		// fonte personalizada
		Font f = new Font();
		f.setColor(BaseColor.BLACK);

		// se for a primeira linha = a fonte e em negrito
		if (i == -1) {
			f.setStyle(Font.BOLD);
		} else {
			f.setStyle(Font.NORMAL);
		}

		f.setSize(8);

		// cria a celula
		PdfPCell cell = new PdfPCell(new Phrase(texto, f));


		// alterna as linhas colocando cor cinza e branco
		if (i % 2 == 0)
			cell.setBackgroundColor(new BaseColor(230, 230, 230));
		else
			cell.setBackgroundColor(BaseColor.WHITE);

		// alinhamento da celula
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

		return cell;
	}



}
