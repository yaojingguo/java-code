package concurrency;
/**
 * Explain the meaning of the following JLS statement:
 * Any local variable, formal method parameter or exception handler parameter
 * used but not declared in an inner class must be declared final.
 */
public class InnerClass {
  public static void main (String [] args) {}

  public void outerMethod(final String formalMethodParameter) {
    try {
    } catch (final Exception exceptionHandlerParamter) {
      final int localVariable = 10;
      Thread t = new Thread() {
        public void run() {
          // Use local variable
          System.out.println(localVariable);    
          // Use exception handler parameter
          exceptionHandlerParamter.printStackTrace(); 
          // Use formal outerMethod parameter
          System.out.println(formalMethodParameter);    
        }
      };
    }
  }
}
