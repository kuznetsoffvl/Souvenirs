package logic;

public class Manufacturer extends Data implements Comparable {
    private String name;
    private Country country;

    public Manufacturer(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Manufacturer {" +
                " name = '" + name + '\'' +
                ", country = " + country +
                " } ";
    }

    @Override
    public int compareTo(Object o) {
        if (this.getClass() != o.getClass()) return getClass().getName().compareTo(o.getClass().getName());
        Manufacturer man = (Manufacturer) o;
        int v = name.compareTo(man.name);
        if (v != 0) return v;
        v = country.compareTo(man.country);
        return v;
    }


}
