package dados.conexao_banco;

/* Classe responsavel por guardar
 * as informacoes sobre o banco de dados
 * */
public final class AcessoBanco {

    private String Usuario,Senha,Ip,Porta;
    private final String DB = "PROSANGUE";
    
    private static AcessoBanco Instance;
 
    private AcessoBanco() {
        
    }
 
    public static synchronized AcessoBanco getInstance() {
        if (Instance == null)
            Instance = new AcessoBanco();
 
        return Instance;
    }
    
    public void setProprieties(String Usuario,String Senha,String Ip,String Porta){
        System.out.println(this.getClass()+" - method: conectarBanco "+Usuario+" "+Senha+" "+Ip+" "+Porta);
        this.Usuario = Usuario;
        this.Senha = Senha;
        this.Ip = Ip;
        this.Porta = Porta;
    }
    /**
     * @return the Url_Conexao
     */
    public String getUrl_Conexao() {
        return "jdbc:mysql://" + this.Ip + ":" + this.Porta + "/" + DB;
    }

    /**
     * @return the Usuario
     */
    public String getUsuario() {
        return Usuario;
    }

    /**
     * @return the Senha
     */
    public String getSenha() {
        return Senha;
    }


}
