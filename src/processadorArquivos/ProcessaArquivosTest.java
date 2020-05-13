package processadorArquivos;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import junit.framework.AssertionFailedError;

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
		TrataArquivo processa = new TrataArquivo();
		List<String> conteudoDiretorio = new ArrayList<String>();
		conteudoDiretorio.addAll(processa.listaArquivos(caminhoDiretorio));
		String expected = nomeArquivo;
		assertTrue(conteudoDiretorio.contains(expected));
	}

	@Test
	void leArquivoPorNome() throws Exception {
		String nomeArquivo = "01.txt";
		String caminhoDiretorio = "data/in/";
		TrataArquivo processa = new TrataArquivo();
		String conteudoArquivo = processa.leArquivoPorNome(caminhoDiretorio, nomeArquivo);
		String expected = "";
		try {
			FileReader arq = new FileReader(caminhoDiretorio + nomeArquivo);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = "";
			while (lerArq.ready()) {
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
	@After
	void validaExisteArquivoProcessado() throws Exception {
		try {
			String nomeArquivo = "01-processado.txt";
			String caminhoDiretorio = "data/out";
			TrataArquivo processa = new TrataArquivo();
			List<String> arquivosProcessados = new ArrayList<String>();
			File arquivoSaida = new File(caminhoDiretorio + nomeArquivo);
			if (arquivoSaida.exists()) {
				arquivosProcessados.addAll(processa.listaArquivos(caminhoDiretorio));
				String expected = nomeArquivo;
				assertTrue(arquivosProcessados.contains(expected));
			}

		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Test
	@After
	void validaConteudoArquivoProcessado() throws Exception {
		try {
			String nomeArquivo = "01-processado.txt";
			String caminhoDiretorio = "data/out/";
			TrataArquivo processa = new TrataArquivo();
			File arquivoSaida = new File(caminhoDiretorio + nomeArquivo);
			if (arquivoSaida.exists()) {
				String conteudoArquivo = processa.leArquivoPorNome(caminhoDiretorio, nomeArquivo);
				String expected = "Total de cliente(s) = 3\n" + "Total de vendedore(s) = 3\n" + "Pior vendedor = Ze\n"
						+ "Id da venda mais cara = 77";
				assertTrue(conteudoArquivo.contains(expected));
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Test
	void processaArquivoFail() throws Exception {
		String messageExpected = "o campo caminhoArquivo e nomeArquivo não pode ser vazio!";
		ProcessaArquivos processaArquivos = new ProcessaArquivos();
		Throwable exception = assertThrows(Exception.class,
				() -> processaArquivos.processaArquivo("", ""));
		assertEquals(messageExpected, exception.getMessage());
	}

	@Test
	void criaArquivoRelatorioFail() throws Exception {
		ProcessaArquivos processaArquivos = new ProcessaArquivos();
		String messageExpected = "o campo conteudoRelatorio e nomeArquivo não pode ser vazio!";
		Throwable exception = assertThrows(Exception.class, () -> processaArquivos.criaArquivoRelatorio("", ""));
		assertEquals(messageExpected, exception.getMessage());
	}
	
	@Test
	void leArquivoPorNomeFail() throws Exception {
		TrataArquivo trataArquivo = new TrataArquivo();
		String messageExpected = "o campo caminhoArquivo e nomeArquivo não pode ser vazio!";
		Throwable exception = assertThrows(Exception.class, () -> trataArquivo.leArquivoPorNome("", ""));
		assertEquals(messageExpected, exception.getMessage());
	}
	
	@Test
	void processaQuantidadeClientesFail() throws Exception {
		ProcessaQuantidadeClientes processaQuantidadeClientes = new ProcessaQuantidadeClientes();
		String messageExpected = "O campo conteudoArquivo não pode ser vazio!";
		Throwable exception = assertThrows(Exception.class, () -> processaQuantidadeClientes.processa(""));
		assertEquals(messageExpected, exception.getMessage());
	}
	
	@Test
	void processaQuantidadeVendedoresFail() throws Exception {
		ProcessaQuantidadeVendedores processaQuantidadeVendedores = new ProcessaQuantidadeVendedores();
		String messageExpected = "O campo conteudoArquivo não pode ser vazio!";
		Throwable exception = assertThrows(Exception.class, () -> processaQuantidadeVendedores.processa(""));
		assertEquals(messageExpected, exception.getMessage());
	}

	@Test
	void processaVendaMaisCaraFail() throws Exception {
		ProcessaVendaMaisCara processaVendaMaisCara = new ProcessaVendaMaisCara();
		String messageExpected = "O campo conteudoArquivo não pode ser vazio!";
		Throwable exception = assertThrows(Exception.class, () -> processaVendaMaisCara.processa(""));
		assertEquals(messageExpected, exception.getMessage());
	}
	
	@Test
	void processaPiorVendedorFail() throws Exception {
		ProcessaPiorVendedor processaPiorVendedor = new ProcessaPiorVendedor();
		String messageExpected = "O campo conteudoArquivo não pode ser vazio!";
		Throwable exception = assertThrows(Exception.class, () -> processaPiorVendedor.processa(""));
		assertEquals(messageExpected, exception.getMessage());
	}
	
	@Test
	void listaArquivosFail() throws Exception {
		TrataArquivo trataArquivo = new TrataArquivo();
		String messageExpected = "o campo caminhoDiretorio não pode ser vazio!";
		Throwable exception = assertThrows(Exception.class, () -> trataArquivo.listaArquivos(""));
		assertEquals(messageExpected, exception.getMessage());
	}

	@Test
	public void testMain() throws IOException {
	    String[] args = null;
	    final InputStream original = System.in;
	    ExecutaProcessadorArquivos.main(args);
	    System.setIn(original);
	}
	
	@Test
	@After
	void removeArquivoParaTeste() {
		String nomeArquivoEntrada = "01.txt";
		String nomeArquivoSaida = "01.txt";
		String diretorioAtual = System.getProperty("user.dir");
		String diretorioEntrada = "/data/in/";
		String diretorioSaida = "/data/out/";
		File arquivoEntrada = new File(diretorioAtual + diretorioEntrada + nomeArquivoEntrada);
		if (arquivoEntrada.exists()) {
			arquivoEntrada.delete();
		}
		File arquivoSaida = new File(diretorioAtual + diretorioSaida + nomeArquivoSaida);
		if (arquivoSaida.exists()) {
			arquivoSaida.delete();
		}
	}
}