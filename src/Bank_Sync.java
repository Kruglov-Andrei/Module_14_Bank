public class Bank_Sync {
    private int money = 10000;
    // переменная, по которой и будем синхронизироваться
    private Object lock = new Object();
    int getMoney(){
        return money;
    }
    void takeMoney(int money){
        synchronized (lock){
            this.money -= money;
        }
    }
    void repayMoney(int money){
        synchronized (lock){
            this.money += money;
        }
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
    public Bank_Sync(){
        new Client().start();
        new Client().start();
        new Client().start();
    }

    public static void main(String[] args) throws InterruptedException {
        Bank_Sync bank = new Bank_Sync();
        while(true){
            System.out.println(bank.money);
            Thread.sleep(1000);
        }
    }
}
