package com.pragmatest.nolt.e2e.tests.stepdefinitions;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import com.pragmatest.nolt.e2e.tests.common.TestConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = TestConfiguration.class)
public class CucumberSpringConfiguration {
}
