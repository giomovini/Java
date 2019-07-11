package teste.aplicacao;

import aplicacao.reserva.Reserva;

public class LinhaReserva {
	
	Reserva M;
	String DataReserva,locatario,titulo;
	public Reserva getM() {
		return M;
	}
	public void setM(Reserva m) {
		M = m;
	}
	public String getDataReserva() {
		return DataReserva;
	}
	public void setDataReserva(String dataReserva) {
		DataReserva = dataReserva;
	}
	public String getLocatario() {
		return locatario;
	}
	public void setLocatario(String locatario) {
		this.locatario = locatario;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	


	
}
