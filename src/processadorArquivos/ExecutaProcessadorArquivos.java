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
				String arquivosProcessados = "Arquivos processados: \n";
				int totalArquivosProcessados = 0;
				try {
					for (String nomeArquivo : trataArquivo.listaArquivos(caminhoDiretorioEntrada)) {
						String conteudoArquivo = trataArquivo.leArquivoPorNome(caminhoDiretorioEntrada, nomeArquivo);
						ProcessaArquivos processaArquivos = new ProcessaArquivos();
						File verificaArquivoJaProcessado = new File(
								caminhoDiretorioSaida + nomeArquivo.replace(".txt", "-processado.txt"));
						if (!verificaArquivoJaProcessado.exists() && !conteudoArquivo.equals("null\n")) {
							arquivosProcessados += nomeArquivo + "\n";
							processaArquivos.processaArquivo(caminhoDiretorioEntrada, nomeArquivo);
							totalArquivosProcessados++;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				arquivosProcessados += "\nEm " + java.time.LocalDateTime.now();
				try {
					if (totalArquivosProcessados > 0) {
						trataArquivo.criaArquivo("logs/", "log-" + java.time.LocalDateTime.now(), arquivosProcessados);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(arquivosProcessados);
				System.out.println("finalizada rotina..." + java.time.LocalDateTime.now());
			}
		};
		timer.scheduleAtFixedRate(timerTask, TEMPO, TEMPO);
	}
}
