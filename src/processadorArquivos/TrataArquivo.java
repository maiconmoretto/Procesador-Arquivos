package processadorArquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TrataArquivo {
	/**
	 * Método para listar os arquivos de um diretório pelo nome do diretório.
	 * 
	 * @author Maicon Moretto
	 * @param caminhoDiretorio String -Nome do caminho do arquivo a ser lido.
	 * @return String - Arquivos do diretório
	 * @throws Exception
	 */
	public <T> List<String> listaArquivos(String caminhoDiretorio) throws Exception {
		if (caminhoDiretorio == "") {
			throw new Exception("o campo caminhoDiretorio não pode ser vazio!");
		}
		try {
			List<String> conteudoDiretorio = new ArrayList<String>();

			File file = new File(caminhoDiretorio);
			File afile[] = file.listFiles();
			int i = 0;
			for (int j = afile.length; i < j; i++) {
				File arquivos = afile[i];
				conteudoDiretorio.add(arquivos.getName());
			}
			return conteudoDiretorio;

		} catch (Exception e) {
			throw new Exception(e);
		}
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
			String linha = "";
			while (lerArq.ready()) {
				linha = lerArq.readLine();
				conteudoArquivo += linha + "\n";
			}
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n" + e.getMessage(), e.getMessage());
		}
		return conteudoArquivo;
	}

	public void criaArquivo(String diretorioArquivo, String nomeArquivo, String conteudoArquivo) throws Exception {
		if (diretorioArquivo == "" || nomeArquivo == "") {
			throw new Exception("o campo diretorioArquivo, conteudoArquivo e nomeArquivo não pode ser vazio!");
		}
		try {
			FileWriter arquivo = new FileWriter(diretorioArquivo + nomeArquivo);
			PrintWriter gravarArq = new PrintWriter(arquivo);
			gravarArq.printf(conteudoArquivo);
			arquivo.close();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public void removeArquivo(String diretorioArquivo, String nomeArquivo) throws Exception {
		if (diretorioArquivo == "" || nomeArquivo == "") {
			throw new Exception("o campo diretorioArquivo e nomeArquivo não pode ser vazio!");
		}
		try {
			File arquivo = new File(diretorioArquivo + nomeArquivo);
			if (arquivo.exists()) {
				arquivo.delete();
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}