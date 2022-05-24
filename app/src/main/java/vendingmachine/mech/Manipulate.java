package vendingmachine.mech;

import java.util.Scanner;

public class Manipulate {

    public void start() {
        Scanner sc = new Scanner(System.in);
        Machine machine = new Machine();

        System.out.print("자판기의 보유 금액을 설정 : ");
        machine.setCoin(Integer.parseInt(sc.nextLine()));

        System.out.println("자판기가 보유한 동전");
        machine.printCoinQty();

        System.out.print("자판기가 판매할 상품을 설정 : ");
        machine.setGoods(sc.nextLine());

        System.out.print("투입할 금액을 설정 : ");
        machine.setCash(Integer.parseInt(sc.nextLine()));

        while (machine.getCash() > 0) {
            System.out.println("잔액 : " + machine.getCash());
            System.out.print("구매할 상품을 입력 : ");

            String name = sc.nextLine();

            if (name.equals("반환")) {
                machine.refund();
                break;
            }

            machine.order(name);
        }

        sc.close();
    }

}
