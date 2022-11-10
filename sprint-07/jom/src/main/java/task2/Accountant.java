package task2;

//You have class from task #1 ParallelCalculator
class ParallelCalculator implements Runnable {

    @Override
    public void run() {

    }
}

class Accountant{
    public static int result;

    @Override
    public void run(int x, int y) {
       result = x + y;
    }
    public static int sum(int x, int y) {
        this.run();
    }
}
