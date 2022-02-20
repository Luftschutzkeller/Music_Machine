public class BankAccount {
    private int balance = 100;

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        balance = balance - amount;
    }
}

class RyanAndMonicaJob implements Runnable {

    private final BankAccount account = new BankAccount();

    public static void main(String[] args) {
        RyanAndMonicaJob theJob = new RyanAndMonicaJob();
        Thread one = new Thread(theJob);
        Thread two = new Thread(theJob);
        one.setName("Ryan");
        two.setName("Monica");
        one.start();
        two.start();
    }

    public void run() {
        for (int x = 0; x < 10; x++) {
            makeWithdrawal();
            if (account.getBalance() < 0) {
                System.out.println("Превышение лимита");
            }
        }
    }

    private synchronized void makeWithdrawal() {
        if (account.getBalance() >= 10) {
            System.out.println(Thread.currentThread().getName() + "собирается снять деньги");
            try {
                System.out.println(Thread.currentThread().getName() + "идёт подремать");
                Thread.sleep(500);
            } catch (InterruptedException ex) { ex.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "просыпается");
            account.withdraw(10);
            System.out.println(Thread.currentThread().getName() + "заканчивает транзакцию");
        } else {
            System.out.println("Извините, для клиента" + Thread.currentThread().getName() + "недостаточно денег");
        }
    }
}