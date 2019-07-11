package aplicacao.reserva;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import gerenciador_exemplares.Exemplar;
import gerenciador_locatarios.Locatario;
import teste.aplicacao.TabelaReserva;

public class Reserva implements IRealizarReserva,IExcluirReserva{
	int identificador;
	Date DataReserva;
	Locatario Loc;
	Exemplar Ex;
	
	@Override
	public List<Reserva> listarReservas() {
		return new TabelaReserva().buscarReservas();
	}
	
	@Override
	public boolean excluirReserva(Reserva Rev) {
		boolean sucess = true;
		sucess = TabelaReserva.getInstance().deletarReserva(Rev);
		JOptionPane.showMessageDialog(null, sucess ? "Registro deletado com sucesso!"
				: "Não foi possivel realizar a deleção da reserva!");
		return sucess;
	}
	@Override
	public boolean realizarReserva(Reserva Rev) {
		boolean sucess = false;
		if(Rev.getDataReserva() == null || Rev.getEx() == null || Rev.getLoc() == null) {
			sucess = false;
		}else {
			sucess = TabelaReserva.getInstance().salvarReserva(Rev);
		}
		JOptionPane.showMessageDialog(null, sucess ? "Registro inserido com sucesso!": 
			"Não foi possivel realizar a reserva do item!");
		
		return sucess;
	}
	
	
	public Locatario getLoc() {
		return Loc;
	}
	public void setLoc(Locatario loc) {
		Loc = loc;
	}
	public Exemplar getEx() {
		return Ex;
	}
	public void setEx(Exemplar ex) {
		Ex = ex;
	}
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public Date getDataReserva() {
		return DataReserva;
	}
	public void setDataReserva(Date dataReserva) {
		DataReserva = dataReserva;
	}
	
	

}
