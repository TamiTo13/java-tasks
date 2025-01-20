package bg.sofia.uni.fmi.mjt.trading;

import bg.sofia.uni.fmi.mjt.trading.price.PriceChart;
import bg.sofia.uni.fmi.mjt.trading.price.PriceChartAPI;
import bg.sofia.uni.fmi.mjt.trading.stock.*;

import java.time.LocalDateTime;

public class Portfolio implements PortfolioAPI{

    private  String owner;
    private PriceChartAPI priceChart;
    private Double budget;
    public int maxSize;
    public StockPurchase[] arr;
    private int current = 0;

    public Portfolio(String owner, PriceChartAPI priceChart, double budget, int maxSize){
        this.owner= owner;
        this.priceChart = priceChart;
        this.budget = budget;
        this.maxSize = maxSize;
        arr = new StockPurchase[maxSize];
    }

    public Portfolio(String owner, PriceChartAPI priceChart, StockPurchase[] stockPurchases, double budget, int maxSize) {
                this.owner = owner;
                this.priceChart=priceChart;
                this.budget=budget;
                this.maxSize = maxSize;
                arr = new StockPurchase[maxSize];
                for(StockPurchase iter: stockPurchases) {
                    arr[current++] = iter;
                }
    }

    @Override
    public StockPurchase buyStock(String stockTicker, int quantity) {
        if(stockTicker == null || (!stockTicker.equals(PriceChart.TAG_AMAZON)
            && !stockTicker.equals(PriceChart.TAG_GOOGLE) && !stockTicker.equals(PriceChart.TAG_MICROSOFT))) {
                return null;
        }
        if(quantity <= 0) return null;
        if(current == maxSize) return null;

        double amount = priceChart.getCurrentPrice(stockTicker) * (double) quantity;
        if(budget < amount ) return null;

        budget -= amount;
        addToArr(stockTicker, quantity, LocalDateTime.now(), priceChart.getCurrentPrice(stockTicker));
        priceChart.changeStockPrice(stockTicker, quantity*5);
        return arr[current-1];

    }

    private void addToArr(String stockTicker, int quantity,
                                         LocalDateTime time, double pricePerUnit) {
        arr[current++]= switch (stockTicker) {
            case AmazonStockPurchase.TAG ->
                    new AmazonStockPurchase(quantity, LocalDateTime.now(),pricePerUnit);
            case GoogleStockPurchase.TAG ->
                    new GoogleStockPurchase(quantity, LocalDateTime.now(),pricePerUnit);
            case MicrosoftStockPurchase.TAG ->
                    new MicrosoftStockPurchase(quantity, LocalDateTime.now(),pricePerUnit);
            default -> null;
        };
    }

    @Override
    public StockPurchase[] getAllPurchases() {
        return arr;
    }

    @Override
    public StockPurchase[] getAllPurchases(LocalDateTime startTimestamp, LocalDateTime endTimestamp) {
        StockPurchase[] returnArr;
        int i = 0;

        for(int j = 0; j<current; j++) {
            if((arr[j].getPurchaseTimestamp().isAfter(startTimestamp) &&
                    arr[j].getPurchaseTimestamp().isBefore(endTimestamp) )||
                    arr[j].getPurchaseTimestamp().isEqual(startTimestamp) ||
                    arr[j].getPurchaseTimestamp().isEqual(endTimestamp)) {
                i++;
            }
        }

        returnArr = new StockPurchase[i];
        i = 0;
        for(int j = 0; j<current; j++) {
            if((arr[j].getPurchaseTimestamp().isAfter(startTimestamp) &&
                    arr[j].getPurchaseTimestamp().isBefore(endTimestamp) )||
                    arr[j].getPurchaseTimestamp().isEqual(startTimestamp) ||
                    arr[j].getPurchaseTimestamp().isEqual(endTimestamp)) {
                    returnArr[i++] = arr[j];
            }
        }
        return returnArr;
    }

    @Override
    public double getNetWorth() {
        if(arr == null) return 0.0;


        double returnVal = 0.0;
        for(int i = 0 ; i<current; i++) {
            returnVal += arr[i].getQuantity()*priceChart.getCurrentPrice(arr[i].getStockTicker());
        }
        return Math.round(returnVal * 100 ) / 100.0;
    }

    @Override
    public double getRemainingBudget() {
        return Math.round(budget * 100 ) / 100.0;
    }

    @Override
    public String getOwner() {
        return owner;
    }
}
