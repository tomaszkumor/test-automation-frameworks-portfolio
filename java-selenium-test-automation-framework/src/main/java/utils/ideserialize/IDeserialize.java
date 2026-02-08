package utils.ideserialize;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public interface IDeserialize<T> {
    default T deserialize(String path, Class<T> classOf) {
        String json = "";

        try {
            json = Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        return new Gson().fromJson(json, classOf);
    }
}
