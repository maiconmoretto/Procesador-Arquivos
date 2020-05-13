# Procesador-Arquivos
Resumo:
Sistema de análise de dados de venda  em Java que irá importar lotes de arquivos e produzir um relatório baseado em informações presentes no mesmo.

Detalhes:
<p>Criar um sistema de análise de dados de venda que irá importar lotes de arquivos e produzir
um relatório baseado em informações presentes no mesmo.</p>
<p>Existem 3 tipos de dados dentro dos arquivos e eles podem ser distinguidos pelo seu
identificador que estará presente na primeira coluna de cada linha, onde o separador de
colunas é o caractere “ç”.</p>
<p>Dados do vendedor</p>
<p>Os dados do vendedor possuem o identificador 001 e seguem o seguinte formato:</p>
<p>001çCPFçNameçSalary</p>
<p>Dados do cliente</p>
<p>Os dados do cliente possuem o identificador 002 e seguem o seguinte formato:</p>
<p>002çCNPJçNameçBusiness Area</p>
<p>Dados de venda</p>
<p>Os dados de venda possuem o identificador 003 e seguem o seguinte formato:</p>
<p>003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name</p>
<p>Exemplo de conteúdo total do arquivo:</p>
<p>001ç1234567891234çPedroç50000</p>
<p>001ç3245678865434çPauloç40000.99</p>
<p>002ç2345675434544345çJose da SilvaçRural</p>
<p>002ç2345675433444345çEduardo PereiraçRural</p>
<p>003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro</p>
<p>003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo</p>
<p>O sistema deverá ler continuamente todos os arquivos dentro do diretório padrão
HOMEPATH/data/in e colocar o arquivo de saída em HOMEPATH/data/out.</p>
<p>No arquivo de saída o sistema deverá possuir os seguintes dados:</p>
<p>• Quantidade de clientes no arquivo de entrada</p>
<p>• Quantidade de vendedores no arquivo de entrada</p>
<p>• ID da venda mais cara•</p>
<p>• O pior vendedor</p>
<p>Requisitos técnicos
<p>•O sistema deve rodar continuamente e capturar novos arquivos assim que eles sejam
inseridos no diretório padrão.</p>
<p>•Você tem total liberdade para escolher qualquer biblioteca externa se achar
necessário.</p>
