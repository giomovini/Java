package com.colisao;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//cria um buffer de som a partir de som a partir de um arquivo de audio .au
public class ClipSom {
	
	//buffer de audio
	private Clip clip;
	
	
	//contrutor que recebe o arquivo de audio como parametro
	public ClipSom(String arquivoAudio){
		try {
			//instancia um buffer de audio
			clip = AudioSystem.getClip();
			
			//cria um fluxo de audio 
			AudioInputStream amostra = AudioSystem.getAudioInputStream(getClass().getResource(arquivoAudio));
			clip.open(amostra);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		
		
	}
	
	
	
	public void tocar(){
		
		//reset o clip de som
		
		clip.setFramePosition(0);
		clip.loop(0);
		
		
		
	}
	
	
	
	
	

}
