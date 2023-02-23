


# Projeto teste Bees

## Startando o projeto:
Acessar a pasta "docker" com o terminal e execute o comando "docker compose up".
As imagens dos projetos serão criadas e alocadas no ambiente docker.

## Testando via postman
Com o ambiente "startado", podemos acessar o endpoint do bff através da url: http://host.docker.internal:8082/gateway/bff-transfer/. Passando os parâmetros necessários para a requisição.

## Trouble Shooting

### Microsserviços não inicializam após "Docker compose up"
Será necessário inicializa-los individualmente.


## Future features
1. Ajustar docker-compose para evitar o conflito dos Microssesrviços no start do ambiente.
2. Incrementar o DLX, salvando informações no banco de dados.
3. Melhorar cobertura de códito e testes unitarios.