import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
   An action that repeatedly removes a greeting from a queue.
*/
public class Consumer implements Runnable
{
	   private BoundedQueue<String> queue;
	   private int greetingCount;

	   private Lock lock;
	   private Condition full;
	   private Condition empty;
	   private static final int DELAY = 10;
   /**
      Constructs the consumer object.
      @param aQueue the queue from which to retrieve greetings
      @param count the number of greetings to consume
   */
   public Consumer(BoundedQueue<String> aQueue, int count, Lock lock, Condition full, Condition empty)
   {
      queue = aQueue;
      greetingCount = count;
      this.lock = lock;
      this.full = full;
      this.empty = empty;
   }

   public void run(){
	   
      try{
         
    	 int i = 0;
         while (i < greetingCount){
        	 
        	 if (lock.tryLock()){
        		
        		 while(queue.isEmpty()){ //If it's empty we need to sleep
        			 empty.await(); //release the lock and wait until it is returned to us
        		 }
        		 
        		 String greeting = queue.remove(); //Consume (om nom nom)
        		 System.out.println(greeting);
        		 
        		 if (!queue.isFull()){ //If the queue isn't full, wake up the producers
        			 full.signal();
        		 }
        		 
        		 lock.unlock(); //Release the lock
        		 i++;
        	 } else {
        		 Thread.sleep((int) (Math.random() * DELAY)); 
        	 }
         }
      }
      catch (InterruptedException exception){}
   }


}
