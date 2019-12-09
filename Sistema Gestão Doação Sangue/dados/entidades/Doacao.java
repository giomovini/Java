/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.entidades;

public class Doacao {

    private String DataDoacao;
    private Integer CodigoIdentificador;
    private String nomeDoador;
    private Boolean Anemia;
    private Boolean HepatiteB;
    private Boolean HepatiteC;
    private Boolean DoencaChagas;
    private Boolean Sifilis;
    private Boolean Aids;
    private Boolean HTLVI;
    private Boolean HTLVII;
    private Boolean AnticorposIrregulares;
    private String TipoSangue;
    private Boolean RhSangue;
    private Boolean TriagemClinica;
    private CandidatoDoacao Doador;

    /**
     * @return the Doacao
     */
    public String getDoacao() {
        return getDataDoacao();
    }

    /**
     * @param DataDoacao the Doacao to set
     */
    public void setDoacao(String DataDoacao) {
        this.setDataDoacao(DataDoacao);
    }

    /**
     * @return the CodigoIdentificador
     */
    public Integer getCodigoIdentificador() {
        if (CodigoIdentificador == null) {
            return 0;
        }
        return CodigoIdentificador;
    }

    /**
     * @param CodigoIdentificador the CodigoIdentificador to set
     */
    public void setCodigoIdentificador(Integer CodigoIdentificador) {
        this.CodigoIdentificador = CodigoIdentificador;
    }

    /**
     * @return the DataDoacao
     */
    public String getDataDoacao() {
        return DataDoacao;
    }

    /**
     * @param DataDoacao the DataDoacao to set
     */
    public void setDataDoacao(String DataDoacao) {
        this.DataDoacao = DataDoacao;
    }

    /**
     * @return the nomeDoador
     */
    public String getNomeDoador() {
        return nomeDoador;
    }

    /**
     * @param nomeDoador the nomeDoador to set
     */
    public void setNomeDoador(String nomeDoador) {
        this.nomeDoador = nomeDoador;
    }

    /**
     * @return the Doador
     */
    public CandidatoDoacao getDoador() {
        return Doador;
    }

    /**
     * @param Doador the Doador to set
     */
    public void setDoador(CandidatoDoacao Doador) {
        this.Doador = Doador;
    }

    /**
     * @return the HepatiteB
     */
    public Boolean getHepatiteB() {
        return HepatiteB;
    }

    /**
     * @param HepatiteB the HepatiteB to set
     */
    public void setHepatiteB(Boolean HepatiteB) {
        this.HepatiteB = HepatiteB;
    }

    /**
     * @return the HepatiteC
     */
    public Boolean getHepatiteC() {
        return HepatiteC;
    }

    /**
     * @param HepatiteC the HepatiteC to set
     */
    public void setHepatiteC(Boolean HepatiteC) {
        this.HepatiteC = HepatiteC;
    }

    /**
     * @return the DoencaChagas
     */
    public Boolean getDoencaChagas() {
        return DoencaChagas;
    }

    /**
     * @param DoencaChagas the DoencaChagas to set
     */
    public void setDoencaChagas(Boolean DoencaChagas) {
        this.DoencaChagas = DoencaChagas;
    }

    /**
     * @return the Sifilis
     */
    public Boolean getSifilis() {
        return Sifilis;
    }

    /**
     * @param Sifilis the Sifilis to set
     */
    public void setSifilis(Boolean Sifilis) {
        this.Sifilis = Sifilis;
    }

    /**
     * @return the Aids
     */
    public Boolean getAids() {
        return Aids;
    }

    /**
     * @param Aids the Aids to set
     */
    public void setAids(Boolean Aids) {
        this.Aids = Aids;
    }

    /**
     * @return the HTLVI
     */
    public Boolean getHTLVI() {
        return HTLVI;
    }

    /**
     * @param HTLVI the HTLVI to set
     */
    public void setHTLVI(Boolean HTLVI) {
        this.HTLVI = HTLVI;
    }

    /**
     * @return the HTLVII
     */
    public Boolean getHTLVII() {
        return HTLVII;
    }

    /**
     * @param HTLVII the HTLVII to set
     */
    public void setHTLVII(Boolean HTLVII) {
        this.HTLVII = HTLVII;
    }

    /**
     * @return the AnticorposIrregulares
     */
    public Boolean getAnticorposIrregulares() {
        return AnticorposIrregulares;
    }

    /**
     * @param AnticorposIrregulares the AnticorposIrregulares to set
     */
    public void setAnticorposIrregulares(Boolean AnticorposIrregulares) {
        this.AnticorposIrregulares = AnticorposIrregulares;
    }

    /**
     * @return the TipoSangue
     */
    public String getTipoSangue() {
        return TipoSangue;
    }

    /**
     * @param TipoSangue the TipoSangue to set
     */
    public void setTipoSangue(String TipoSangue) {
        this.TipoSangue = TipoSangue;
    }

    /**
     * @return the RhSangue
     */
    public Boolean getRhSangue() {
        return RhSangue;
    }

    /**
     * @param RhSangue the RhSangue to set
     */
    public void setRhSangue(Boolean RhSangue) {
        this.RhSangue = RhSangue;
    }

    /**
     * @return the Anemia
     */
    public Boolean getAnemia() {
        return Anemia;
    }

    /**
     * @param Anemia the Anemia to set
     */
    public void setAnemia(Boolean Anemia) {
        this.Anemia = Anemia;
    }

    /**
     * @return the TriagemClinica
     */
    public Boolean getTriagemClinica() {
        return TriagemClinica;
    }

    /**
     * @param TriagemClinica the TriagemClinica to set
     */
    public void setTriagemClinica(Boolean TriagemClinica) {
        this.TriagemClinica = TriagemClinica;
    }

}
