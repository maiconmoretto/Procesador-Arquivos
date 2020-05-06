import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProcessaArquivosTest {

	@Test
	void listaArquivos() {
		String expected = "001ç1234567891234çPedroç50000\n" + 
				"001ç3245678865434çPauloç40000.99\n" + 
				"002ç2345675434544345çJose da SilvaçRural\n" + 
				"002ç2345675433444345çEduardo PereiraçRural\n" + 
				"003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro\n" + 
				"003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo";
		String result = "001ç1234567891234çPedroç50000\n" + 
				"001ç3245678865434çPauloç40000.99\n" + 
				"002ç2345675434544345çJose da SilvaçRural\n" + 
				"002ç2345675433444345çEduardo PereiraçRural\n" + 
				"003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro\n" + 
				"003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo";
		assertEquals(expected, result);
	}

}
