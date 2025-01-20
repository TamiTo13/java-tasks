package bg.sofia.uni.fmi.mjt.simcity.plot;

import bg.sofia.uni.fmi.mjt.simcity.exception.BuildableAlreadyExistsException;
import bg.sofia.uni.fmi.mjt.simcity.exception.BuildableNotFoundException;
import bg.sofia.uni.fmi.mjt.simcity.exception.InsufficientPlotAreaException;
import bg.sofia.uni.fmi.mjt.simcity.property.buildable.Buildable;

import java.util.HashMap;
import java.util.Map;

public class Plot<E extends Buildable> implements PlotAPI<E> {

    HashMap<String, E> plot;
    private int capacity;
    private int current;

    public Plot(int buildableArea) {
        plot = new HashMap<>();
        capacity = buildableArea;
        current = 0;
    }

    @Override
    public void construct(String address, E buildable) {
        if (address == null || buildable == null || address.isBlank()) {
            throw new IllegalArgumentException();
        }

        if (plot.containsKey(address)) {
            throw new BuildableAlreadyExistsException();
        }

        if (buildable.getArea() + current > capacity) {
            throw new InsufficientPlotAreaException();
        }

        current += buildable.getArea();
        plot.put(address, buildable);
    }

    @Override
    public void constructAll(Map<String, E> buildables) {
        if (buildables == null || buildables.isEmpty()) {
            throw new IllegalArgumentException();
        }

        for (String address1: plot.keySet()) {
            for (String address2: buildables.keySet()) {
                if (address1.equals(address2)) {
                    throw new BuildableAlreadyExistsException();
                }
            }
        }

        int check = 0;
        for (String key: buildables.keySet()) {
            check += buildables.get(key).getArea();
        }
        if (current + check > capacity) {
            throw new InsufficientPlotAreaException();
        }

        current += check;
        for (String key: buildables.keySet()) {
            plot.put(key, buildables.get(key));
        }
    }

    @Override
    public void demolish(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException();
        }
        if (!plot.containsKey(address)) {
            throw new BuildableNotFoundException();
        }

        current -= plot.get(address).getArea();
        plot.remove(address);
    }

    @Override
    public void demolishAll() {
        current = 0;
        plot.clear();
    }

    @Override
    public Map<String, E> getAllBuildables() {
        if (plot.isEmpty()) {
            return new HashMap<>();
        }

        return Map.copyOf(plot);
    }

    @Override
    public int getRemainingBuildableArea() {
        return capacity - current;
    }
}
