package com.polarbookshop.catalogservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "polar")
public class PolarProperties {

  private String greeting;
  @SuppressWarnings("unused")
  private TestData testdata = new TestData();

  public String getGreeting() {
    return greeting;
  }

  public void setGreeting(String greeting) {
    this.greeting = greeting;
  }
  
  public TestData getTestdata() {
    return testdata;
  }

  public void setTestdata(TestData testdata) {
    this.testdata = testdata;
  }

  public static class TestData{
    private boolean enabled;

    public boolean isEnabled() {
      return enabled;
    }

    public void setEnabled(boolean enabled) {
      this.enabled = enabled;
    }
    
  }
  
}
