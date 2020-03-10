interface CalculationService {
  int calculateInt(String input);
  float calculateFloat(String input);
  long calculateLong(String input);
  double calculateDouble(String input);
}

public class PostfixCalculationService implements CalculationService {
  @Override
  public int calculateInt(String input) {
    return (int) Evaluate.eval(input);
  }

  @Override
  public float calculateFloat(String input) {
    return (float) Evaluate.eval(input);
  }

  @Override
  public long calculateLong(String input) {
    return (long) Evaluate.eval(input);
  }

  @Override
  public double calculateDouble(String input) {
    return Evaluate.eval(input);
  }
}

