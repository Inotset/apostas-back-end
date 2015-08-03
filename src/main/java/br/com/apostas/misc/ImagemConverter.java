package br.com.apostas.misc;

import java.io.ByteArrayInputStream;
import java.util.Base64;

public class ImagemConverter {
	
	public static String converterImagemParaString(byte[] imagem){
		byte[] imagemFormatada = Base64.getEncoder().encode(imagem);

		ByteArrayInputStream b = new ByteArrayInputStream(imagemFormatada);

		int n = b.available();

		byte[] bytes = new byte[n];

		b.read(bytes, 0, n);

		String s = new String(bytes);

		return s;
	}

}
