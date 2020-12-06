package scott.forthedition;

import java.util.List;

class Instruction {
  private int time;
  private int from;
  private int to;

  public Instruction(int time, int from, int to) {
    this.time = time;
    this.from = from;
    this.to = to;
  }

  public int getTime() {
    return time;
  }

  public int getFrom() {
    return from;
  }

  public int getTo() {
    return to;
  }

  public boolean getDirection() {
    return to > from;
  }
}

class Elevator {
  private int cur;
  private int dest;

  public Elevator(int cur, int dest) {
    this.cur = cur;
    this.dest = dest;
  }
}

// FIFO

// Nearest Target

// Direction First

public class Sol {
  public void solve(List<Instruction> ins, Elevator ele) {

  }
}
