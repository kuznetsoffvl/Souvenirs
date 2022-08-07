import logic.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.json.JSONObject;
import org.json.JSONArray;

public class Main {
    public static void main(String[] args) {

        new Main().run();

    }

    private void run1() {
        //System.out.println(LocalDate.parse("2022-10-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    private void run(){

        List<Data> manufacturers = new Main().fillManufacturers();
        List<Data> souvenirs = new Main().fillSouvenirs(manufacturers);

        String path = "manufacturers.json";

        //new Main().writeDataToFile(manufacturers, path, Manufacturer.class.getName());
        List<Data> mans = new Main().readDataFromFile(path, Manufacturer.class);

        System.out.println(mans);

        path = "souvenirs.json";
        //new Main().writeDataToFile(souvenirs, path, Souvenir.class.getName());
        List<Data> sous = new Main().readDataFromFile(path, Souvenir.class);

        System.out.println(sous);

    }

    private List<Data> readDataFromFile(String path, Class clazz) {
        List<Data> result = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {
            String line;
            while (true) {
                try {
                    if (!((line = reader.readLine())!=null)) break;

                    JSONObject json  = new JSONObject(line);
                    JSONArray jsonArray = json.getJSONArray(clazz.getName());
                    for (Object obj : jsonArray) {
                        Data newData = getObjectByJSONObject((JSONObject) obj, clazz);
                        result.add(newData);
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Data getObjectByJSONObject(JSONObject jsonObject, Class clazz){
        Data result = null;
        if ("Manufacturer".contains(clazz.getSimpleName())) {
            result = new Manufacturer(
                    jsonObject.getString("name"),
                    jsonObject.getEnum(Country.class, "country"));
        } else if ("Souvenir".contains(clazz.getSimpleName())){
            result = new Souvenir(
                    jsonObject.getString("name"),
                    jsonObject.getString("manufacturerName"),
                    LocalDate.parse(jsonObject.getString("releaseDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    jsonObject.getInt("price")
            );
        } //else System.out.println("className" + clazz.getName());
        return result;
    }

    private void writeDataToFile(List<Data> data, String path, String key) {

        JSONObject json = new JSONObject();
        json.put(key, data);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            //data.stream().forEach();
            writer.write(json.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Data> fillSouvenirs(List<Data> manufacturers) {
        List<Data> list = new ArrayList<>();
        ManufacturerList mList = new ManufacturerList(manufacturers);

        list.add(new Souvenir("Cup", mList.getNext().getName(), LocalDate.of(2020, 12, 21), 2));
        list.add(new Souvenir("Pen", mList.getNext().getName(), LocalDate.of(2019, 10, 10), 3));
        list.add(new Souvenir("Lighter", mList.getNext().getName(), LocalDate.of(2018, 6, 15), 4));
        list.add(new Souvenir("Cap", mList.getNext().getName(), LocalDate.of(2016, 8, 23), 6));
        list.add(new Souvenir("Trinket", mList.getNext().getName(), LocalDate.of(2022, 7, 18), 8));
        list.add(new Souvenir("T-Shirt", mList.getNext().getName(), LocalDate.of(2021, 5, 2), 10));
        return list;
    }

    public List<Data> fillManufacturers() {
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
