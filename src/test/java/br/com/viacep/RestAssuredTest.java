package br.com.viacep;

import br.com.viacep.settings.ExtentReportSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

class RestAssuredTest extends ExtentReportSetup {

    @BeforeAll
    public static void setupAll() {
        setupExtentReports();
        RestAssured.baseURI = "https://viacep.com.br/ws/";
    }

    @Test
    void testeConsultarCEPValido() throws IOException {
        List<String> ceps = Files.readAllLines(Paths.get("./src/test/resources/cepsValidos.txt"));
        for (String cep : ceps) {
            try {
                extentTest = extent.createTest("Teste de consulta de CEP válido - CEP: " + cep);
                Response response = RestAssured
                        .when()
                        .get(cep + "/json")
                        .then()
                        .statusCode(200)
                        .body(matchesJsonSchemaInClasspath("get-cep-schema.json"))
                        .extract()
                        .response();
                extentTest.info("CEP consultado: " + cep);
                extentTest.info("Resposta: " + response.getBody().asString());
            } catch (AssertionError e) {
                extentTest.fail("Teste falhou: " + e.getMessage());
            }
        }
    }

    @Test
    void testeConsultarCEPInvalido() throws IOException {
        List<String> ceps = Files.readAllLines(Paths.get("./src/test/resources/cepsInvalidos.txt"));
        for (String cep : ceps) {
            try {
                extentTest = extent.createTest("Teste de consulta de CEP inválido - CEP: " + cep);
                Response response = RestAssured
                        .when()
                        .get(cep + "/json")
                        .then()
                        .statusCode(200)
                        .body("erro", equalTo(true))
                        .extract()
                        .response();
                extentTest.info("CEP consultado: " + cep);
                extentTest.info("Resposta: " + response.getBody().asString());
            } catch (AssertionError e) {
                extentTest.fail("Teste falhou: " + e.getMessage());
            }
        }
    }

    @Test
    void testeConsultarCEPIncorreto() {
        String cep = "x";
        try {
            extentTest = extent.createTest("Teste de consulta de CEP incorreto - CEP: " + cep);
            Response response = RestAssured
                    .when()
                    .get(cep + "/json")
                    .then()
                    .statusCode(400)
                    .extract()
                    .response();
            extentTest.info("CEP consultado: " + cep);
            extentTest.info("Resposta: " + response.getBody().asString());
        } catch (AssertionError e) {
            extentTest.fail("Teste falhou: " + e.getMessage());
        }
    }
}
