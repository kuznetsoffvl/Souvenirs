package logic;

import java.time.LocalDate;
import java.util.List;

public class Souvenir implements Data {
    private String name;
    private String manufacturerName;
    private LocalDate releaseDate;
    private int price;
    //private int index;

    public Souvenir(String name, String manufacturerName, LocalDate releaseDate, int price) {
        this.name = name;
        this.manufacturerName = manufacturerName;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Souvenir{" +
                "name='" + name + '\'' +
                ", manufacturerName='" + manufacturerName + '\'' +
                ", releaseDate=" + releaseDate +
                ", price=" + price +
                '}';
    }
}
