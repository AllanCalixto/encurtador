# Sistema de Encurtamento de URLs


## Explicação do projeto
https://www.youtube.com/

## Funcionalidades

- **Encurtamento de URLs:** Os usuários podem inserir uma URL longa e obter uma URL encurtada correspondente.
- **Redirecionamento:** As URLs encurtadas são redirecionadas para suas URLs originais.
- **Estatísticas:** Os usuários podem ver estatísticas sobre as URLs encurtadas, incluindo o número de acessos.

## Tecnologias Utilizadas

- Java
- Spring Boot
- MySQL
- Docker

## Configuração

1. **Clonar o Repositório:** Clone este repositório em sua máquina local.
2. **Configurar o Banco de Dados:** Configure seu banco de dados de acordo com as configurações em `application.properties`.
3. **Executar o Aplicativo:** Execute o aplicativo usando sua IDE ou o Maven: `mvn spring-boot:run`.

## Uso

- Para encurtar uma URL, envie uma solicitação POST para `/encurtador/criar` com a URL longa no corpo da solicitação.
- Para acessar uma URL encurtada, basta abrir a URL encurtada no navegador.
- Para visualizar as 10 URLs mais acessadas, acesse `/encurtador/top10`.

