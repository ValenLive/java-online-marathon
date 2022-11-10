# JOM.  Multithreading
## Task 1

Create **ParallelCalculator** class that will be able to execute an operation in **parallel thread**.
Use the implementation of **Runnable** interface for this.

Constructor of **ParallelCalculator** should take **3 parameters**:

1. The **BinaryOperator<Integer>** to define an operation that will be executed,
2. The **operand1** of type int and **operand2** of type **int**.

The **ParallelCalculator** class should have **_not_**  **private result** field of type **int** where the result of the operation will be written when it's executed.

## Task 2

Suppose, you have class from task #1 **ParallelCalculator** that is able to execute an operation in separate thread. 
It uses an implementation of **Runnable** interface for this.

You need to create **Accountant** class with a **static sum(...)** method that takes two parameters of type int and returns their **_sum_**. 
Use **ParallelCalculator** for this. The **sum(...)** method doesn't **throw** any checked exceptions.

The **_sum_** must be evaluated in a **_separate thread_**  (please, don't call **run()** method of **ParallelCalculator**. Use **start()** method on object of type **Thread**).

Using **Thread.sleep()** method is unwelcomed in this task.

## Task 3
Suppose, we have the next class:

    class threadExample{
        public static void threadRun() { 
            Interactor interactor = new Interactor();     
            Thread t1 = new Thread(()-> { 
                try{ 
                    interactor.serve(x -> -x+4, 11); 
                } 
                catch(InterruptedException e){ 
                    e.printStackTrace(); 
                }
            });   
        
            Thread t2 = new Thread(() -> { 
                try{ 
                    interactor.consume((a, b) -> a + 2*b, 20); 
                } 
                catch(InterruptedException e){ 
                    e.printStackTrace(); 
                }
            });   
        
                try{
                t2.start();            
                t1.start();  
                t2.join();             
                t1.join();             
                }
                catch(InterruptedException e){ 
                    e.printStackTrace(); 
            } 
        }
    }

You need to implement the methods of the **Interactor** class so that output will look like this:
**_Serving thread running<br/>
Serving thread initializes the key<br/>
key = -7<br/>
Consuming thread received the key. key = -7<br/>
Consuming thread changed the key. key = 33<br/>
Serving thread resumed_**

The **serve(...)** method should initialize the **x** field with applied its first parameter to the second one and **print the messages** only about its own actions.

The **сounsume(...)** method should wait until serve initializes **x** field and then change **x** by assigning it the result of applying the method's first parameter to the second and the third ones. 
This method also **prints the messages** only about its own actions.<br/>
Assume that the **consume(...)** method should be able to execute without the **serve(...)** method **after waiting for 3 seconds**.<br/>
Use **synchronized** blocks (or methods), **wait()** and **notify()** methods for the implementation.

## Task 4
Suppose, we have the next class:

    class MyThreads {
    public final static Object den = new Object();
    public final static Object ada = new Object();

        public static int n;
        public static int m;

         public static Thread t1 = new Thread() {
         public void run() {
            synchronized (den) {
                for(int i = 0; i < 5; i++, n++)
                    System.out.println("Thread1 n = " + n);
                Thread.yield();
                synchronized (ada) {
                    for(int i = 0; i < 5; i++, m++)
                        System.out.println("Thread1 m = " + m);
                    System.out.println( "Thread1 success!");
                }  
            }  
        }  
    };

    public static Thread t2 = new Thread() {
        public void run() {
            synchronized (ada) {
                for(int i = 0; i < 5; i++, m++)
                    System.out.println("Thread2 m = " + m);
                Thread.yield();
                synchronized (den) {
                    for(int i = 0; i < 5; i++, n++)
                        System.out.println("Thread2 n = " + n);
                    System.out.println("Thread2 success!");
                    }
                 }
             }
        };   
    }

Fix the problem with the preloaded implementation of the class **MyThread**.

Test starts both threads with the code:

        MyThreads.t1.start();
        MyThreads.t2.start();

The goal of each thread is to update **m** and **n** fields and not switch between threads **while loop is executed**.

You need to get an output like this:<br/>

Thread1 n = 0<br/>
Thread1 n = 1<br/>
Thread1 n = 2<br/>
Thread1 n = 3<br/>
Thread1 n = 4<br/>
Thread2 m = 0<br/>
Thread2 m = 1<br/>
Thread2 m = 2<br/>
Thread2 m = 3<br/>
Thread2 m = 4<br/>
Thread2 n = 5<br/>
Thread2 n = 6<br/>
Thread2 n = 7<br/>
Thread2 n = 8<br/>
Thread2 n = 9<br/>
Thread2 success!<br/>
Thread1 m = 5<br/>
Thread1 m = 6<br/>
Thread1 m = 7<br/>
Thread1 m = 8<br/>
Thread1 m = 9<br/>
Thread1 success!<br/>
Please, don't change actions that change variables or print output within **run()** methods. Use only **thread synchronization assets**.