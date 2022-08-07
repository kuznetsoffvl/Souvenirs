package logic;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Files {

    private static final String manufacturersFilePath = "manufacturers.json";
    private static final String souvenirsFilePath = "souvenirs.json";

    public static List<Data> readDataFromFile(Class clazz) {
        List<Data> result = new ArrayList<>();
        String path = getFilePathByClass(clazz);

        try (BufferedReader reader = java.nio.file.Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {
            String line;
            while (true) {
                try {
                    //if (!((line = reader.readLine())!=null)) break;
                    if ((line = reader.readLine())==null) break;

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
            //reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private static Data getObjectByJSONObject(JSONObject jsonObject, Class clazz){
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

    public static void writeDataToFile(List<Data> data, Class clazz) {

        String path = getFilePathByClass(clazz);

        JSONObject json = new JSONObject();
        json.put(clazz.getName(), data);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(json.toString());
            //writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFilePathByClass(Class clazz){

        return "Manufacturer".contains(clazz.getSimpleName())
                ?  manufacturersFilePath
                : "Souvenir".contains(clazz.getSimpleName())
                ? souvenirsFilePath
                : "";
    }
}
