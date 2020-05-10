
public class ProcessaPiorVendedor implements Processa {

	@Override
	public String processa(String conteudoArquivo) {
		String[] linhas = conteudoArquivo.split("\n");
		String nomeUltimoVendedor = "";
		double totalUltimoVendedor = 0;
		String nomePiorVendedor = "";
		String tipoVenda = "003";
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
				if (totalUltimoVendedor < totalVendedorAtual) {
					nomePiorVendedor = "pior vendedor:  " + nomeUltimoVendedor;
				} else {
					nomePiorVendedor = "pior vendedor: " + nomeVendedorAtual ;
				}			
				nomeUltimoVendedor = nomeVendedorAtual;
				totalUltimoVendedor = totalVendedorAtual;
			}
		}
		return nomePiorVendedor;
	}
}
