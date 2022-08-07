package logic;

public class Manufacturer implements Data{
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
}
