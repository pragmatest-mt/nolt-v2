package com.pragmatest.nolt.e2e.tests.stepdefinitions;

import com.pragmatest.nolt.e2e.tests.common.TestConfiguration;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = TestConfiguration.class)
public class CucumberSpringConfiguration {
}
