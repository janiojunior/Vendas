package br.unitins.vendas.application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

public class Util {
	
	// /images
	public static final String PATH_IMAGES = File.separator + "images";
	// /images/usuario
	public static final String PATH_IMAGES_USUARIO = PATH_IMAGES + File.separator + "usuario";
	
	private Util() {
		// para nao permitir uma instancia
	}
	
	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance()
			.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}
	
	public static void addWarningMessage(String message) {
		FacesContext.getCurrentInstance()
			.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
	}
	
	public static void addInfoMessage(String message) {
		FacesContext.getCurrentInstance()
			.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}
	
	public static boolean saveImageUsuario(InputStream inputStream, String imageType, int idUsuario) {
		// Exemplo da maquina do janio: /home/janio/images/usuario
		String diretorio = System.getProperty("user.home") + PATH_IMAGES_USUARIO;
		
		// Criando os diretorios caso nao exista
		File file = new File(diretorio);
		if (!file.exists()) {
			file.mkdirs(); // mkdirs - cria arquivo ou diretorio (caso o diretorio anterior nao exista ele cria tambem)
		}
		
		try {
			// criando o espaco de memoria para armazenamento de uma imagem
			// inputStream - eh o fluxo de dados de entrada 
			BufferedImage bImage = ImageIO.read(inputStream);
			
			// estrutura final do arquivo ex: /home/janio/images/usuario/01.png
			File arquivoFinal = new File(diretorio + File.separator + idUsuario + "." + imageType);
			// salvando a imagem
			// buffer da imagem, png/gif, 01.png
			ImageIO.write(bImage, imageType, arquivoFinal);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
