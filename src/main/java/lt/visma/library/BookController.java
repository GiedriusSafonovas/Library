package lt.visma.library;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class BookController {
    @Autowired
    private BookRepository library;
    @Autowired
    private UserRepository users;

    //Rest API endpoint to add a new book.
    @PostMapping
    @RequestMapping("/addbook")
    public ResponseEntity addBook(@RequestBody Book book){
        library.addBook(book);
        return ResponseEntity.ok().build();
    }

    //Rest API endpoint to take a book from the library.
    @PutMapping
    @RequestMapping("/takebook")
    @ResponseBody
    public ResponseEntity takeBook(@RequestBody BookRequest bookRequest){
        Book book = library.getBookByGuid(bookRequest.getGuid());
        User user = bookRequest.getUser();
        int period = bookRequest.getPeriodInDays();
        if(book == null) return ResponseEntity.status(404).body("No such book found");
        if(period > 60) return ResponseEntity.status(403).body("Taking book for longer than 60 days is not allowed");
        if(library.getBookByGuid(book.getGuid()).isTaken()) return ResponseEntity.status(406).body("Book already taken");
        if(!users.getUsers().contains(user)){
            users.addUser(user);
            users.updateUser(user);
            library.takeBook(book);
            return ResponseEntity.ok().build();
        }else{
            if(users.getUserByName(user).getBooksTaken()>=3){
                return ResponseEntity.status(403).body("Taking more than 3 books is not allowed");
            }else{
                users.updateUser(user);
                library.takeBook(book);
                return ResponseEntity.ok().build();
            }
        }
    }

    //Rest API endpoint to get a book by GUID.
    @GetMapping
    @RequestMapping("/{guid}")
    public Book getBookByGuid(@PathVariable(value = "guid") long id){
        return library.getBookByGuid(id);
    }

    //Rest API endpoint to list all the books.
    @GetMapping
    public List<Book> getBooks(){
        return library.getBookList();
    }

    //Filter the data.
    @PostMapping
    @RequestMapping("/filter")
    public List<Book> getFilteredBooks(@RequestBody Filter filter){
        return library.getFilteredBooks(filter.getFilter());
    }

    //Rest API endpoint to delete a book.
    @DeleteMapping
    @RequestMapping("/removebook/{guid}")
    public void deleteBook(@PathVariable(value = "guid") long id){
        Book book = library.getBookByGuid(id);
        library.deleteBook(book);
    }




}
