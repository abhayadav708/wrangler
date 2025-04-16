package io.cdap.wrangler.api.parser;

import org.junit.Assert;
import org.junit.Test;

public class ByteSizeTest {

  @Test
  public void testParsing() {
    Assert.assertEquals(1024, new ByteSize("1KB").getBytes());
    Assert.assertEquals(1048576, new ByteSize("1MB").getBytes());
    Assert.assertEquals(10 * 1024, new ByteSize("10KB").getBytes());
    Assert.assertEquals(2L * 1024 * 1024 * 1024, new ByteSize("2GB").getBytes());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInput() {
    new ByteSize("123XY");
  }
}

