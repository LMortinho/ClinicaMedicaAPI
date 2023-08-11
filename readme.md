<h1>Introdução</h1>
Esta API utiliza o Framework Spring Boot 3 e parte do código foi criado a partir de aulas da Alura.<br>
A Criação e exposição deste código é apenas para fins educacionais e não lucrativos.

<h1>Funcionalidades</h1>
- Controllers com os métodos, GET, POST, PUT e DELETE das seguintes entidades:
- Médicos
- Pacientes
- Consultas
- Usuário (Autenticação)

<h1>Mais informações:</h1>
<h2>Autenticação e Segurança:</h2>
- A Api é RESTless, portanto, não há sessões, sempre que um usuário logar, será necessário utilizar um HEADER com seu Token de autenticação.
- Para o método de Autenticação foi utilizado o JSON web token (JWT) do usuário auth0 e os JWT expiram em 2h. 
- Em todos os Controllers que manuseiam o Banco de Dados (Exceto o AutenticacaoController), foi configurado via filter o bloqueio de todas as requisições sem o Header com o token de autorização válido.
- As senhas da entidade usuário, são encriptadas utilizando o "Bcrypt".
- Por medidas de segurança, foi desbilitado o JSON de Stacktrace que é retornado no corpo das requisições, para evitar exposição e vulnerabilidade do código.
<h2>Controllers<h2>
- Cada Controller possui as boas práticas a respeito do HTTP response status code (Codes 2xx, 4xx, 5xx), utilizando o ResponseEntity.
- Foi realizado o tratamento das Exceptions para retornar os devidos response status code, em caso de uma exception.
- Foi utilizada a Swagger UI para facilitar o uso e entendimento dos Controllers.
