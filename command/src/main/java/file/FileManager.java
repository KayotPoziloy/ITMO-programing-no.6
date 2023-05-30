package file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import collection.HumanBeing;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Класс для работы с файлами (чтение/запись)
 */
public class FileManager {
    /**
     * Парсер формата json
     */
    private Gson gson = new GsonBuilder()
            .setDateFormat("sss")
            .registerTypeAdapter(HumanBeing.class, new CustomConverter())
            .create();

    /**
     * Метод записывает объекты из файла .json в коллекцию
     * @param file - путь до файла
     * @return коллекция объектов
     */
    public HumanBeing[] parseToCollection(String file) {
        gson = new GsonBuilder()
                .setDateFormat("SSS")
                .registerTypeAdapter(HumanBeing.class, new CustomConverter())
                .create();

        ArrayList<HumanBeing> humanBeings = null;
        try {
            String json = Files.readString(Paths.get(file));
            humanBeings = gson.fromJson(json, new TypeToken<ArrayList<HumanBeing>>() {}.getType());

        } catch (JsonSyntaxException e) {
            System.err.println("Ошибка синтаксиса " + e);
            System.exit(-1);
        } catch (IOException e) {
            System.err.println("ошибка с файлом " + e);
            throw new RuntimeException(e);
        }

        if (humanBeings == null) {
            return new HumanBeing[0];
        }

        HumanBeing[] humanArr = new HumanBeing[humanBeings.size()];
        return humanBeings.toArray(humanArr);
    }

    /**
     * Запись коллекции в файл
     * @param collection - коллекция для записи в файл
     */
    public void writeCollection(Collection<?> collection) {
        try (FileWriter fileWriter = new FileWriter(System.getenv("Lab5_save"))) {
            fileWriter.write(gson.toJson(collection));
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}