
import logic.*;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Вся реализация возможнотей прописана в Queries, проверка - в MainTest

        new Main().run();
    }

    private void run(){

        // проверяем, как работают файловые чтение/запись

        List<Data> manufacturers = Main.fillManufacturers();
        List<Data> souvenirs = Main.fillSouvenirs(manufacturers);

        Files.writeDataToFile(manufacturers, Manufacturer.class);
        List<Data> mans = Files.readDataFromFile(Manufacturer.class);

        System.out.println(new DataList(mans));

        Files.writeDataToFile(souvenirs,Souvenir.class);
        List<Data> sous = Files.readDataFromFile(Souvenir.class);

        System.out.println(new DataList(sous));
    }


    public static List<Data> fillSouvenirs(List<Data> manufacturers) {

        DataList mList = new DataList(manufacturers);

        List<Data> list = new ArrayList<>();

        list.add(new Souvenir("Cup", Queries.getName(mList.getNext()), LocalDate.of(2020, 12, 21), 2));
        list.add(new Souvenir("Pen", Queries.getName(mList.getNext()), LocalDate.of(2019, 10, 10), 3));
        list.add(new Souvenir("Lighter", Queries.getName(mList.getNext()), LocalDate.of(2018, 6, 15), 4));
        list.add(new Souvenir("Cap", Queries.getName(mList.getNext()), LocalDate.of(2016, 8, 23), 6));
        list.add(new Souvenir("Trinket", Queries.getName(mList.getNext()), LocalDate.of(2022, 7, 18), 8));
        list.add(new Souvenir("T-Shirt", Queries.getName(mList.getNext()), LocalDate.of(2021, 5, 2), 10));
        list.add(new Souvenir("Magnet", Queries.getName(mList.getNext()), LocalDate.of(2021, 7, 6), 1));
        list.add(new Souvenir("Plate", Queries.getName(mList.getNext()), LocalDate.of(2020, 3, 31), 2));
        list.add(new Souvenir("Hourglass", Queries.getName(mList.getNext()), LocalDate.of(2019, 4, 23), 3));
        list.add(new Souvenir("Playing cards", Queries.getName(mList.getNext()), LocalDate.of(2020, 9, 3), 2));
        list.add(new Souvenir("Firework", Queries.getName(mList.getNext()), LocalDate.of(2021, 1, 8), 3));
        list.add(new Souvenir("Flag", Queries.getName(mList.getNext()), LocalDate.of(2022, 2, 4), 4));
        list.add(new Souvenir("Notepad", Queries.getName(mList.getNext()), LocalDate.of(2021, 5, 10), 2));
        list.add(new Souvenir("Piggy bank", Queries.getName(mList.getNext()), LocalDate.of(2020, 8, 15), 3));
        list.add(new Souvenir("Piggy bank", Queries.getName(mList.getNext()), LocalDate.of(2019, 8, 15), 4));

        return list;
    }

    public static List<Data> fillManufacturers() {

        ArrayList<Data> list = new ArrayList<>();

        list.add(new Manufacturer("Big Fun", Country.CHINA));
        list.add(new Manufacturer("Hi There", Country.UK));
        list.add(new Manufacturer("Lion Products", Country.GERMANY));
        list.add(new Manufacturer("Lovely Crafts", Country.FRANCE));
        list.add(new Manufacturer("Podvirya", Country.UKRAINE));
        list.add(new Manufacturer("Must Have", Country.USA));

        return list;
    }
}
