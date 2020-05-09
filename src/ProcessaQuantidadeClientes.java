import java.util.*;

public class ProcessaQuantidadeClientes implements Processa {

	@Override
	public String processa(String conteudoArquivo) {
		List<String> cnpjClientes = new ArrayList<String>();
		int quantidadeClientes = 0;

		int index = conteudoArquivo.indexOf("002ç");
		while (index >= 0) {
			int inicioCnpj = (index + 4);
			int fimCnpj = (inicioCnpj + 14);
			CharSequence cnpj = conteudoArquivo.subSequence(inicioCnpj, fimCnpj);
			if (!cnpjClientes.contains(cnpj)) {
				cnpjClientes.add((String) cnpj);
				quantidadeClientes++;
			}
			index = conteudoArquivo.indexOf("002ç", index + 1);
		}

		return "Total de cliente(s) " + quantidadeClientes;
	}
}
