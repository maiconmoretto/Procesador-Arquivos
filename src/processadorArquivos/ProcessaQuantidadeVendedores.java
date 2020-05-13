package processadorArquivos;

import java.util.ArrayList;
import java.util.List;

public class ProcessaQuantidadeVendedores implements Processa {

	@Override
	public String processa(String conteudoArquivo) throws Exception {
		if (conteudoArquivo == "") {
			throw new Exception("O campo conteudoArquivo não pode ser vazio!");
		} 
		List<String> cpfVendedores = new ArrayList<String>();
		String[] linhas = conteudoArquivo.split("\n");
		String tipoCliente = "001";
		for (int j = 0; j < linhas.length; j++) {
			String identificadorTipoDado = linhas[j].substring(0, 3);
			if (identificadorTipoDado.equals(tipoCliente)) {
				String[] colunas = linhas[j].split("ç");
				String cpf = colunas[1];
				if (!cpfVendedores.contains(cpf)) {
					cpfVendedores.add(cpf);
				}
			}
		}
		return "Total de vendedore(s) = " + cpfVendedores.size();
	}
}
