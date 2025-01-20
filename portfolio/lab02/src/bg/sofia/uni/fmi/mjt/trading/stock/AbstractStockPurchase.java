package bg.sofia.uni.fmi.mjt.trading.stock;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

public  abstract class  AbstractStockPurchase implements  StockPurchase{

    protected int quantity;
    protected LocalDateTime purchaseTimestamp;
    protected double purchasePricePerUnit;


    protected AbstractStockPurchase(int quantity, LocalDateTime purchaseTimestamp, double purchasePricePerUnit){
        this.quantity = quantity;
        this.purchaseTimestamp = purchaseTimestamp;
        this.purchasePricePerUnit = purchasePricePerUnit;
    };

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public LocalDateTime getPurchaseTimestamp() {
        return purchaseTimestamp;
    }

    @Override
    public double getPurchasePricePerUnit() {
        return purchasePricePerUnit;
    }

    @Override
    public double getTotalPurchasePrice() {
       return Math.round(((double)quantity * purchasePricePerUnit) * 100 ) / 100.0;

    }

    @Override
    public abstract String getStockTicker();
}
