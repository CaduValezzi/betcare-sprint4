# BetCare - service-oriented-app-betcare

Solução demonstrativa para identificar e apoiar apostadores em risco.

## Descrição
O crescimento do mercado de apostas no Brasil trouxe aumento em comportamento compulsivo. Este projeto oferece API para registro/login, registro de apostas, avaliação heurística de risco e endpoints para fornecer intervenções (links/recursos), tudo com autenticação JWT e boas práticas SOLID.

## Como rodar
1. Java 17+ e Maven instalados.
2. `mvn clean install`
3. `JWT_SECRET` como variável de ambiente (ex: export JWT_SECRET="minhachaveforte")
4. `mvn spring-boot:run`
5. Swagger UI: http://localhost:8080/swagger-ui/index.html

## Endpoints principais
- `POST /api/auth/register` - body `{ "username":"x", "password":"y" }`
- `POST /api/auth/login` - retorna `{ "token": "..." }`
- `GET /api/profile` - retorna perfil do usuário (auth)
- `GET /api/risk/assess/{userId}` - avalia risco (auth)

## Testes
- Unitários: `mvn -Dtest=*Test test`
- Integração: `mvn verify`

## Observações de segurança
- Stateless JWT, BCrypt para senha, filtros para interceptação.
- Em produção: HTTPS obrigatório, rotação de chave JWT, políticas de rate limit e integrações com serviços de saúde mental.

## Tecnologias
- Java 17, Spring Boot 3.x, Spring Security, JWT (jjwt), Spring Data JPA, H2 (dev), springdoc-openapi, JUnit5, Mockito.
