package gerenciador_locatarios;

import java.util.List;

public interface IManterLocatario {

	public boolean cadastrarLocatario(Locatario Loc);

	public boolean editarLocatario(Locatario Loc);

	public boolean deletarLocatario(Locatario Loc);

	public List<Locatario> buscarLocatarios();

	public List<Locatario> buscarLocatarios(String Nome);

	public boolean validarLocatario(Locatario Loc);

}
