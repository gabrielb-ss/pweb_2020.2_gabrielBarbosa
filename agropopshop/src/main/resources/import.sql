/*Primeira linha em branco - Spring não tava adicionado os valores*/
INSERT INTO clientes (nome, genero, endereco, cep, profissao, email) VALUES ('Maria da Penha Maia Fernandes', 'feminino', 'rua dos bobos', '123321-111', 'escritora', 'mariaDaPenha@gov.br');
INSERT INTO clientes (nome, genero, endereco, cep, profissao, email) VALUES ('Rex lopes', 'masculino', 'rua da pedra', '455789-111', 'pedreiro', 'olhaapedra@liyue.cn');
INSERT INTO produtos (nome, marca, altura, largura, profundidade, peso, preco) VALUES ('leite em pó', 'nesple', '12cm', '12cm', '12cm', '12g', '12.00');
INSERT INTO dependetes (idPai, nome, dataNascimento, genero) VALUES ('1', 'Rex lopes', parseDateTime('01/02/1467','dd/MM/yyyy'), 'masculino');