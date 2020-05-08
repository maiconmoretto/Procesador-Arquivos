import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;


public class ProcessaArquivos {
	
	 /**Método para listar os arquivos de um diretório pelo nome do diretório.
	 * @author Maicon Moretto
	 * @param caminhoArquivo String -Nome do caminho do arquivo a ser lido.
	 * @return String - Arquivos do diretório
	 * @throws Exception 
	 */
	public String listaArquivos (String caminhoDiretorio) throws Exception {
		if (caminhoDiretorio == "") {
			throw new Exception("o campo caminhoDiretorio não pode ser vazio!");
		}
		String conteudoDiretorio = "";

		File file = new File(caminhoDiretorio);
		File afile[] = file.listFiles();
		System.out.println(afile);
		int i = 0;
		for (int j = afile.length; i < j; i++) {
			File arquivos = afile[i];
			System.out.println(arquivos.getName());
			conteudoDiretorio+= arquivos.getName();
		}
				
		return conteudoDiretorio;
	}
	

	 /**Método para ler o conteúdo do arquivo pelo nome.
	 * @author Maicon Moretto
	 * @param nome String -Nome do arquivo a ser lido.
	 * @param caminhoArquivo String -Nome do caminho do arquivo a ser lido.
	 * @return String - Conteúdo do arquivo
	 * @throws Exception 
	 */
	public String leArquivoPorNome(String caminhoArquivo, String nomeArquivo) throws Exception {
		if (caminhoArquivo == "" || nomeArquivo == "") {
			throw new Exception("o campo caminhoArquivo e nomeArquivo não pode ser vazio!");
		}
		String conteudoArquivo= "";
		  try {
		      FileReader arq = new FileReader(caminhoArquivo + nomeArquivo);
		      BufferedReader lerArq = new BufferedReader(arq);
		 
		      String linha = lerArq.readLine();
		
		      while (linha != null) {		     
		        linha = lerArq.readLine();
		        conteudoArquivo+= linha;
		      }
		      arq.close();
		    } catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n" + e.getMessage(),
		          e.getMessage());
		    }
		  return conteudoArquivo;
	}
	

}
