/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensagem;

/**
 *
 * @author Uchiha
 */
public class Cliente {

    private String apelido;
    private String nome;
    private String ipCliente;
    private String portaCliente;

    public Cliente(String apelido, String nome,String ipCliente, String portaCliente) {
        this.apelido = apelido;
        this.nome = nome;
        this.ipCliente = ipCliente;
        this.portaCliente = portaCliente;
    }

    /**
     * @return the apelido
     */
    public String getApelido() {
        return apelido;
    }

    /**
     * @param apelido the apelido to set
     */
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the ipCliente
     */
    public String getIpCliente() {
        return ipCliente;
    }

    /**
     * @param ipCliente the ipCliente to set
     */
    public void setIpCliente(String ipCliente) {
        this.ipCliente = ipCliente;
    }

    /**
     * @return the portaCliente
     */
    public String getPortaCliente() {
        return portaCliente;
    }

    /**
     * @param portaCliente the portaCliente to set
     */
    public void setPortaCliente(String portaCliente) {
        this.portaCliente = portaCliente;
    }

}
