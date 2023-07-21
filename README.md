# Projeto ViaCEP Test

Este projeto é um exemplo de teste automatizado usando Rest Assured com relatório ExtentReport.

## Tecnologias Utilizadas

- Java
- Gradle
- Rest Assured
- ExtentReport

## Estrutura do Projeto

```
viaceptest
├── report.html
└── src
    └── test
        ├── java
        │   └── br
        │       └── com
        │           └── viacep
        │               └── RestAssuredTest.java
        └── resources
            ├── cepsInvalidos.txt
            ├── cepsValidos.txt
            └── get-cep-schema.json
```

## Executando os Testes

Para executar os testes, abra o terminal na raiz do projeto e execute o seguinte comando:

```bash
./gradlew clean test
```

## Relatório de Testes

Os relatórios dos testes são gerados utilizando o ExtentReport e podem ser encontrados no seguinte caminho:

```
viaceptest/report.html
```

O relatório é salvo no formato HTML e pode ser visualizado em um navegador da web.