package vendingmachine.mech;

import java.util.HashMap;

import vendingmachine.Coin;

public class Machine {
    private Coin[] coinUnit;
    private HashMap<String, Integer> coinQty;
    private HashMap<String, Product> goods;
    private int cash;
    private int minPrice;

    public Machine() {
        coinUnit = Coin.values();
        coinQty = new HashMap<>();
        goods = new HashMap<>();
        cash = 0;
        minPrice = Integer.MAX_VALUE;
    }

    public void setCoin(int money) {
        // 우선은 내림차순으로 분배
        for (Coin coin : coinUnit) {
            setCoinQty(coin.name(), money / coin.getAmount());
            money = money % coin.getAmount();
        }

        int qty500w = getCoinQty("COIN_500");
        int qty100w = getCoinQty("COIN_100");

        // 500원이 100원보다 2배 이상 많을 경우 수량 조절
        if (qty500w >= qty100w * 2) {
            // 500 : 100 = 7 : 3 비율로 하면 금액이 높아질 수록 어느정도 갯수의 균형이 맞춰진다

            int amount = 500 * qty500w; // 500원 동전의 총금액 산정
            int balancedQty = (int) (qty500w * 0.7); // 위의 비율로 조정된 500원 동전 갯수

            amount = amount - (500 * balancedQty); // 100원 동전을 증가할 금액 계산

            setCoinQty("COIN_500", balancedQty);
            setCoinQty("COIN_100", qty100w + amount / 100);
        }
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

    public void setGoods(String info) {
        // 입력 예시 : [콜라,1500,20];[사이다,1000,10]
        /**
         * 정규식으로 특수문자를 제거
         * ;는 입력자 편의로 구분한 것으로 보고 이것도 제거
         * a b c a b c 순서대로 각각의 정보를 알맞은 곳에 넣자
         */

        info = info.replaceAll("\\[|\\]", "");
        String[] unit = info.split(",|;");

        for (int i = 0; i < unit.length; i += 3) {
            Product product = new Product(Integer.parseInt(unit[i + 1]), Integer.parseInt(unit[i + 2]));
            goods.put(unit[i], product);

            minPrice = Integer.min(minPrice, Integer.parseInt(unit[i + 1]));
        }
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int money) {
        cash = money;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public boolean isExistGoods(String name) {
        if (goods.get(name) == null)
            return false;

        return true;
    }

    public void order(String name) {
        if (goods.get(name).getQuantity() == 0) {
            // 재고 없음 알림
            System.out.println("(" + name + ") 상품은 모두 판매되었습니다.");
            return;
        }

        if (goods.get(name).getPrice() > cash) {
            // 금액 부족 알림
            System.out.println("잔액이 부족합니다.");
            return;
        }

        cash -= goods.get(name).getPrice();
        goods.get(name).decreaseQuantity();

        System.out.println(name + " 구입 완료.");
    }

    public void refund() {
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
