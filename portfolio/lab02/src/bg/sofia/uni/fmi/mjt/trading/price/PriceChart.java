package bg.sofia.uni.fmi.mjt.trading.price;

import static java.lang.Double.parseDouble;

public class PriceChart implements PriceChartAPI{

    public final static String TAG_MICROSOFT = "MSFT";
    public final static String TAG_GOOGLE = "GOOG";
    public final static String TAG_AMAZON = "AMZ";

    double microsoft,google,amazon;

    public PriceChart(double microsoftStockPrice, double googleStockPrice, double amazonStockPrice) {
        microsoft = microsoftStockPrice;
        google = googleStockPrice;
        amazon = amazonStockPrice;
    }

    @Override
    public double getCurrentPrice(String stockTicker) {
        if (stockTicker == null) return 0.0;

        double returnVal = switch (stockTicker) {
            case TAG_MICROSOFT -> microsoft;
            case TAG_GOOGLE -> google;
            case TAG_AMAZON -> amazon;
            default -> 0.0d;
        };
        return Math.round(returnVal*100) / 100.0;
    }

    @Override
    public boolean changeStockPrice(String stockTicker, int percentChange) {

        if(percentChange <= 0) return false;
        if(stockTicker == null) return false;

        switch (stockTicker) {
            case TAG_MICROSOFT:
                microsoft += microsoft*((double) percentChange / 100 );
                break;
            case TAG_GOOGLE:
                google += google*((double) percentChange / 100 );
                break;
            case TAG_AMAZON:
                amazon += amazon*((double) percentChange / 100 );
                break;
            default:
                return false;
        };

        return true;
        }
    }

