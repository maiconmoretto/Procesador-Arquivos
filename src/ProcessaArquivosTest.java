import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class ProcessaArquivosTest {

	@Test
	void listaArquivos() {
		String nomeArquivo = "01.txt";
		
		File file = new File("/home/maicon/eclipse-workspace/Procesador de Arquivos/data/in/" + nomeArquivo);
		boolean aquivoExiste = file.exists();
		if (!aquivoExiste) {
			try {
				file.createNewFile();
			} catch (IOException io) {
				io.printStackTrace();
			}
		}
		String caminhoArquivo = "data/in/";
		ProcessaArquivos processa = new ProcessaArquivos();
		String conteudoDiretorio = processa.listaArquivos(caminhoArquivo);
		String expected = nomeArquivo;
		assertEquals(expected, conteudoDiretorio);
	}

	@Test
	void leArquivoPorNome() {
		String nomeArquivo = "01.txt";
		String caminhoArquivo = "data/in/";
		
		File file = new File("/home/maicon/eclipse-workspace/Procesador de Arquivos/data/in/" + nomeArquivo);
		boolean aquivoExiste = file.exists();
		
		if (!aquivoExiste) {
			try {
				file.createNewFile();
			} catch (IOException io) {
				io.printStackTrace();
			}
		}
		ProcessaArquivos processa = new ProcessaArquivos();
		String conteudoArquivo = processa.leArquivoPorNome(caminhoArquivo, nomeArquivo);
		String expected = "";

		try {
			FileReader arq = new FileReader(caminhoArquivo + nomeArquivo);
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine();

			while (linha != null) {
				linha = lerArq.readLine();
				expected += linha;
			}
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n" + e.getMessage(), e.getMessage());
		}
		assertEquals(expected, conteudoArquivo);
	}

}
