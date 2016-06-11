package be.witspirit.testfactory.exampledomain;

import java.util.Objects;

/**
 * Strong representation of an Engine specification
 */
public class EngineSpec {
    public enum Fuel {
        DIESEL, PETROL
    }

    private Fuel fuel;
    private int displacement; // in CC

    public EngineSpec(Fuel fuel, int displacement) {
        this.fuel = fuel;
        this.displacement = displacement;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public int getDisplacement() {
        return displacement;
    }

    @Override
    public String toString() {
        return displacement+" CC "+fuel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngineSpec that = (EngineSpec) o;
        return displacement == that.displacement &&
                fuel == that.fuel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fuel, displacement);
    }
}
