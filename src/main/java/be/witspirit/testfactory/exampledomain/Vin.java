package be.witspirit.testfactory.exampledomain;

import java.util.Objects;

/**
 * Strong VIN representation
 */
public class Vin {
    private String vin;

    public Vin(String vin) {
        if (vin == null || vin.length() != 17) {
            throw new IllegalArgumentException("Invalid format for a VIN : "+vin);
        }
        this.vin = vin;
    }

    @Override
    public String toString() {
        return vin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vin vin1 = (Vin) o;
        return Objects.equals(vin, vin1.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin);
    }
}
