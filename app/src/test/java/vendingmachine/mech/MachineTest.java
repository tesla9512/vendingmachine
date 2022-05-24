package vendingmachine.mech;

// import static org.hamcrest.Matchers.is;

// import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

public class MachineTest {

    Machine machine = new Machine();

    @Test
    void coinTest() {
        machine.setCoin(10000);
        machine.printCoinQty();
    }

    @Test
    void refundTest() {
        machine.setCoin(4990);
        machine.setCash(870);
        machine.refund();
    }

    // @Test
    // void productTest() {
    // machine.setGoods("[삼다수,800,1]");

    // MatcherAssert.assertThat(machine.isExistNameOfGoods("삼다수"), is(true));
    // MatcherAssert.assertThat(machine.isExistNameOtGoods("콜라"), is(false));
    // }

    @Test
    void orderTest() {
        machine.setCoin(9990);
        machine.setCash(1000);
        machine.setGoods("[삼다수,800,1]");
        machine.order("삼다수");
        System.out.println("잔액 : " + machine.getCash() + "원");
        machine.order("삼다수");
        machine.refund();
    }
}
