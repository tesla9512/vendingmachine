package vendingmachine.mech;

import java.util.HashMap;

import vendingmachine.element.Goods;

public class GoodsManager {
    private HashMap<String, Goods> goodsSet;
    private int minPrice;

    public GoodsManager() {
        goodsSet = new HashMap<>();
        minPrice = Integer.MAX_VALUE;
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
            Goods product = new Goods(Integer.parseInt(unit[i + 1]), Integer.parseInt(unit[i + 2]));
            goodsSet.put(unit[i], product);

            minPrice = Integer.min(minPrice, Integer.parseInt(unit[i + 1]));
        }
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getPrice(String name) {
        return goodsSet.get(name).getPrice();
    }

    public void decreaseQuantity(String name) {
        goodsSet.get(name).decreaseQuantity();
    }

    public boolean isExistNameOfGoods(String name) {
        if (goodsSet.get(name) == null) {
            System.out.println("상품이 존재하지 않습니다. 다시 입력하세요.");
            return false;
        }

        return true;
    }

    public boolean isExistQtyOfGoods(String name) {
        if (goodsSet.get(name).getQuantity() == 0) {
            System.out.println("(" + name + ") 상품은 모두 판매되었습니다.");
            return false;
        }

        return true;
    }

    public boolean canBuyGoods(String name, int money) {
        if (money < goodsSet.get(name).getPrice()) {
            System.out.println("잔액이 부족합니다.");
            return false;
        }

        return true;
    }

}
