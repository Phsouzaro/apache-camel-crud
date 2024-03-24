# Aplicação CRUD de Produtos com Apache Camel

Esta aplicação é um exemplo de uma aplicação CRUD (Create, Read, Update, Delete) para gerenciar produtos, utilizando Apache Camel para a integração entre o serviço e o banco de dados. A aplicação foi criada com o objetivo de estudos, demonstrando a utilização do Apache Camel para definir rotas de processamento e a integração com um banco de dados para persistência de dados.

## Rota Principal

A aplicação define uma rota principal `ProdutoRouter` que gerencia as operações CRUD para produtos. A rota principal é composta por quatro sub-rotas, cada uma responsável por uma operação específica:

- **Salvar Produto**: Permite criar um novo produto no banco de dados.
- **Listar Produtos**: Retorna uma lista de todos os produtos cadastrados.
- **Detalhar Produto**: Retorna os detalhes de um produto específico pelo seu ID.
- **Editar Produto**: Atualiza os detalhes de um produto existente.

### Rota de Salvar Produto

A rota de salvar produto recebe um produto como entrada, processa a requisição através do `ProdutoProcessor` e salva o produto no banco de dados utilizando o `ProdutoBean`.

### Rota de Listar Produtos

A rota de listar produtos retorna uma lista de todos os produtos cadastrados no banco de dados. Caso a lista esteja vazia, uma exceção é lançada.

### Rota de Detalhar Produto

A rota de detalhar produto recebe um ID de produto como entrada, busca o produto correspondente no banco de dados e retorna seus detalhes.

### Rota de Editar Produto

A rota de editar produto recebe um produto com detalhes atualizados e um ID, busca o produto existente no banco de dados pelo ID, atualiza seus detalhes e salva as alterações.

## Serviço / Bean com Lógicas

O `ProdutoBean` é o componente responsável por realizar as operações de persistência no banco de dados. Ele utiliza o `ProdutoRepository` para interagir com o banco de dados, realizando operações como salvar, listar, buscar por ID e editar produtos.

### Métodos

- **salvarProduto**: Salva um novo produto no banco de dados.
- **listarProdutos**: Retorna uma lista de todos os produtos cadastrados.
- **detalharProduto**: Retorna os detalhes de um produto específico pelo seu ID.
- **editarProduto**: Atualiza os detalhes de um produto existente.

## Exemplo de Uso

Para utilizar a aplicação, você pode enviar requisições HTTP para as rotas correspondentes, passando os dados necessários para cada operação. Por exemplo, para salvar um novo produto, você pode enviar uma requisição POST para a rota de salvar produto com o corpo da requisição contendo os detalhes do produto.

## Conclusão

Esta aplicação demonstra a utilização do Apache Camel para definir rotas de processamento e integrar uma aplicação Java com um banco de dados para operações CRUD. É um exemplo prático de como o Apache Camel pode ser utilizado para simplificar a integração entre diferentes sistemas e serviços.
