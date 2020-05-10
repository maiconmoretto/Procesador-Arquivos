import java.util.*;

public class ProcessaQuantidadeClientes implements Processa {

	@Override
	public String processa(String conteudoArquivo) {
		List<String> cnpjClientes = new ArrayList<String>();
		String[] linhas = conteudoArquivo.split("\n");
		String tipoCliente = "002";
		for (int j = 0; j < linhas.length; j++) {
			String identificadorTipoDado = linhas[j].substring(0, 3);
			if (identificadorTipoDado.equals(tipoCliente)) {
				String[] colunas = linhas[j].split("ç");
				String cnpj = colunas[1];
				if (!cnpjClientes.contains(cnpj)) {
					cnpjClientes.add(cnpj);
				}
			}
		}
		return "Total de cliente(s) " + cnpjClientes.size();
	}
}
