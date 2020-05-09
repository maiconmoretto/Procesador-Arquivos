import java.util.ArrayList;
import java.util.List;

public class ProcessaQuantidadeVendedores implements Processa {

	@Override
	public String processa(String conteudoArquivo) {
		List<String> cpfVendedores = new ArrayList<String>();
		int quantidadeVendedores = 0;

		int index = conteudoArquivo.indexOf("001ç");
		while (index >= 0) {
			int inicioCpf = (index + 4);
			int fimCpf = (inicioCpf + 11);
			CharSequence cpf = conteudoArquivo.subSequence(inicioCpf, fimCpf);
			if (!cpfVendedores.contains(cpf)) {
				cpfVendedores.add((String) cpf);
				quantidadeVendedores++;
			}
			index = conteudoArquivo.indexOf("002ç", index + 1);
		}

		return "Total de vendedore(s) " + quantidadeVendedores;
	}

}
