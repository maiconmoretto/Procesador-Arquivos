
public class ProcessaVendaMaisCara implements Processa {

	@Override
	public String processa(String conteudoArquivo) {
		String[] linhas = conteudoArquivo.split("\n");
		int idUltimaVenda = 0;
		double totalVendaMaisCara = 0;
		double totalUltimaVenda = 0;
		int idVendaMaisCara = 0;
		String tipoVenda = "003";
		for (int j = 0; j < linhas.length; j++) {
			String identificadorTipoDado = linhas[j].substring(0, 3);
			if (identificadorTipoDado.equals(tipoVenda)) {
				String[] colunas = linhas[j].split("ç");
				String dadosVenda = colunas[2];
				int idVendaAtual = Integer.valueOf(colunas[1]);
				double totalVendaAtual = 0;
				String[] listagemItensNota = dadosVenda.split(",");
				for (int l = 0; l < listagemItensNota.length; l++) {
					double quantidade = Double.valueOf(listagemItensNota[l].split("-")[1]);
					double preco = Double.valueOf(listagemItensNota[l].split("-")[2].replace("]", ""));
					totalVendaAtual = totalVendaAtual + (quantidade * preco);
				}
				if (totalVendaMaisCara < totalVendaAtual) {
					idVendaMaisCara = idVendaAtual;
					totalVendaMaisCara = totalVendaAtual;
				}
				idUltimaVenda = idVendaAtual;
				totalUltimaVenda = totalVendaAtual;
			}
		}
		return "Id da venda mais cara " + idVendaMaisCara;
	}

}
