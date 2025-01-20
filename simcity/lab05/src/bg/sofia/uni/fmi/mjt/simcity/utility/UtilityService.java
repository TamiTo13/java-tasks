package bg.sofia.uni.fmi.mjt.simcity.utility;

import bg.sofia.uni.fmi.mjt.simcity.property.billable.Billable;

import java.util.HashMap;
import java.util.Map;

public class UtilityService implements UtilityServiceAPI {

    Map<UtilityType, Double> taxRates;
    static final double ROUND = 100.0;

    public UtilityService(Map<UtilityType, Double> taxRates) {
        this.taxRates = new HashMap<>();
        for (UtilityType key: taxRates.keySet()) {
            this.taxRates.putIfAbsent(key, taxRates.get(key));
        }
    }

    @Override
    public <T extends Billable> double getUtilityCosts(UtilityType utilityType, T billable) {

        if (utilityType == null || billable == null) {
            throw new IllegalArgumentException();
        }

        double amount;
        amount = switch (utilityType) {
            case WATER -> billable.getWaterConsumption();
            case ELECTRICITY -> billable.getElectricityConsumption();
            case NATURAL_GAS -> billable.getNaturalGasConsumption();
        };

        return taxRates.get(utilityType) * amount;
    }

    @Override
    public <T extends Billable> double getTotalUtilityCosts(T billable) {
        if (billable == null) {
            throw new IllegalArgumentException();
        }

        double ret = 0.0;
        ret += billable.getNaturalGasConsumption() * taxRates.get(UtilityType.NATURAL_GAS);
        ret += billable.getElectricityConsumption() * taxRates.get(UtilityType.ELECTRICITY);
        ret += billable.getWaterConsumption() * taxRates.get(UtilityType.WATER);

        return ret;
    }

    @Override
    public <T extends Billable> Map<UtilityType, Double> computeCostsDifference(T firstBillable, T secondBillable) {
        if ( firstBillable == null || secondBillable == null) {
            throw new IllegalArgumentException();
        }

        final HashMap<UtilityType, Double> ret = new HashMap<>();
        double buffer;

        buffer = Math.abs(firstBillable.getElectricityConsumption() - secondBillable.getElectricityConsumption());
        buffer *= taxRates.get(UtilityType.ELECTRICITY);
        ret.put(UtilityType.ELECTRICITY, buffer);

        buffer = Math.abs(firstBillable.getNaturalGasConsumption() - secondBillable.getNaturalGasConsumption());
        buffer *= taxRates.get(UtilityType.NATURAL_GAS);
        ret.put(UtilityType.NATURAL_GAS, buffer);

        buffer = Math.abs(firstBillable.getWaterConsumption() - secondBillable.getWaterConsumption());
        buffer *= taxRates.get(UtilityType.WATER);
        ret.put(UtilityType.WATER, buffer);

        return Map.copyOf(ret);
    }
}
