import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class ProcessaArquivosTest {
	@Test
	@Before
	void criaArquivoParaTeste() throws IOException {
		String nomeArquivo = "01.txt";
		String diretorioAtual = System.getProperty("user.dir");
		FileWriter file = new FileWriter(diretorioAtual + "/data/in/" + nomeArquivo);
		PrintWriter gravarArq = new PrintWriter(file);
		String conteudoArquivo = "001ç1234567891234çPedroç50000\n" + "001ç3245678865434çPauloç40000.99\n"
				+ "002ç2345675434544345çJose da SilvaçRural\n" + "002ç2345675433444345çEduardo PereiraçRural\n"
				+ "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro\n" + "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo\n"
				+ "002ç2345675434544345çJose da SilvaçRural\n" + "001ç1234567891234çPedroç50000\n"
				+ "003ç99ç[1-1-1]çZe\n" + "002ç1345675433444345çMaria\n" + "001ç56245678865434çRobertoç100\n"
				+ "003ç77ç[1-123-100,2-30-2.50,3-40-3.09]çFernanda";
		gravarArq.printf(conteudoArquivo);
		file.close();
	}

	@Test
	void listaArquivosAprocessar() throws Exception {
		String nomeArquivo = "01.txt";
		String caminhoDiretorio = "data/in/";
		ProcessaArquivos processa = new ProcessaArquivos();
		String conteudoDiretorio = processa.listaArquivos(caminhoDiretorio);
		String expected = nomeArquivo;
		assertEquals(expected, conteudoDiretorio);
	}

	@Test
	void leArquivoPorNome() throws Exception {
		String nomeArquivo = "01.txt";
		String caminhoDiretorio = "data/in/";
		ProcessaArquivos processa = new ProcessaArquivos();
		String conteudoArquivo = processa.leArquivoPorNome(caminhoDiretorio, nomeArquivo);
		String expected = "";
		try {
			FileReader arq = new FileReader(caminhoDiretorio + nomeArquivo);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			expected += linha + "\n";
			while (linha != null) {
				linha = lerArq.readLine();
				expected += linha + "\n";
			}
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n" + e.getMessage(), e.getMessage());
		}
		assertEquals(expected, conteudoArquivo);
	}

	@Test
	void processaArquivos() throws Exception {
		String nomeArquivo = "01.txt";
		String caminhoDiretorio = "data/in/";
		ProcessaArquivos processa = new ProcessaArquivos();
		String arquivoProcessado = processa.processaArquivo(caminhoDiretorio, nomeArquivo);
		String expected = "Arquivos processados com sucesso!";
		assertEquals(expected, arquivoProcessado);
	}
	
	@Test
	void validaExisteArquivoProcessado() throws Exception {
		String nomeArquivo = "01-processado.txt";
		String caminhoDiretorio = "data/out";
		ProcessaArquivos processa = new ProcessaArquivos();
		String arquivosProcessados = processa.listaArquivos(caminhoDiretorio);
		String expected = nomeArquivo;
		assertTrue(arquivosProcessados.contains(expected));
	}
	
	@Test
	void validaConteudoArquivoProcessado() throws Exception {
		String nomeArquivo = "01-processado.txt";
		String caminhoDiretorio = "data/out/";
		ProcessaArquivos processa = new ProcessaArquivos();
		String conteudoArquivo = processa.leArquivoPorNome(caminhoDiretorio, nomeArquivo);
		String expected = "Total de cliente(s) 3\n" + 
				"Total de vendedore(s) 3\n" + 
				"Pior vendedor:  Ze\n" + 
				"Id da venda mais cara 77";
		assertTrue(conteudoArquivo.contains(expected));
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
