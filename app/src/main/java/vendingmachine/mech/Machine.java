package vendingmachine.mech;

public class Machine {
    private CoinManager coinManager;
    private GoodsManager goodsManager;
    private int cash;

    public Machine() {
        coinManager = new CoinManager();
        goodsManager = new GoodsManager();
        cash = 0;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int money) {
        cash = money;
    }

    public void setCoin(int money) {
        coinManager.setCoin(money);
    }

    public void printCoinQty() {
        coinManager.printCoinQty();
    }

    public void refund() {
        coinManager.refund(cash);
    }

    public void setGoods(String info) {
        goodsManager.setGoods(info);
    }

    public void order(String name) {
        // 재고 없음 / 상품 없음 / 잔액 부족
        if (goodsManager.isExistQtyOfGoods(name) == false
                || goodsManager.isExistNameOfGoods(name) == false
                || goodsManager.canBuyGoods(name, cash) == false) {
            return;
        }

        cash -= goodsManager.getPrice(name);
        goodsManager.decreaseQuantity(name);

        System.out.println(name + " 구입 완료.");
    }
}
