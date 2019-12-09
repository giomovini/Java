/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.entidades;


/**
 *
 * @author Uchiha
 */
public class CandidatoDoacao {


    private Integer CodigoIdentificador;
    private String Nascimento;
    private String Sexo;
    private String Nome;
    private String NomePai;
    private String NomeMae;
    private String RG;  
    private String Endereco;
    private String Cep;
    private String Estado;
    private String Cidade;
    private String Bairro;
    private String Telefone;
            

    /**
     * @return the Nascimento
     */
    public String getNascimento() {
        return Nascimento;
    }
   

    /**
     * @param Nascimento the Nascimento to set
     */
    public void setNascimento(String Nascimento) {
        this.Nascimento = Nascimento;
    }

    /**
     * @return the Nome
     */
    public String getNome() {
        return Nome;
    }

    /**
     * @param Nome the Nome to set
     */
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    /**
     * @return the NomePai
     */
    public String getNomePai() {
        return NomePai;
    }

    /**
     * @param NomePai the NomePai to set
     */
    public void setNomePai(String NomePai) {
        this.NomePai = NomePai;
    }

    /**
     * @return the NomeMae
     */
    public String getNomeMae() {
        return NomeMae;
    }

    /**
     * @param NomeMae the NomeMae to set
     */
    public void setNomeMae(String NomeMae) {
        this.NomeMae = NomeMae;
    }

    /**
     * @return the RG
     */
    public String getRG() {
        return RG;
    }

    /**
     * @param RG the RG to set
     */
    public void setRG(String RG) {
        this.RG = RG;
    }

    /**
     * @return the Endereco
     */
    public String getEndereco() {
        return Endereco;
    }

    /**
     * @param Endereco the Rua to set
     */
    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    /**
     * @return the Cep
     */
    public String getCep() {
        return Cep;
    }

    /**
     * @param Cep the Cep to set
     */
    public void setCep(String Cep) {
        this.Cep = Cep;
    }

    /**
     * @return the Estado
     */
    public String getEstado() {
        return Estado;
    }

    /**
     * @param Estado the Estado to set
     */
    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    /**
     * @return the Cidade
     */
    public String getCidade() {
        return Cidade;
    }

    /**
     * @param Cidade the Cidade to set
     */
    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    /**
     * @return the Sexo
     */
    public String getSexo() {
        return Sexo;
    }

    /**
     * @param Sexo the Sexo to set
     */
    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    /**
     * @return the Bairro
     */
    public String getBairro() {
        return Bairro;
    }

    /**
     * @param Bairro the Bairro to set
     */
    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    /**
     * @return the CodigoIdentificador
     */
    public Integer getCodigoIdentificador() {
        return CodigoIdentificador;
    }

    /**
     * @param CodigoIdentificador the CodigoIdentificador to set
     */
    public void setCodigoIdentificador(Integer CodigoIdentificador) {
        this.CodigoIdentificador = CodigoIdentificador;
    }

    /**
     * @return the Telefone
     */
    public String getTelefone() {
        return Telefone;
    }

    /**
     * @param Telefone the Telefone to set
     */
    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }
    
}
