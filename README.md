# Kotlin API

Projeto desenvolvido em **Kotlin + Ktor**.  
Inclui endpoints para gerenciar veículos, além de questões de algoritmos e POO (`question01` a `question04`).

---

## Requisitos

- **IntelliJ IDEA** (Community ou Ultimate)
- **JDK 17+**
- **Gradle** (wrapper incluso, IntelliJ detecta automaticamente)

---

## Rodando o projeto no IntelliJ IDEA

1. **Abrir o projeto**
    - Abra o IntelliJ IDEA.
    - Clique em **File → Open...** e selecione a pasta raiz do repositório.
    - O IntelliJ deve detectar automaticamente o projeto **Gradle/Kotlin**.

2. **Configurar JDK**
    - Vá em **File → Project Structure → Project**.
    - Em **SDK**, selecione **Java 17** (ou superior).

3. **Rodar a aplicação**
    - No painel lateral, abra:  
      `src/main/kotlin/com/tinnova/desafio/Application.kt`
    - Clique no botão **Run ▶️** na função `main`.
    - O servidor Ktor subirá em:
      ```
      http://localhost:8080
      ```

---

## Rodando os testes no IntelliJ IDEA

1. No painel lateral, vá até `src/test/kotlin/com/tinnova/desafio/`.
2. Clique com o botão direito em uma classe de teste (ex.: `VehicleRepositoryTest.kt`).
3. Selecione **Run 'VehicleRepositoryTest'**.
    - Para rodar **todos os testes**, clique com o botão direito na pasta `test` → **Run 'Tests in ...'**.

---

## Endpoints principais

- `GET /elections` → Cálculo de votos (parâmetros: `totalVoters`, `validVotes`, `blankVotes` e `nullVotes`).
- `GET /calculate-factorial/{number}` → Cálculo de fatorial para um número.
- `GET /multiples/{limit}` → Soma de múltiplos de 3 ou 5 dado um número (limit).
- `GET /bubble-sort/{values_separated_by_comma}` → Ordenação de vetor dado uma sequencia de números como parâmetro.
#### (Vehicles API)
- `GET /vehicles` → Lista veículos (opcional: filtros `marca` e `ano`).
- `GET /vehicles/{id}` → Busca veículo por ID.
- `POST /vehicles` → Cria novo veículo.
- `PUT /vehicles/{id}` → Atualiza veículo (substituição completa).
- `PATCH /vehicles/{id}` → Atualiza parcialmente um veículo.
- `DELETE /vehicles/{id}` → Remove veículo.

---
 