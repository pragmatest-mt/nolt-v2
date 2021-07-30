package com.pragmatest.nolt.end2end.tests;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = CucumberTests.class)
public class CucumberSpringConfiguration {
}
