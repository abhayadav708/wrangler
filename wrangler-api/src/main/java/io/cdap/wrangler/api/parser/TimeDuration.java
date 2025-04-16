package io.cdap.wrangler.api.parser;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeDuration extends Token {
  private static final Pattern PATTERN = Pattern.compile("(?i)([0-9]*\\.?[0-9]+)\\s*(ms|s|m|h)");
  private final long millis;

  public TimeDuration(String value) {
    super("TIME_DURATION", value);
    Matcher matcher = PATTERN.matcher(value.trim());
    if (!matcher.matches()) {
      throw new IllegalArgumentException("Invalid time duration format: " + value);
    }

    double number = Double.parseDouble(matcher.group(1));
    String unit = matcher.group(2).toLowerCase(Locale.ROOT);

    switch (unit) {
      case "ms": this.millis = (long) number; break;
      case "s":  this.millis = (long) (number * 1000); break;
      case "m":  this.millis = (long) (number * 60 * 1000); break;
      case "h":  this.millis = (long) (number * 60 * 60 * 1000); break;
      default: throw new IllegalArgumentException("Unsupported time unit: " + unit);
    }
  }

  public long getMillis() {
    return millis;
  }

  public double getSeconds() {
    return millis / 1000.0;
  }

  @Override
  public String toString() {
    return millis + " ms";
  }
}

