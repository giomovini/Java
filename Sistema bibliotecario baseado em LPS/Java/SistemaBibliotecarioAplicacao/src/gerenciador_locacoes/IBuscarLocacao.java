package gerenciador_locacoes;

import java.util.List;


public interface IBuscarLocacao {

	public List<Locacao> buscarLocacoes();

	public List<Locacao> buscarLocacoes(String Loc, String Ex, String Status);

}
