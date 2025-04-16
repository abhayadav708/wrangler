package io.cdap.wrangler.api.parser;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ByteSize extends Token {
  private static final Pattern PATTERN = Pattern.compile("(?i)([0-9]*\\.?[0-9]+)\\s*([kmg]?b)");
  private final long bytes;

  public ByteSize(String value) {
    super("BYTE_SIZE", value);
    Matcher matcher = PATTERN.matcher(value.trim());
    if (!matcher.matches()) {
      throw new IllegalArgumentException("Invalid byte size format: " + value);
    }

    double number = Double.parseDouble(matcher.group(1));
    String unit = matcher.group(2).toLowerCase(Locale.ROOT);

    switch (unit) {
      case "b":  this.bytes = (long) number; break;
      case "kb": this.bytes = (long) (number * 1024); break;
      case "mb": this.bytes = (long) (number * 1024 * 1024); break;
      case "gb": this.bytes = (long) (number * 1024 * 1024 * 1024); break;
      default: throw new IllegalArgumentException("Unsupported byte unit: " + unit);
    }
  }

  public long getBytes() {
    return bytes;
  }

  @Override
  public String toString() {
    return bytes + " bytes";
  }
}

