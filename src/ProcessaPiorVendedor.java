
public class ProcessaPiorVendedor implements Processa {

	@Override
	public String processa(String conteudoArquivo) {
		String[] linhas = conteudoArquivo.split("\n");
		String piorVendedor = "";
		String tipoVenda = "003";
		for (int j = 0; j <= linhas.length; j++) {
			String[] ultimoVendedor = { "Paulo", "2" };
			String identificadorTipoDado = linhas[j].substring(0, 3);
			if (identificadorTipoDado.contains(tipoVenda)) {

				String[] colunas = linhas[j].split("ç");
				String dadosVenda = colunas[2];
				String nomeVendedor = colunas[3];
				int totalVendedor = 0;

				String[] listagemItensNota = dadosVenda.split(",");
				for (int l = 0; l < listagemItensNota.length; l++) {
					String idItem = listagemItensNota[l].split("-")[0].replace("[", "");
					String quantidade = listagemItensNota[l].split("-")[1];
					String preco = listagemItensNota[l].split("-")[2].replace("]", "");
					System.out.println("iditem " + idItem);

					System.out.println("quantidade " + quantidade);

					System.out.println("preco " + preco);
//					totalVendedor = totalVendedor + ((int)quantidade * (int)preco);
					totalVendedor = 1;
					piorVendedor = nomeVendedor;
				}
			}
		}
		System.out.println("pior" + piorVendedor);
		return piorVendedor;
	}

}
