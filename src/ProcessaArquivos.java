import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ProcessaArquivos {
	
	 /**Método para lsitar os arquivos de um diretório pelo nome.
	  * @author Maicon Moretto
	 * @param nome String -Nome do diretório a ser lido.
	 * @return String - Arquivos do diretório
	 */
	public String listaArquivos (String nome) throws IOException {
		
		String path = "/home/maicon/eclipse-workspace/Procesador de Arquivos/data";
		try (Stream<Path> paths = Files.walk(Paths.get(path))) {
		    paths
		        .filter(Files::isRegularFile)
		        .forEach(System.out::println);
		} 
		
		
		return "001ç1234567891234çPedroç50000\n" + 
		"001ç3245678865434çPauloç40000.99\n" + 
		"002ç2345675434544345çJose da SilvaçRural\n" + 
		"002ç2345675433444345çEduardo PereiraçRural\n" + 
		"003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro\n" + 
		"003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo";
	}
	

	 /**Método para ler o conteúdo do arquivo pelo nome.
	  * @author Maicon Moretto
	 * @param nome String -Nome do arquivo a ser lido.
	 * @return String - Conteúdo do arquivo
	 */
	public String leArquivoPorNome(String caminhoArquivo, String nomeArquivo) {
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
		/*
		return "001ç1234567891234çPedroç50000\n" + 
		"001ç3245678865434çPauloç40000.99\n" + 
		"002ç2345675434544345çJose da SilvaçRural\n" + 
		"002ç2345675433444345çEduardo PereiraçRural\n" + 
		"003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro\n" + 
		"003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo";
		*/
	}
	

}
