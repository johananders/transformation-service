package com.wordsmith.transformation.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.ImmutableList;
import com.wordsmith.transformation.api.model.TransformationRequest;
import com.wordsmith.transformation.api.model.TransformationResponse;
import com.wordsmith.transformation.dao.TransformationRepository;
import java.net.URI;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TransformationControllerIntegrationTest {

    private static final String API_VERSION = "v1";

    @LocalServerPort
    private int port;
    @Autowired
    private TransformationRepository transformationRepository;
    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void before() {
        transformationRepository.deleteAll();
    }

    @Test
    public void createTransformation() {
        final ResponseEntity<TransformationResponse> response = restTemplate.exchange(
            getBaseUri(),
            HttpMethod.POST,
            new HttpEntity<>(TransformationRequest.of("abc")),
            TransformationResponse.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        final TransformationResponse body = response.getBody();
        assertThat(body.getTransformed()).isEqualTo("cba");
        assertThat(response.getHeaders().get("location"))
            .isEqualTo(ImmutableList.of(getBaseUri() + "/" + body.getId()));
    }

    @Test
    public void getTransFormation() {
        final ResponseEntity<TransformationResponse> createdResponse = restTemplate.postForEntity(
            URI.create(getBaseUri()),
            TransformationRequest.of("abc"),
            TransformationResponse.class
        );

        assertThat(createdResponse.getBody()).isNotNull();

        final ResponseEntity<TransformationResponse> getResponse = restTemplate.getForEntity(
            getBaseUri() + "/" + createdResponse.getBody().getId(),
            TransformationResponse.class
        );

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody()).isNotNull();
    }

    @Test
    public void validateResponseJson() throws JSONException {
        final String expectedJson = "{id:1,original:'abc',transformed:'cba'}";

        final ResponseEntity<String> response = restTemplate.exchange(
            getBaseUri(),
            HttpMethod.POST,
            new HttpEntity<>(TransformationRequest.of("abc")),
            String.class
        );

        JSONAssert.assertEquals(expectedJson, response.getBody(), true);
    }

    @Test
    public void getUnknownIdshouldReturn404() {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity(
            getBaseUri() + "/10001",
            String.class
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private String getBaseUri() {
        return "http://localhost:" + port + "/transformations/" + API_VERSION;
    }
}