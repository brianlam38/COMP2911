import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
   This program runs two threads in parallel.
*/
public class ThreadTester
{
   public static void main(String[] args)
   {
	  final int GREETING_COUNT = 100;
	  
	  final Lock lock = new ReentrantLock(); //Create the lock for the BoundedQueue
	  final Condition full = lock.newCondition(); //Used for producer waiting when queue is full
	  final Condition empty = lock.newCondition(); //Used for Consumer waiting when queue is empty
	  
      BoundedQueue<String> queue = new BoundedQueue<String>(10);
      queue.setDebug(true);
      
      Runnable run1 = new Producer("Hello, World!", queue, lock, full, empty, GREETING_COUNT);
      
      Runnable run2 = new Producer("Goodbye, World!", queue, lock, full, empty, GREETING_COUNT);
      
      Runnable run3 = new Consumer(queue, 2 * GREETING_COUNT, lock, full, empty);
      
      Thread thread1 = new Thread(run1);
      Thread thread2 = new Thread(run2);
      Thread thread3 = new Thread(run3);

      thread1.start();
      thread2.start();
      thread3.start();
   }
}

