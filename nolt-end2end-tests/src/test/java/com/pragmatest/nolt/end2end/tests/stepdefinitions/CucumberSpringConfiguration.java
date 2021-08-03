package com.pragmatest.nolt.end2end.tests.stepdefinitions;

import com.pragmatest.nolt.end2end.tests.common.TestConfiguration;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = TestConfiguration.class)
public class CucumberSpringConfiguration {
}
