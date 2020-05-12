package processadorArquivos;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class ExecutaProcessadorArquivos {

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
						File verificaArquivoJaProcessado = new File(
								caminhoDiretorioSaida + nomeArquivo.replace(".txt", "-processado.txt"));
						if (!verificaArquivoJaProcessado.exists() && !conteudoArquivo.equals("null\n")) {
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
}