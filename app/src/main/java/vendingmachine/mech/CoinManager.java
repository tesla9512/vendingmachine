package vendingmachine.mech;

import java.util.HashMap;

import vendingmachine.element.Coin;

public class CoinManager {
    private Coin[] coinUnit;
    private HashMap<String, Integer> coinQty;

    public CoinManager() {
        coinUnit = Coin.values();
        coinQty = new HashMap<>();

        for (Coin coin : coinUnit) {
            coinQty.put(coin.name(), 0);
        }
    }

    public void setCoin(int money) {
        // 모든 종류 동전 순회하면서 1개씩 증량 하는데
        // 이중 반복문은 쓰기 싫으니까 1회 순회만 작성하고 재귀로 돌린다
        if (money == 0)
            return;

        for (Coin coin : coinUnit) {
            // 증량 가능?
            if (money / coin.getAmount() > 0) {
                setCoinQty(coin.name(), getCoinQty(coin.name()) + 1);
                money -= coin.getAmount();
            }
        }
        setCoin(money);
    }

    public void printCoinQty() {
        for (Coin coin : coinUnit) {
            System.out.println(coin.getAmount() + "원 : " + getCoinQty(coin.name()) + "개");
        }
    }

    private void setCoinQty(String unit, int qty) {
        coinQty.put(unit, qty);
    }

    public int getCoinQty(String unit) {
        return coinQty.get(unit);
    }

    public void refund(int cash) {
        System.out.println("- 반환된 돈 -");

        for (Coin coin : coinUnit) {
            int returnQty = 0; // 실제로 반환될 동전 갯수 (해당 단위)
            int holdings = getCoinQty(coin.name()); // 자판기가 보유한 동전 갯수 (해당 단위)
            int needQty = cash / coin.getAmount(); // 해당 동전으로 지불해야할 최대 동전 갯수

            returnQty = Integer.min(needQty, holdings);

            if (returnQty == 0)
                continue;

            cash = cash - (returnQty * coin.getAmount());

            // 반환된 동전 갯수 출력
            System.out.println(coin.getAmount() + "원 동전 : " + returnQty + "개");

            if (cash == 0)
                break;
        }
    }
}
