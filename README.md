# True Instagram Server
### Backend do app mobile Clonegram

Este projeto foi desenvolvido com o objetivo de praticar o desenvolvimento de REST API'S com Java 
e Spring Boot, além de técnicas de perfil de projeto e dockerização.

Este projeto provê para o aplicativo as funções de criar stories, criar posts para o feed e incrementar
a quantidade de likes e comentários em um post. Dessa forma, torna o aplicativo mais dinâmico e garante
a prática de troca de informações usando REST API.

__Link para o repositório do Clonegram:__ [Clonegram App](https://github.com/MarcRuas/Clonegram)

## Tecnologias Utilizadas

- __Java e Spring Boot__
- __Maven__
- __Banco de dados MongoDB__
- __Docker e Docker Compose__

## Como usar?
Se quiser testar o projeto, siga esses passos:

### Requisitos mínimos:

- __Ter instalado, na sua máquina, o Docker e Docker Compose__
- __Ter instalado o Git CLI__

Link para a documentação oficial do Docker: 
[Documentação Docker Engine](https://docs.docker.com/engine/install/)

Link para download do Git CLI:
[Download Git](https://git-scm.com/downloads)

## Passo 1: Clonagem do Repositório

Para começar, navegue até o diretório de sua preferência e digite o seguinte comando:

- ### Para clonar usando SSH
```shell
git clone git@github.com:weblerson/true-instagram-server.git
```

- ### Para clonar usando HTTPS
```shell
git clone https://github.com/weblerson/true-instagram-server.git
```

## Passo 2: Subir os Serviços
Antes de avançar, é necessário entender como será a execução do projeto.

Ele possui, públicos, dois perfis: um de testes e um de desenvolvimento, que é o mais próximo possível
de um perfil de produção.

No perfil de testes, todos os testes da aplicação serão executados. No perfil de desenvolvimento, os
serviços irão subir e estarão disponíveis como se fosse a aplicação real.

No perfil de desenvolvimento, além do serviço da aplicação e do banco de dados, tem o serviço do
servidor web, que é responsável por redirecionar as requisições para o serviço do Spring e
servir os arquivos de mídia que são gerados. É para esse serviço que devemos fazer as requisições.

__Então, usando o Docker Compose, subimos o perfil de testes executando o seguinte comando:__
```shell
docker compose -f docker-compose.test.yaml --env-file test.env up --build
```

__Para subir o perfil de desenvolvimento, o seguinte comando deve ser executado em seu terminal:__
```shell
docker compose -f docker-compose.dev.yaml --env-file dev.env up --build
```

- ### Vale lembrar que a primeira execução vai ser mais demorada, pois o Maven precisa baixar as dependências necessárias

## Pronto!
Agora, seus que seus serviços estão no ar...
