interface Strategy {
  doAlgorithm(data: string[]): string[];
}

class Context {
  private strategy: Strategy;
}