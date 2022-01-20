public class Bank_Static {
        private static int money = 10000;

        static int getMoney() {
            return money;
        }

        static synchronized void takeMoney(int money) {
            Bank_Static.money -= money;
        }

        static synchronized void repayMoney(int money) {
            Bank_Static.money += money;
        }

        static class Client extends Thread {
            @Override
            public void run() {
                while (true) {
                    takeMoney(1000);
                    repayMoney(1000);
                }
            }
        }

        public static void main(String[] args) throws InterruptedException{
            new Client().start();
            new Client().start();
            new Client().start();
            while (true){
                System.out.println(Bank_Static.money);
                Thread.sleep(1000);
            }
        }
    }
