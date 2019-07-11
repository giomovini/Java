package gerenciador_exemplares;

import java.util.List;

public interface IManterExemplar {

	public boolean cadastrarExemplar(Exemplar Ex);

	public boolean editarExemplar(Exemplar Ex);

	public boolean deletarExemplar(Exemplar Ex);

	public List<Exemplar> buscarExemplares();

	public List<Exemplar> buscarExemplares(String Tit, String Aut);

	public boolean validarExemplar(Exemplar Ex);

	public boolean alterarQtdeDisponivel(Exemplar Ex,int qtde);

}
