package model;

/**
 * Created by L.H.S on 2017/6/19.
 */
public class CountryMap {

    private String name;
    private double value;

    public CountryMap(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
