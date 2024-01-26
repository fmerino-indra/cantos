package org.fmm.cantos.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fmm.cantos.dto.CantoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service("RequesDataService")
public class RequestDataServiceImpl implements RequestDataService {
	private static final Logger logger = LoggerFactory.getLogger(RequestDataService.class);


	@Value("${application.url-base}")
	private String urlBase;
	
	@Value("${application.href}")
	private String href;
	
	@Value("${application.local-base-dir}")
	private String localBaseDir;
	
	@Value("${application.local-download-dir}")
	private String localDownloadDir;
	
	public Map<String, CantoDTO> downloadProcess() {
		String aux = null;
		Map<String, CantoDTO> cantos = new LinkedHashMap<>();
		Map<String, CantoDTO> cantosAux = null;
		String url = null; 

		while (href != null) {
			url = urlBase + href;
			aux = requestPage(url);
			cantosAux = processItems(aux);
			cantos.putAll(cantosAux);
			href= processPagination(aux);
		}
		return cantos;
	}
	
	private Map<String, CantoDTO> processItems(String page) {
		Map<String, CantoDTO> cantos = new LinkedHashMap<>();
		CantoDTO cantoDTO = null;
		
		/* <div class="post">
		 *    <h2 class="post-item ...
		 *    <div class="post-item post-meta">...
		 *    <div class="post-item post-summary"...
		 * </div>
		 */
		Pattern patronCanciones = Pattern.compile("<div class=\"post\">\\n\\s*<h2 class=\"post-item post-title\">(?<h2>.*?)</h2>\\n\\s*<div class=\"post-item post-meta\">(?<labels>.*?)</div>\\n\\s*<div class=\"post-item post-summary markdown-body\">\\n\\s*(?<summary>.*?)\\n\\s*</div>\\n\\s*</div>", Pattern.DOTALL);
		Matcher m = patronCanciones.matcher(page);

		while (m.find()) {
			cantoDTO = new CantoDTO();
			cantoDTO = processItem(m.group("h2"),m.group("labels"),m.group("summary"));
			cantoDTO = requestDetail(cantoDTO);
			cantos.put(cantoDTO.getName(), cantoDTO);
		}

		return cantos;
	}

	private String requestPage(String url) {
		byte[] response = null;
		String answer = "";

		response = requestRawPage(url);
		if (response != null)
			answer = new String(response, StandardCharsets.UTF_8);
		return answer;
	}
	
	private byte[] requestRawPage(String url) {
		HttpRequest request = null;
		HttpClient client = null;
		HttpResponse<byte[]> response = null;
		logger.debug("Requesting page: {}",url);
		System.out.println("[FMM] Requesting page:"+url);
		try {
			request = HttpRequest.newBuilder()
					  .uri(new URI(url))
					  .header(HttpHeaders.ACCEPT, MediaType.TEXT_HTML_VALUE)
					  .header(HttpHeaders.ACCEPT_LANGUAGE, "es")
					  .GET()
					  .build();
			
			client = HttpClient.newHttpClient();
			response = client.send(request, BodyHandlers.ofByteArray());

		} catch (URISyntaxException e) {
			logger.error("An exception has raised: {}",e);
//			e.printStackTrace();
		} catch (IOException e) {
			logger.error("An exception has raised: {}",e);
//			e.printStackTrace();
		} catch (InterruptedException e) {
			logger.error("An exception has raised: {}",e);
//			e.printStackTrace();
		}
		if (response != null) {
			if (response.statusCode() == 200) {
			logger.info("Requested page: {}",url);
			return response.body();
			} else {
				logger.warn("Requested page: {} NOT DOWNLOADED. Error: {}",url, response.statusCode());
				return new byte[0];
			}

		} else {
			logger.warn("An exception has raised requesting page: {}",url);
			return new byte[0];
		}
	}

	private CantoDTO processItem(String h2, String labels, String summary) {
		CantoDTO cantoDTO = null;
		String aux = null;
		Pattern patronCanciones = Pattern.compile("<a href=\"(?<url>.*)\">(?<nombre>.*)</a>",Pattern.CASE_INSENSITIVE);
		Matcher m = patronCanciones.matcher(h2);
		if (m.find()) {
			cantoDTO = new CantoDTO();
			cantoDTO.setName(m.group("nombre"));
			cantoDTO.setUrl(m.group("url"));
		}
		logger.debug("Processing item: {}", cantoDTO.getName());
		
		patronCanciones = Pattern.compile("<a href=\"/tags/(?<label>.*?)\">", Pattern.DOTALL);
		m = patronCanciones.matcher(labels);
		while (m.find()) {
			cantoDTO.addLabel(m.group("label"));
		}
		
		patronCanciones = Pattern.compile(".*");
		m = patronCanciones.matcher(summary);
		while (m.find()) {
			if (!(m.group().length()==0 || m.group().equals("\n")))
				cantoDTO.addSummary(m.group());
		}
		
		// URL - termina en /
		patronCanciones = Pattern.compile("(?<texto>.*?)/");
		m = patronCanciones.matcher(cantoDTO.getUrl());
		
		while (m.find()) {
			aux = m.group("texto");
		}
		cantoDTO.setNameShort(aux);
		logger.debug("Processed item: {} ({})", cantoDTO.getName(), cantoDTO.getNameShort());

		return cantoDTO;
	}
	public String processPagination(String page) {
		Pattern patronPaginacion = Pattern.compile("<div class=\"pag-next\">\\n\\s*<a href=\"(?<nextPage>.*?)\">",
				Pattern.CASE_INSENSITIVE);
		Matcher mPage = patronPaginacion.matcher(page);
		String url = null;

		if (mPage.find()) {
			url = mPage.group("nextPage");
		}
		return url;
	}

	private CantoDTO requestDetail(CantoDTO canto) {
		String page=null;
		logger.debug("Processing detail: {}", canto.getName());
		
		page = requestPage(canto.getUrl());
		
		String aux = null;
		byte[] auxBytes = null;
		
		Path newBinary = null;
		
		auxBytes = page.getBytes();
		
		saveToFile(auxBytes, canto.getNameShort(), canto.getNameShort(), "txt");
		
		/*
<audio id="8_0" controls="" controlslist="nodownload" preload="none" style=" width:100%;">
   <source src="https://cantos01.fra1.cdn.digitaloceanspaces.com/00/A_la_victima_pascual/%5BOK%5D_A_la_victima_pascual.mp3" type="audio/mpeg">
</audio>
		 */
		Pattern detailPattern = Pattern.compile("<source src=\"(?<mp3Url>.*?)\" type=",	Pattern.CASE_INSENSITIVE);
		Matcher m = detailPattern.matcher(page);
		if (m.find()) {
			canto.addMp3Url(m.group("mp3Url"));
		}

		/*
  <div
    class="post-aplayer"
    data-base="https://cantos.cnc.madrid/"
    data-urls="https://cantos01.fra1.cdn.digitaloceanspaces.com/00//Adonde_te_escondiste_amado/Cantico_Spirituale_San_Giovanni_della_Croce.mp3,%20https://cantos01.fra1.cdn.digitaloceanspaces.com/00/Adonde_te_escondiste_amado/Cantico_espiritual-3x4-ENH1.mp3"
    data-names="Italia 198?, España (version 3/4)"
    data-artists="Kiko_Arguello, Kiko_Arguello"
    data-covers="/img/Resucito-Portada8.jpeg, /img/Resucito-Portada8.jpeg"
  ></div>
		 */
		if (canto.getMp3Url() == null) {
			detailPattern = Pattern.compile("data-urls=\"(?<mp3Url>.*)\"",	Pattern.CASE_INSENSITIVE);
			m = detailPattern.matcher(page);
			if (m.find()) {
				aux = m.group("mp3Url");
			}
			detailPattern = Pattern.compile(".*?(?<mp3Url>http[^,]*)(,?|$)", Pattern.CASE_INSENSITIVE);
			m = detailPattern.matcher(aux);
			while (m.find()) {
				canto.addMp3Url(m.group("mp3Url"));
			}
		}
		
		detailPattern = Pattern.compile("<img class=\"special-img-class\" src=\"(?<imgUrl>.*?)\" style=",	Pattern.CASE_INSENSITIVE);
		m = detailPattern.matcher(page);
		if (m.find()) {
			canto.setImgUrl(m.group("imgUrl"));
		}
		
		newBinary = downloadAndSaveBinary(canto.getImgUrl(), canto.getNameShort(), canto.getNameShort(), "png");
		canto.setImgFile(newBinary);
		// URL - termina en .mp3
		detailPattern  = Pattern.compile("(.*/)*(?<texto>.*)\\.mp3$");
		
		for (String mp3: canto.getMp3Url()) {
			m = detailPattern.matcher(mp3);
			if (m.find()) {
				newBinary = downloadAndSaveBinary(mp3, canto.getNameShort(), m.group("texto"), "mp3");
				canto.addMp3File(newBinary);
				
			}
		}
		logger.info("Processed detail: {}", canto.getName());
		
		return canto;
	}
	
	private Path downloadAndSaveBinary(String url, String dir, String name, String ext) {
		byte[] fichero = null;
		
		Path newFile = null;
		Path directories = null;
		FileChannel channel = null;

		try {
			fichero = requestRawPage(url);
			directories = Paths.get(Paths.get(localBaseDir).toAbsolutePath().toString(), localDownloadDir, dir);
			newFile = Paths.get(directories.toAbsolutePath().toString(), name+"."+ext);
			try {
				Files.createDirectories(directories);
				Files.createFile(newFile);
			} catch (FileAlreadyExistsException faee) {
				
			}
				
			channel = FileChannel.open(newFile,StandardOpenOption.WRITE);
				
			channel.write(ByteBuffer.wrap(fichero));
			channel.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newFile;
	}

	private void saveToFile(byte[] bytes, String dir, String name, String ext) {
		Path newFile = null;
		Path directories = null;
		directories = Paths.get(Paths.get(localBaseDir).toAbsolutePath().toString(), localDownloadDir, dir);
		newFile = Paths.get(directories.toAbsolutePath().toString(), name+"."+ext);
		try {
			Files.createDirectories(directories);
			Files.createFile(newFile);
			Files.write(newFile, bytes, StandardOpenOption.WRITE);

		} catch (FileAlreadyExistsException faee) {
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
// Pasa un byte a hexa
//aux = String.format("%02X", cantoDTO.getName().getBytes()[6]) + String.format("%02X", cantoDTO.getName().getBytes()[7]);

