package bg.sofia.uni.fmi.mjt.trading.stock;

import java.time.LocalDateTime;

public class MicrosoftStockPurchase extends AbstractStockPurchase {

    public final static String TAG = "MSFT";

    public MicrosoftStockPurchase(int quantity, LocalDateTime purchaseTimestamp, double purchasePricePerUnit) {
        super(quantity, purchaseTimestamp, purchasePricePerUnit);
    }


    @Override
    public String getStockTicker() {
        return TAG;
    }

}
