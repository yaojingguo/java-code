package scott.forthedition;

import java.util.ArrayList;
import java.util.List;

public class Anno {

  public static void main(String[] args) {
    work();
  }

  @SuppressWarnings("unchecked")
  static void work() {
    List container = new ArrayList();
    container.add(new Integer(10));
  }

}
