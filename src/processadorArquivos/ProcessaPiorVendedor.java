package processadorArquivos;

public class ProcessaPiorVendedor implements Processa {

	@Override
	public String processa(String conteudoArquivo) {
		String[] linhas = conteudoArquivo.split("\n");
		double totalPiorVendedor = 0;
		String nomePiorVendedor = "";
		String tipoVenda = "003";
		int contadorVendores = 0;
		for (int j = 0; j < linhas.length; j++) {
			String identificadorTipoDado = linhas[j].substring(0, 3);
			if (identificadorTipoDado.equals(tipoVenda)) {
				String[] colunas = linhas[j].split("ç");
				String dadosVenda = colunas[2];
				String nomeVendedorAtual = colunas[3];
				double totalVendedorAtual = 0;
				String[] listagemItensNota = dadosVenda.split(",");
				for (int l = 0; l < listagemItensNota.length; l++) {
					double quantidade = Double.valueOf(listagemItensNota[l].split("-")[1]);
					double preco = Double.valueOf(listagemItensNota[l].split("-")[2].replace("]", ""));
					totalVendedorAtual = totalVendedorAtual + (quantidade * preco);
				}

				if (contadorVendores == 0) {
					totalPiorVendedor = totalVendedorAtual;
					nomePiorVendedor = nomeVendedorAtual;
				} else {
					if (totalPiorVendedor > totalVendedorAtual) {
						totalPiorVendedor = totalVendedorAtual;
						nomePiorVendedor = nomeVendedorAtual;
					}
				}
				contadorVendores++;
			}
		}
		return "Pior vendedor = " + nomePiorVendedor;
	}
}