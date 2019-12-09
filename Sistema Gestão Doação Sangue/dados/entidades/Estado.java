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
public class Estado {
    
      private static Estado Instancia;

    public static synchronized Estado getInstance() {

        if (Instancia == null) {
            Instancia = new Estado();
        }
        return Instancia;
    }

    private Estado() {
    }
    
    public String siglaEstado(String Estado) {
        switch (Estado) {
            case "ACRE":
                return "AC";
            case "ALAGOAS":
                return "AL";
            case "AMAPÁ":
                return "AP";
            case "AMAZONAS":
                return "AM";
            case "BAHIA":
                return "BA";
            case "CEARÁ":
                return "CE";
            case "DISTRITO FEDERAL":
                return "DF";
            case "ESPIRITO SANTO":
                return "ES";
            case "GOIÁS":
                return "GO";
            case "MARANHÃO":
                return "MA";
            case "MATO GROSSO":
                return "MT";
            case "MATO GROSSO DO SUL":
                return "MS";
            case "MINAS GERAIS":
                return "MG";
            case "PARÁ":
                return "PA";
            case "PARAÍBA":
                return "PB";
            case "PARANÁ":
                return "PR";
            case "PERNAMBUCO":
                return "PE";
            case "PIAUÍ":
                return "PI";
            case "RIO DE JANEIRO":
                return "RJ";
            case "RIO GRANDE DO NORTE":
                return "RN";
            case "RIO GRANDE DO SUL":
                return "RS";
            case "RONDÔNIA":
                return "RO";
            case "RORAIMA":
                return "RR";
            case "SANTA CATARINA":
                return "SC";
            case "SÃO PAULO":
                return "SP";
            case "SERGIPE":
                return "SE";
            case "TOCANTINS":
                return "TO";
            default:
                return "";
        }
    }
    
    
     public String nomeEstado(String Sigla) {
        switch (Sigla) {
            case "AC":
                return "ACRE";
            case "AL":
                return "ALAGOAS";
            case "AP":
                return "AMAPÁ";
            case "AM":
                return "AMAZONAS";
            case "BA":
                return "BAHIA";
            case "CE":
                return "CEARÁ";
            case "DF":
                return "DISTRITO FEDERAL";
            case "ES":
                return "ESPIRITO SANTO";
            case "GO":
                return "GOIÁS";
            case "MA":
                return "MARANHÃO";
            case "MT":
                return "MATO GROSSO";
            case "MS":
                return "MATO GROSSO DO SUL";
            case "MG":
                return "MINAS GERAIS";
            case "PA":
                return "PARÁ";
            case "PB":
                return "PARAÍBA";
            case "PR":
                return "PARANÁ";
            case "PE":
                return "PERNAMBUCO";
            case "PI":
                return "PIAUÍ";
            case "RJ":
                return "RIO DE JANEIRO";
            case "RN":
                return "RIO GRANDE DO NORTE";
            case "RS":
                return "RIO GRANDE DO SUL";
            case "RO":
                return "RONDÔNIA";
            case "RR":
                return "RORAIMA";
            case "SC":
                return "SANTA CATARINA";
            case "SP":
                return "SÃO PAULO";
            case "SE":
                return "SERGIPE";
            case "TO":
                return "TOCANTINS";
            default:
                return "";
        }
    }
    
}
