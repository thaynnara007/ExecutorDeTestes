# ExecutorDeTestes

A classe Executor executa testes Junits e gera um array de 0s e 1s, onde 0 representa
teste aceito e 1 teste falho. Nesse contexto, para seu funcionamento, a classe
requer como parâmetro o caminho dos testes a serem executados e a mesma deve ser 
importada para dentro do projeto onde estão os testes.

->executorType2:
Caso o caminho para o teste, como a classe de teste e o método de teste sejam conhecidos
e seja desejado executa-los em uma ordem específica, no branch executorType2 tem uma 
variação do código original que faz isso.

o modelo de entrada segue o seguinte padrão:
"(caminhoParaAClasseDeTeste)/(nomdeDoTeste)$(nomeDaClasse).java>#(qualquerComentarioDesejado)"

-> executorType3:
Executa os testes de uma pasta. O caminho para a pasta é passado como parâmetro

-> executorType4:

recebe como parametro uma string a qual representa varias suites de teste.
cada caminho de teste é representado pelo formato:
"(caminhoParaClasseDeTeste)/(classeDoTeste)$(nomeDoTeste).java#(qualquerComentarioDsejado)"
Cada suite deve ser separada, dentro da string, por ";".
O algoritimo dara como retorno um array de 0s e 1s para cada suite.
