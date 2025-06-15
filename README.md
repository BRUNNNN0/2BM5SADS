# Endpoints e Permissões

## Públicos (acesso sem autenticação)

Estas rotas estão disponíveis para qualquer usuário e **não exigem** um token JWT.

- **POST `/auth/login`**  
  Realiza o login e retorna um token JWT.

- **POST `/auth/register`**  
  Cria um novo usuário no sistema.

---

## Acesso restrito — Apenas para usuários com perfil ADMIN

Estas rotas requerem autenticação com um token JWT válido e só podem ser acessadas por usuários com perfil de administrador.

- **GET `/users`**  
  Lista todos os usuários cadastrados.

- **GET `/users/{id}`**  
  Consulta um usuário específico pelo ID.

- **DELETE `/users/{id}`**  
  Deleta um usuário específico pelo ID.

- **PUT `/users/{id}`**  
  Atualiza os dados de um usuário específico.

---

## Acesso para usuários autenticados (ADMIN ou USER)

Essas rotas exigem autenticação com token JWT válido e estão disponíveis para qualquer usuário logado, seja com perfil de administrador ou usuário comum.

- **GET `/users/getCurrent`**  
  Retorna os dados do usuário atualmente logado.

- **PUT `/users/edit`**  
  Atualiza os próprios dados do usuário logado.

---

## PARA ACESSAR A PARTE RESTRITA

- As rotas públicas (`/auth/login` e `/auth/register`) não exigem autenticação.
- Todas as outras rotas exigem um token JWT válido no cabeçalho da requisição:

```http
Authorization: Bearer <seu_token>
