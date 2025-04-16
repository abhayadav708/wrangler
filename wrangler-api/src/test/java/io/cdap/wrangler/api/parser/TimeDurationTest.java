package io.cdap.wrangler.api.parser;

import org.junit.Assert;
import org.junit.Test;

public class TimeDurationTest {

  @Test
  public void testParsing() {
    Assert.assertEquals(1000, new TimeDuration("1s").getMillis());
    Assert.assertEquals(1500, new TimeDuration("1500ms").getMillis());
    Assert.assertEquals(60000, new TimeDuration("1m").getMillis());
    Assert.assertEquals(3600000, new TimeDuration("1h").getMillis());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInput() {
    new TimeDuration("5lightyears");
  }
}

