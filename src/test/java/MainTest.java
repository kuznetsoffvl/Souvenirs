import logic.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MainTest {

    List<Data> manufacturers;
    List<Data> souvenirs;

    @BeforeEach
    void setUp() {

        // Переинициализируем при необходимости в файлы, потом будем читать из файлов
        // Если нужно переинициализировать файлы, то раскоментируем код ниже

//        List<Data> mans = Main.fillManufacturers();
//        Files.writeDataToFile(mans, Manufacturer.class);//
//        List<Data> sous = Main.fillSouvenirs(mans);
//        Files.writeDataToFile(sous, Souvenir.class);

        manufacturers = Files.readDataFromFile(Manufacturer.class);
        souvenirs = Files.readDataFromFile(Souvenir.class);
    }

    @AfterEach
    void tearDown() {
    }

    /**просматривать всех производителей*/
    @Test
    void testPrintManufacturers() {
        //manufacturers.stream().forEach(System.out::println);
        DataList mans = new DataList(manufacturers);
        System.out.println(mans);
    }

    /**просматривать все сувениры*/
    @Test
    void testPrintSouvenirs() {
        //souvenirs.stream().forEach(System.out::println);
        DataList sous = new DataList(souvenirs);
        System.out.println(sous);
    }

    /**Вывести информацию о сувенирах заданного производителя.*/
    @Test
    void testGetSouvenirsOfManufacturer() {

        String manName = "Big Fun";
        List<Data> result = Queries.getSouvenirsOfManufacturer(souvenirs, manName);

        System.out.println(new DataList(result));

        assertEquals(
            Arrays.asList(souvenirs.get(0), souvenirs.get(6), souvenirs.get(12)),
                result);
    }

    /**Вывести информацию о сувенирах, произведенных в заданной стране.*/
    @Test
    void testGetSouvenirsOfCountry() {

        Country country = Country.UKRAINE;
        List<Data> result = Queries.getSouvenirsOfCountry(manufacturers, souvenirs, country);

        System.out.println(new DataList(result));

        assertEquals(
                Arrays.asList(souvenirs.get(4), souvenirs.get(10)),
                result);
    }

    /**Вывести информацию о производителях, чьи цены на сувениры меньше заданной.*/
    @Test
    void testGetManufacturersWhosePricesForSouvenirsLessThanSpecified() {

        int price = 3;
        List<Data> result = Queries.getManufacturersWhosePricesForSouvenirsLessThanSpecified(manufacturers, souvenirs, price);

        System.out.println(new DataList(result));
//      System.out.println(new DataList(manufacturers));
//      System.out.println(new DataList(souvenirs));

        assertEquals(
                Arrays.asList(manufacturers.get(0), manufacturers.get(1), manufacturers.get(3)),
                result);

    }
    
    /**Вывести информацию по всем производителям и, для каждого производителя вывести
     информацию о всех сувенирах, которые он производит.*/
    @Test
    void testGetAllManufacturersAndTheirSouvenirs() {

        Map<Data, List<Data>> result = Queries.getAllManufacturersAndTheirSouvenirs(manufacturers, souvenirs);

        // Цикл использую для вывода, чтобы добавить символы табуляции и перевода строк
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Data, List<Data>> entrySet : result.entrySet()) {
            stringBuilder.append(entrySet.getKey()).append("\n");

            List<Data> sous = entrySet.getValue();
            for (Data s : sous) {
                stringBuilder.append("   ").append(s).append("\n");
            }
        }

        System.out.println(stringBuilder);
    }

    /**Вывести информацию о производителях заданного сувенира, произведенного в заданном году.*/
    @Test
    void testGetManufacturersOfSouvenirProducedInGivenYear() {

        int year = 2020;
        Data souvenir = souvenirs.get(13);

        List<Data> result = Queries.getManufacturersOfSouvenirProducedInGivenYear(manufacturers, souvenirs, year, souvenir);

        System.out.println(new DataList(result));

        assertEquals(Arrays.asList(manufacturers.get(1)), result);
    }

    /**Для каждого года вывести список сувениров, произведенных в этом году.*/
    @Test
    void testGetSouvenirsGroupedByYear() {

        Map<Integer, List<Data>> result = Queries.getSouvenirsGroupedByYear(souvenirs);

        // Цикл использую для вывода, чтобы добавить символы табуляции и перевода строк
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Integer, List<Data>> entrySet : result.entrySet()) {
            stringBuilder.append(entrySet.getKey()).append("\n");
            List<Data> sous = entrySet.getValue();
            for (Data s : sous) {
                stringBuilder.append("   ").append(s).append("\n");
            }
        }
        System.out.println(stringBuilder);
    }

    /**Удалить заданного производителя и его сувениры.*/
    @Test
    void testDeleteSelectedManufacturerAndItsSouvenirs() {

        Data manToDelete = manufacturers.get(0);

        List<Data> mans = List.copyOf(manufacturers);
        List<Data> sous = List.copyOf(souvenirs);

        String mansClassName = mans.get(0).getClass().getName();
        String sousClassName = sous.get(0).getClass().getName();

        Map<String, List<Data>> resultMap = Queries.deleteSelectedManufacturerAndItsSouvenirs(
                mans, sous, manToDelete, mansClassName, sousClassName);

        mans = resultMap.get(mansClassName);
        sous = resultMap.get(sousClassName);

        System.out.println(new DataList(mans));
        System.out.println(new DataList(sous));
        //System.out.println(new DataList(manufacturers));
        //System.out.println(new DataList(souvenirs));

        assertEquals(
                Arrays.asList(manufacturers.get(1), manufacturers.get(2), manufacturers.get(3), manufacturers.get(4), manufacturers.get(5)),
                mans);

        assertEquals(
                Arrays.asList(
                        souvenirs.get(1),  souvenirs.get(2),  souvenirs.get(3),  souvenirs.get(4),
                        souvenirs.get(5),  souvenirs.get(7),  souvenirs.get(8),  souvenirs.get(9),
                        souvenirs.get(10), souvenirs.get(11), souvenirs.get(13), souvenirs.get(14)),
                sous);
    }
}