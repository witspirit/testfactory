package be.witspirit.testfactory.exampledomain;

import java.util.ArrayList;
import java.util.List;

/**
 * A Car model to see whether it becomes easy to create the test boilerplate
 */
public class Car {
    private Vin vin;
    private EngineSpec engine;
    private List<String> serviceComments = new ArrayList<>();

    public Vin getVin() {
        return vin;
    }

    public Car setVin(Vin vin) {
        this.vin = vin;
        return this;
    }

    public EngineSpec getEngine() {
        return engine;
    }

    public Car setEngine(EngineSpec engine) {
        this.engine = engine;
        return this;
    }

    public List<String> getServiceComments() {
        return serviceComments;
    }

    public Car addServiceComment(String comment) {
        serviceComments.add(comment);
        return this;
    }
}
