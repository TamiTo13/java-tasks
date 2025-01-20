import bg.sofia.uni.fmi.mjt.trading.Portfolio;
import bg.sofia.uni.fmi.mjt.trading.price.PriceChart;
import bg.sofia.uni.fmi.mjt.trading.stock.StockPurchase;

public class Main {
    public static void main(String[] args) {
        Portfolio a =
                new Portfolio
                        ("asda",
                                new PriceChart(5.5, 4.5,3.5),
                                new StockPurchase[0],
                                20,
                                10);
                    System.out.println(a.getRemainingBudget());
        System.out.println(a.buyStock("GOOG", 2));
        System.out.println(a.getRemainingBudget());
    }
}
