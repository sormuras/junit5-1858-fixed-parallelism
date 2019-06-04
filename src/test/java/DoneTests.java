import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

@Execution(ExecutionMode.CONCURRENT)
class DoneTests {
  @Test
  @Tag("main")
  void done1() {
    work(1);
  }

  @Test
  @Tag("main")
  void done2() {
    work(2);
  }

  @Test
  @Tag("main")
  void done3() {
    work(3);
  }

  @Test
  @Tag("main")
  void done4() {
    work(4);
  }

  @Test
  @Tag("main")
  void done5() {
    work(5);
  }

  private void work(int id)  {
    System.out.println("BEGIN " + id);
    System.out.println(Thread.currentThread().getName());
    ForkJoinPool customThreadPool = new ForkJoinPool(4);
    RecursiveAction a = new RecursiveAction() {
      @Override
      protected void compute() {
        while (true) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println(Thread.currentThread().getName());
        }
      }
    };
    customThreadPool.invoke(a);

//    try {
//      Thread.sleep(Long.MAX_VALUE);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }

    System.out.println("DONE " + id);
  }

}
