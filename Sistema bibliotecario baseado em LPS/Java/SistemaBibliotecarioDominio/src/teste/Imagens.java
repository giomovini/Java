package teste;

public enum Imagens {
	
	 icone("icon.png"),
	 inicial("inicial.jpg");
	 
    public String Imagem;
    Imagens(String Imagem) {
        this.Imagem = "/teste/"+Imagem;
    }

}
