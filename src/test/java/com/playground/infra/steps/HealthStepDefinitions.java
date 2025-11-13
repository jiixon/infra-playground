package com.playground.infra.steps;

import com.playground.infra.config.TestcontainersConfig;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import(TestcontainersConfig.class)
public class HealthStepDefinitions {
    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    @When("클라이언트가 GET \\/health API를 요청하면")
    public void 클라이언트가_health_api를_요청하면() {
        response = restTemplate.getForEntity("/health", String.class);
    }

    @Then("응답 상태 코드는 {int} 이어야 한다")
    public void 응답_상태_코드는_이어야_한다(int expectedStatus) {
        assertThat(response.getStatusCode().value()).isEqualTo(expectedStatus);
    }

    @And("응답 본문은 {string} 이어야 한다")
    public void 응답_본문은_이어야_한다(String expectedBody) {
        assertThat(response.getBody()).isEqualTo(expectedBody);
    }
}
