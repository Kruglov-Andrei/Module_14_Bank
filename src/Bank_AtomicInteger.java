import java.util.concurrent.atomic.AtomicInteger;

public class Bank_AtomicInteger {
    private AtomicInteger money = new AtomicInteger();
    int getMoney(){
        return money.get();
    }
    void takeMoney(int money){// безопасно уменьшаем
        this.money.addAndGet(-money);
    }
    void repayMoney(int money){//безопасно увеличиваем
        this.money.addAndGet(money);
    }
    class Client extends Thread{
        @Override
        public void run(){
            while (true){ // выдаем кредит, только если есть свободные средства
                if (getMoney() >= 1000){
                    takeMoney(1000);
                    repayMoney(1000);
                }
            }
        }
    }

    public Bank_AtomicInteger() {//устанавливаем начальное значение
        money.set(10000);
        for (int i = 0; i < 5; i++){
            new Client().start();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Bank_AtomicInteger bank = new Bank_AtomicInteger();
        while (true){
            System.out.println(bank.getMoney());
            Thread.sleep(1000);
        }
    }
}
