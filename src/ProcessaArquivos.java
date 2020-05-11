import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.sql.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.io.File;
import java.io.IOException;

public class ProcessaArquivos {

	public static void main(String args[]) {
		final long TEMPO = (1000);
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				System.out.println("iniciada rotina..." + java.time.LocalDateTime.now());
				TrataArquivo trataArquivo = new TrataArquivo();
				String caminhoDiretorioEntrada = "data/in/";
				String caminhoDiretorioSaida = "data/out/";
				try {
					for (String nomeArquivo : trataArquivo.listaArquivos(caminhoDiretorioEntrada)) {
						String conteudoArquivo = trataArquivo.leArquivoPorNome(caminhoDiretorioEntrada, nomeArquivo);

						ProcessaArquivos processaArquivos = new ProcessaArquivos();
						File verificaArquivoJaProcessado = new File(caminhoDiretorioSaida + nomeArquivo.replace(".txt", "-processado.txt"));
						if (!verificaArquivoJaProcessado.exists()
								&& !conteudoArquivo.equals("null\n")) {
							System.out.println("nome arquivo sendo processado = " + nomeArquivo);
							System.out.println("conteudo arquivo sendo processado = " + conteudoArquivo);

							System.out.println("tamanho arquivo sendo processado = " + conteudoArquivo.length());
							processaArquivos.processaArquivo(caminhoDiretorioEntrada, nomeArquivo);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("finalizada rotina..." + java.time.LocalDateTime.now());

			}
		};
		timer.scheduleAtFixedRate(timerTask, TEMPO, TEMPO);
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
			TrataArquivo trataArquivo = new TrataArquivo();
			String conteudoArquivo = trataArquivo.leArquivoPorNome(caminhoDiretorio, nomeArquivo);
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
			if (!this.criaArquivoRelatorio(nomeRelatorio, conteudoRelatorio)) {
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
	 * @param nomeArquivo       String -Nome do arquivo a ser lido.
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
