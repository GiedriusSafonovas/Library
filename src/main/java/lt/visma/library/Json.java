package lt.visma.library;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Json {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Collection readLibraryJson(File f, Class<Book[]> aClass) {
        try {
            return Arrays.asList(Json.objectMapper.readValue(f,aClass));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Collection readUserJson(File f, Class<User[]> aClass) {
        try {
            return Arrays.asList(Json.objectMapper.readValue(f,aClass));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static void writeJson(File f, List<?> list){
        try {
            objectMapper.writeValue(f,list);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
