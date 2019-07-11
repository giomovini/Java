package aplicacao.impressao_carteirinha;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import gerenciador_locatarios.Locatario;

public class ImpressaoCarteirinha implements IImpressaoCarteirinha{
	
	@Override
	public boolean imprimirCarteirinha(Locatario Loc) {
		boolean sucess = false;
		String caminho = gerarModeloCarteirinha(Loc);
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(caminho);
			PrintService impressora = PrintServiceLookup.lookupDefaultPrintService();
			PrintRequestAttributeSet printAttributes = new HashPrintRequestAttributeSet();
			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			HashDocAttributeSet docAttributeSet = new HashDocAttributeSet();
			Doc doc = new SimpleDoc(fis, flavor, docAttributeSet);
			PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, docAttributeSet);
			if (services != null && services.length > 0 && impressora != null) {
				PrintService service = ServiceUI.printDialog(null, 320, 240, services, impressora, flavor,
						printAttributes);
				impressora = (service != null && services.length > 0) ? service : null;
				
				if(service != null) {
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
			} catch (Exception lE) {}
		}
		JOptionPane.showMessageDialog(null, sucess ? "Carteirinha impressa com sucesso" : "Houve falha na impressão");
		return sucess;
	}
	
	@Override
	public String gerarModeloCarteirinha(Locatario Loc) {
		String caminho = ".\\carteirinha.pdf";
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(caminho));
			document.open();
			Calendar validade = Calendar.getInstance();
			validade.setTime(new Date());
			validade.set(Calendar.YEAR, validade.get(Calendar.YEAR) + 1);

			PdfPTable tabela = new PdfPTable(2);
			tabela.setTotalWidth(new float[] { 130, 130 });
			tabela.setLockedWidth(true);
			tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabela.addCell(padronizarCelulas("Carteirinha UTBiblio", -1));
			tabela.addCell(padronizarCelulas(" ", -1));
			tabela.addCell(padronizarCelulas("Nome", 1));
			tabela.addCell(padronizarCelulas(Loc.getNome(), 1));
			tabela.addCell(padronizarCelulas("CPF", 1));
			tabela.addCell(padronizarCelulas(Loc.getCPF(), 1));
			tabela.addCell(padronizarCelulas("Nascimento", 1));
			tabela.addCell(padronizarCelulas(Loc.getNascimento().substring(8,10)+"/"
			+Loc.getNascimento().substring(5,7)+"/"+Loc.getNascimento().substring(0,4), 1));
			tabela.addCell(padronizarCelulas("Validade", 1));
			tabela.addCell(padronizarCelulas(new SimpleDateFormat("dd/MM/yyyy").format(validade.getTime()), 1));
			document.add(tabela);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}finally {
			document.close();
		}
		return caminho;
	}

	@Override
	public PdfPCell padronizarCelulas(String Texto, int Tipo) {
		Font f = new Font();
		f.setStyle((Tipo == -1) ? Font.BOLD : Font.NORMAL);
		f.setSize(12);
		PdfPCell cell = new PdfPCell(new Phrase(Texto, f));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan((Tipo == -1) ? 2 : 1);
		return cell;
	}

}
