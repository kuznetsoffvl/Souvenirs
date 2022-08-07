package logic;

import java.time.LocalDate;
import java.util.List;

public class Queries {

//    public static int getPrice(Data data) {
//        if (Manufacturer.class == data.getClass()) return ((Souvenir) data).getPrice();
//        return -1;
//    }

    public static int getPrice(Data data) {
        return ((Souvenir) data).getPrice();
    }

    public static Country getCountry(Data data) {
        if (Manufacturer.class == data.getClass()) return ((Manufacturer) data).getCountry();
        return null;
    }

    public static String getName(Data data){
        if (Manufacturer.class == data.getClass()) return ((Manufacturer) data).getName();
        if (Souvenir.class == data.getClass()) return ((Souvenir) data).getName();
        return "";
    }

    public static String getManufacturerName(Data data){
        if (Manufacturer.class == data.getClass()) return ((Manufacturer) data).getName();
        if (Souvenir.class == data.getClass()) return ((Souvenir) data).getManufacturerName();
        return null;
    }

    public static Data getByName(List<Data> list, String name){
        return list.stream().filter(x->name.equals(Queries.getName(x))).toList().get(0);
    }

    public static int getYearOfProduce(Data data){
        return ((Souvenir) data).getReleaseDate().getYear();
    }

    public static LocalDate getReleaseDate(Data data){
        return ((Souvenir) data).getReleaseDate();
    }

}
