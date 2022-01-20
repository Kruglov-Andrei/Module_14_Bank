public class Bank {
    private int money = 10000;

    public int getMoney() {
        return money;
    }

    synchronized void takeMoney(int money) {
        this.money -= money;
    }

    synchronized void repayMoney(int money) {
        this.money += money;
    }

    class Client extends Thread {
        @Override
        public void run() {
            while (true) {
                takeMoney(1000);
                repayMoney(1000);
            }
        }
    }

    public Bank() {
        new Client().start();
        new Client().start();
        new Client().start();
    }


    public static void main(String[] args) throws InterruptedException{
        Bank bank = new Bank();
        while (true){
            System.out.println(bank.getMoney());
            Thread.sleep(1000);
        }
    }
}
