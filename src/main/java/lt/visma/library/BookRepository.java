package lt.visma.library;


import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component
public class BookRepository{
    private List<Book> bookList;
    private File library;

    BookRepository() {
        library = new File("Library.json");
        bookList = new ArrayList<>();
        Collection data = Json.readLibraryJson(library, Book[].class);
        if(data != null) bookList.addAll(data);
        long maxguid = 0;
        for (Book book : bookList) {
            if (book.getGuid() > maxguid) {
                maxguid = book.getGuid();
            }
        }
        Book.setCounter(maxguid);
    }

    public void writeJson() {
        Json.writeJson(library, bookList);
    }

    public void updateBookList(Book book) {
        int bookIndex = bookList.indexOf(book);
        bookList.remove(bookIndex);
        bookList.set(bookIndex, book);
    }

    public void addBook(Book book) {
        bookList.add(book);
        writeJson();
    }

    public void takeBook(Book book) {
        if (bookList.contains(book)) {
            int bookIndex = bookList.indexOf(book);
            if (!bookList.get(bookIndex).isTaken()) {
                bookList.get(bookIndex).setTaken(true);
            }
            writeJson();
        }
    }

    public Book getBookByGuid(long id) {
        for (Book book : bookList) {
            if (book.getGuid() == id) {
                return book;
            }
        }
        return null;
    }

    public void deleteBook(Book book) {
        if (bookList.contains(book)) {
            bookList.remove(book);
            writeJson();
        }
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Book> getFilteredBooks(Map<String,String> filter) {
        List<Book> filteredList = new ArrayList<>();
        filteredList.addAll(bookList);
        for (Map.Entry<String,String> set: filter.entrySet()) {
            for (Book book : bookList) {
                switch (set.getKey()) {
                    case "author": {
                        //Author
                        String parameter = set.getValue();
                        if (parameter != null) {
                            if (!parameter.equals(book.getAuthor())) {
                                filteredList.remove(book);
                            }
                        }
                    }
                    break;
                    case "category": {
                        //Category
                        String parameter = set.getValue();
                        if (parameter != null) {
                            if (!parameter.equals(book.getCategory())) {
                                filteredList.remove(book);
                            }
                        }
                    }
                    break;
                    case "language": {
                        //Language
                        String parameter = set.getValue();
                        if (parameter != null) {
                            if (!parameter.equals(book.getLanguage())) {
                                filteredList.remove(book);
                            }
                        }
                    }
                    break;
                    case "isbn": {
                        //ISBN
                        String parameter = set.getValue();
                        if (parameter != null) {
                            if (!parameter.equals(book.getIsbn())) {
                                filteredList.remove(book);
                            }
                        }
                    }
                    break;
                    case "name": {
                        //Name
                        String parameter = set.getValue();
                        if (parameter != null) {
                            if (!parameter.equals(book.getName())) {
                                filteredList.remove(book);
                            }
                        }
                    }
                    break;
                    case "availability": {
                        //Availability
                        String parameter = set.getValue();
                        if (parameter != null) {
                            if (parameter.toLowerCase(Locale.ROOT).equals("taken")) {
                                if (!book.isTaken()) {
                                    filteredList.remove(book);
                                }
                            } else if (parameter.toLowerCase(Locale.ROOT).equals("available")) {
                                if (book.isTaken()) {
                                    filteredList.remove(book);
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
        return filteredList;
    }
}
