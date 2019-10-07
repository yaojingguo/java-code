package concurrency;

// JAVA program to demonstrate execution on Cyclic Barrier

import com.google.common.base.Verify;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.Random;

class Computation1 implements Runnable {
  public int product = 0;

  public void run() {
    try {
      System.out.println("computing product");
      Thread.sleep(1000 + new Random().nextInt(1000));
      product = 2 * 3;
      CyclicBarrierExamples.newBarrier.await();
      System.out.println("barrier crossed");
    } catch (InterruptedException | BrokenBarrierException e) {
      e.printStackTrace();
    }
  }
}

class Computation2 implements Runnable {
  public int sum = 0;

  public void run() {
    Verify.verify(!CyclicBarrierExamples.newBarrier.isBroken(), "The barrier is broken");
    try {
      System.out.println("computing sum");
      Thread.sleep(1000 + new Random().nextInt(1000));
      sum = 10 + 20;
      CyclicBarrierExamples.newBarrier.await(3000, TimeUnit.MILLISECONDS);
      System.out.println("barrier crossed");
      Verify.verify(
          CyclicBarrierExamples.newBarrier.getNumberWaiting() == 0,
          "Number of parties waiting at the barrier should be 0");
    } catch (InterruptedException | BrokenBarrierException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
    }
  }
}

public class CyclicBarrierExamples implements Runnable {
  public static CyclicBarrier newBarrier = new CyclicBarrier(3);

  public static void main(String[] args) {
    // parent thread
    CyclicBarrierExamples test = new CyclicBarrierExamples();

    Thread t1 = new Thread(test);
    t1.start();
  }

  public void run() {
    oneTime();

    // Resetting the newBarrier
    newBarrier.reset();
    System.out.println("----------------------------------------");

    oneTime();
  }

  private void oneTime() {
    System.out.println(
        "Number of parties required to trip the barrier = " + newBarrier.getParties());


    // objects on which the child thread has to run
    Computation1 comp1 = new Computation1();
    Computation2 comp2 = new Computation2();
    Verify.verify(comp1.product + comp2.sum == 0);

    // creation of child thread
    Thread t1 = new Thread(comp1);
    Thread t2 = new Thread(comp2);

    // moving child thread to runnable state
    t1.start();
    t2.start();

    try {
      CyclicBarrierExamples.newBarrier.await();
    } catch (InterruptedException | BrokenBarrierException e) {
      e.printStackTrace();
    }

    // barrier breaks as the number of thread waiting for the barrier
    // at this point = 3
    Verify.verify(comp1.product + comp2.sum == 36);
  }
}
