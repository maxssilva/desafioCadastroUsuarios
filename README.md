desafioCadastroUsuarios

Perguntas realizadas e respostas:
Durante a implementação de uma nova funcionalidade de software solicitada, quais critérios você avalia e implementa para garantia de qualidade de software?
R:Primeiramente avalio as regras de negócio, se são claras e se estão de acordo com o que o sistema se propõe a fazer, em seguida estudo como a implementação
será realizada, quais classes/entidades/métodos ela irá consumir e se outras deverão ser criadas, durante a implementação procuro na medida do possível seguir
as premissas do clean code, pensando no reaproveitamento, escalabilidade e manutenção do código, após o desenvolvimento da funcinoalidade testo o seu comportamento, 
verificando se está de acordo com o que foi solicitado e se atende aos critérios de aceite pedidos pelo cliente, em seguida desenvolvo os testes unitários/integração, 
visando assim uma entrega de qualidade.



Em qual etapa da implementação você considera a qualidade de software?
Em todo o ciclo de desenvolvimento, desde o refinamento com o cliente até a finalização dos testes.




Sobre o Projeto:

O presente projeto tem por objetivo criar um crud com 2 entidades, Usuário e Endereço.
A entidade Usuário possui como atributos: Nome, Data de Nascimento e Endereço, 
A entidade Endereço possui Logradouro, Cep, Número e Cidade, Usuário e se é o endereço principal.

Existe um relacionamento bidirecional entre as entidades, na qual um usuário pode ter vários endereços, e um endereço pode ter apenas um usuário.
As regras de negócio englobam alguns detalhes:
O cadastro do usuário deve preceder ao cadastro do endereço

Um Usuário pode ser criado e editado, porém o sistema não deixa ser cadastrado 2 usuários iguais.

Um mesmo endereço pode ser cadastrado para mais de um usuário.

O Endereço pode ser editado em todos os aspectos, porém para um mesmo usuário ele não pode ser atribuido em duplicidade.

No cadastro de um endereço ele informa através de um Boolean se aquele endereço é o principal, caso for e exista algum endereço no cadastro que seja principal
automaticamente ele seta o valor de endereço principal ao antigo como false.

Os testes unitários estão em desenvolvimento.

Modelos de requisição:

Cadastro de Usuário:

{
    "name": "Nome e Sobrenome
    "birthday": "1986-06-23T18:25:43.511Z"        
}

Cadastro de Endereço:

{
    "street": " Miguel Barchaaaaaa",
    "cep": 38402055,
    "number": 53,
    "city": "Uberlândia",
    "mainAddress": true
  }
  


Tecnologias Utilizadas:
Java versão 8, 
Banco de dados H2,
Maven,
Postman para teste da API
JUnit para construção dos testes unitários
