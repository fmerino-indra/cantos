package org.fmm.cantos.dto;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Principal {
	public static void main(String[] args) throws UnsupportedEncodingException {
//		System.out.println(convertToBinary("á", "UTF-8"));
		prueba();
	}
	
	private static String convertToBinary(String input, String encoding) 
		      throws UnsupportedEncodingException {
		    byte[] encoded_input = Charset.forName(encoding)
		      .encode(input)
		      .array();  
		    return IntStream.range(0, encoded_input.length)
		        .map(i -> encoded_input[i])
		        .mapToObj(e -> Integer.toBinaryString(e ^ 255))
		        .map(e -> String.format("%1$" + Byte.SIZE + "s", e).replace(" ", "0"))
		        .collect(Collectors.joining(" "));
		}
	
	private static void prueba () {
		String valor = "data-urls=\"https://cantos01.fra1.cdn.digitaloceanspaces.com/00//Adonde_te_escondiste_amado/Cantico_Spirituale_San_Giovanni_della_Croce.mp3,%20https://cantos01.fra1.cdn.digitaloceanspaces.com/00/Adonde_te_escondiste_amado/Cantico_espiritual-3x4-ENH1.mp3\"";
		Pattern detailPattern = Pattern.compile("data-urls=\"(?<mp3Url>.*)\"",	Pattern.CASE_INSENSITIVE);
		Matcher m = detailPattern.matcher(valor);
		if (m.find()) {
			valor = m.group("mp3Url");
		}
		detailPattern = Pattern.compile(".*?(?<mp3Url>http[^,]*)(,?|$)", Pattern.CASE_INSENSITIVE);
		m = detailPattern.matcher(valor);
		while (m.find()) {
			System.out.println(m.group("mp3Url"));
//			System.out.println(m.group(2));
		}

	}
}
