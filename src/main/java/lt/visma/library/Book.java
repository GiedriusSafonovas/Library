package lt.visma.library;

import java.time.LocalDate;

public class Book {
    private String name;
    private String author;
    private String category;
    private String language;
    private String publicationDate;
    private String isbn;
    private long guid;
    private boolean taken;
    private static long counter;

    public Book(){
        counter++;
        guid = counter;
    }

    public static long getCounter() {
        return counter;
    }

    public static void setCounter(long counter) {
        Book.counter = counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public long getGuid() {
        return guid;
    }

    public void setGuid(long guid) {
        this.guid = guid;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    @Override
    public String toString() {
        return String.format("Name = %s, Author = %s, Category = %s, Language = %s, Publication date = %s, ISBN = %s, GUID = %d, taken = %b",
                name, author, category, language, publicationDate, isbn, guid, taken);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Book){
            if(this.guid == ((Book) obj).guid){
                return true;
            }
        }
        return false;
    }
}

