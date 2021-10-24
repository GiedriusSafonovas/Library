package lt.visma.library;

import java.util.*;

public class Filter {
    private String author;
    private String category;
    private String language;
    private String isbn;
    private String name;
    private String availability;



    public Map<String,String> getFilter(){
        Map <String,String> map = new HashMap<>();
        map.put("author",author);
        map.put("category",category);
        map.put("language",language);
        map.put("isbn",isbn);
        map.put("name",name);
        map.put("availability",availability);
        return map;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
