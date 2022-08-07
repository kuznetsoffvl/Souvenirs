package logic;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Queries {

    /**Вывести информацию о сувенирах заданного производителя.*/
    public static List<Data> getSouvenirsOfManufacturer(List<Data> souvenirs, String manName) {

        List<Data> result = souvenirs.stream()
                .filter(x -> manName.equals(Queries.getManufacturerName(x)))
                .toList();

        return result;
    }

    /**Вывести информацию о сувенирах, произведенных в заданной стране.*/
    public static List<Data> getSouvenirsOfCountry(List<Data> manufacturers, List<Data> souvenirs, Country country) {

        List<String> filteredMansName = manufacturers.stream()
                .filter(x -> Queries.getCountry(x) == country)
                .map(Queries::getName)
                .toList();
        List<Data> result = souvenirs.stream()
                .filter(x -> filteredMansName
                        .contains(Queries.getManufacturerName(x)))
                .toList();

        return result;
    }

    /**Вывести информацию о производителях, чьи цены на сувениры меньше заданной.*/
    public static List<Data> getManufacturersWhosePricesForSouvenirsLessThanSpecified(List<Data> manufacturers, List<Data> souvenirs, int price) {

//        List<String> filteredMansName = souvenirs.stream()
//                .filter(x -> Queries.getPrice(x) < price)
//                .map(Queries::getManufacturerName)
//                .collect(Collectors.toList());
//
//        List<Data> result1 = manufacturers.stream()
//                .filter(x -> filteredMansName.contains(Queries.getName(x)))
//                .toList();

        List<Data> result = souvenirs.stream()
                .filter(s -> Queries.getPrice(s) < price)
                .map(Queries::getManufacturerName)
                .distinct()
                .map(s -> Queries.getByName(manufacturers, s))
                .toList();

        return result;
    }

    /**Вывести информацию по всем производителям и, для каждого производителя вывести
     информацию о всех сувенирах, которые он производит.*/
    public static Map<Data, List<Data>> getAllManufacturersAndTheirSouvenirs(List<Data> manufacturers, List<Data> souvenirs) {

        Map<Data, List<Data>> result = souvenirs.stream()
                .collect(Collectors.groupingBy(x -> Queries.getByName(manufacturers, Queries.getManufacturerName(x))));

        return result;
    }

    /**Вывести информацию о производителях заданного сувенира, произведенного в заданном году.*/
    public static List<Data> getManufacturersOfSouvenirProducedInGivenYear(List<Data> manufacturers, List<Data> souvenirs, int year, Data souvenir) {

        List<Data> result = souvenirs.stream()
                .filter(s -> s == souvenir)
                .filter(s -> Queries.getYearOfProduce(s) == year)
                .map(s -> Queries.getByName(manufacturers, Queries.getManufacturerName(s)))
                .toList();

        return result;
    }

    /**Для каждого года вывести список сувениров, произведенных в этом году.*/
    public static Map<Integer, List<Data>> getSouvenirsGroupedByYear(List<Data> souvenirs) {

        Map<Integer, List<Data>> result = souvenirs.stream()
                .sorted(Comparator.comparing(Queries::getReleaseDate))
                .collect(Collectors.groupingBy(Queries::getYearOfProduce));

        return result;
    }

    /**Удалить заданного производителя и его сувениры.*/
    public static Map<String, List<Data>> deleteSelectedManufacturerAndItsSouvenirs(
            List<Data> mans, List<Data> sous, Data manToDelete, String mansClassName, String sousClassName) {

        mans = mans.stream()
                .filter(m -> m != manToDelete)
                .toList();

        sous = sous.stream()
                .filter(s -> (!Queries.getName(manToDelete).equals(Queries.getManufacturerName(s))))
                .toList();

        Map result = new TreeMap();

        result.put(mansClassName, mans);
        result.put(sousClassName, sous);

        return result;
    }

    /** Далее идут служебные методы, вызываемые из данного класса и из других классов*/

    public static int getPrice(Data data) {
        return ((Souvenir) data).getPrice();
    }

    public static Country getCountry(Data data) {
        if (Manufacturer.class == data.getClass()) return ((Manufacturer) data).getCountry();
        return null;
    }

    public static String getName(Data data){
        if (Manufacturer.class == data.getClass()) return data.getName();
        if (Souvenir.class == data.getClass()) return data.getName();
        return "";
    }

    public static String getManufacturerName(Data data){
        if (Manufacturer.class == data.getClass()) return data.getName();
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
