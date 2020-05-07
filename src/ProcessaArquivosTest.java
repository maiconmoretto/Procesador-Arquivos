import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class ProcessaArquivosTest {
	@Test
	@Before
	void criaArquivoParaTeste() {
		String nomeArquivo = "01.txt";
		String diretorioAtual = System.getProperty("user.dir");
		File file = new File(diretorioAtual + "/data/in/" + nomeArquivo);
		boolean aquivoExiste = file.exists();
		if (!aquivoExiste) {
			try {
				file.createNewFile();
			} catch (IOException io) {
				io.printStackTrace();
			}
		}
	}

	@Test
	void listaArquivosAprocessar() {
		String nomeArquivo = "01.txt";

		String caminhoArquivo = "data/in/";
		ProcessaArquivos processa = new ProcessaArquivos();
		String conteudoDiretorio = processa.listaArquivos(caminhoArquivo);
		String expected = nomeArquivo;
		assertEquals(expected, conteudoDiretorio);
	}

	/*
	 * @Test void listaArquivosProcessados() { String nomeArquivo =
	 * "01-processado.txt"; String caminhoArquivo = "data/out/"; ProcessaArquivos
	 * processa = new ProcessaArquivos(); String conteudoDiretorio =
	 * processa.listaArquivos(caminhoArquivo); String expected = nomeArquivo;
	 * assertEquals(expected, conteudoDiretorio); }
	 */

	@Test
	void leArquivoPorNome() {
		String nomeArquivo = "01.txt";
		String caminhoArquivo = "data/in/";

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
	@Test
	@After
	void removeArquivoParaTeste() {
		String nomeArquivo = "01.txt";
		 String diretorioAtual = System.getProperty("user.dir");
		File file = new File(diretorioAtual + "/data/in/" + nomeArquivo);
			
		boolean aquivoExiste = file.exists();
		if (aquivoExiste) {
			file.delete();
		}
	}

}
