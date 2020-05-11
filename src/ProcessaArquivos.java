import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;

public class ProcessaArquivos {

	/**
	 * Método para listar os arquivos de um diretório pelo nome do diretório.
	 * 
	 * @author Maicon Moretto
	 * @param caminhoDiretorio String -Nome do caminho do arquivo a ser lido.
	 * @return String - Arquivos do diretório
	 * @throws Exception
	 */
	public String listaArquivos(String caminhoDiretorio) throws Exception {
		if (caminhoDiretorio == "") {
			throw new Exception("o campo caminhoDiretorio não pode ser vazio!");
		}
		String conteudoDiretorio = "";

		File file = new File(caminhoDiretorio);
		File afile[] = file.listFiles();
		int i = 0;
		for (int j = afile.length; i < j; i++) {
			File arquivos = afile[i];
			conteudoDiretorio += arquivos.getName();
		}
		return conteudoDiretorio;
	}

	/**
	 * Método para ler o conteúdo do arquivo pelo nome.
	 * 
	 * @author Maicon Moretto
	 * @param nome             String -Nome do arquivo a ser lido.
	 * @param caminhoDiretorio String -Nome do caminho do arquivo a ser lido.
	 * @return String - Conteúdo do arquivo
	 * @throws Exception
	 */
	public String leArquivoPorNome(String caminhoDiretorio, String nomeArquivo) throws Exception {
		if (caminhoDiretorio == "" || nomeArquivo == "") {
			throw new Exception("o campo caminhoArquivo e nomeArquivo não pode ser vazio!");
		}
		String conteudoArquivo = "";
		try {
			FileReader arq = new FileReader(caminhoDiretorio + nomeArquivo);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			conteudoArquivo +=  linha + "\n";
			while (linha != null) {
				linha = lerArq.readLine();
				conteudoArquivo += linha + "\n" ;
			}
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n" + e.getMessage(), e.getMessage());
		}
		return conteudoArquivo;
	}

	/**
	 * Método para processa o Arquivo arquivo pelo nome.
	 * 
	 * @author Maicon Moretto
	 * @param nomeArquivo      String -Nome do arquivo a ser lido.
	 * @param caminhoDiretorio String -Nome do caminho do arquivo a ser lido.
	 * @return String - Mensagem de Sucesso ao processar os arquivos
	 * @throws Exception
	 */
	public String processaArquivo(String caminhoDiretorio, String nomeArquivo) throws Exception {
		if (caminhoDiretorio == "" || nomeArquivo == "") {
			throw new Exception("o campo caminhoArquivo e nomeArquivo não pode ser vazio!");
		}
		try {
			String conteudoArquivo = this.leArquivoPorNome(caminhoDiretorio, nomeArquivo);
			ProcessaQuantidadeClientes quantidadeClientes = new ProcessaQuantidadeClientes();
			ProcessaQuantidadeVendedores quantidadeVendedores = new ProcessaQuantidadeVendedores();
			ProcessaPiorVendedor piorVendedor = new ProcessaPiorVendedor();
			ProcessaVendaMaisCara vendaMaisCara = new ProcessaVendaMaisCara();
			String nomeRelatorio = nomeArquivo.replace(".txt", "-processado.txt");
			String conteudoRelatorio = "";
			conteudoRelatorio += quantidadeClientes.processa(conteudoArquivo) + "\n";
			conteudoRelatorio += quantidadeVendedores.processa(conteudoArquivo) + "\n";
			conteudoRelatorio += piorVendedor.processa(conteudoArquivo) + "\n";
			conteudoRelatorio += vendaMaisCara.processa(conteudoArquivo);
			if(!this.criaArquivoRelatorio(nomeRelatorio, conteudoRelatorio)) {
				throw new Exception("Erro ao criar o relatório!");
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return "Arquivos processados com sucesso!";
	}


	/**
	 * Método para cria Arquivo de Relatório.
	 * 
	 * @author Maicon Moretto
	 * @param nomeArquivo      String -Nome do arquivo a ser lido.
	 * @param conteudoRelatorio String -Conteudo a ser inserido no relatório.
	 * @return String - Trues
	 * @throws Exception
	 */
	public boolean criaArquivoRelatorio(String nomeArquivo, String conteudoRelatorio) throws Exception {
		if (nomeArquivo == "" || conteudoRelatorio == "") {
			throw new Exception("o campo conteudoRelatorio e nomeArquivo não pode ser vazio!");
		}
		try {
			String diretorioAtual = System.getProperty("user.dir");
			FileWriter file = new FileWriter(diretorioAtual + "/data/out/" + nomeArquivo);
			PrintWriter gravarArq = new PrintWriter(file);
			gravarArq.printf(conteudoRelatorio);
			file.close();
		} catch (Exception e) {
			throw new Exception(e);
		}
		return true;
	}

}
