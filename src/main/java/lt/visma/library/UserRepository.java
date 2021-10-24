package lt.visma.library;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class UserRepository {
    private List<User> users;
    private File userFile;


    UserRepository(){
        users = new ArrayList<>();
        userFile = new File("Users.json");
        Collection data = Json.readUserJson(userFile, User[].class);
        if(data != null) users.addAll(data);
    }

    public void writeJson(){
        Json.writeJson(userFile,users);
    }

    public void addUser(User user){
        users.add(user);
        writeJson();
    }

    public void updateUser(User user){
        if(users.contains(user)){
            int userIndex = users.indexOf(user);
            int booksTaken = users.get(userIndex).getBooksTaken() + 1;
            users.get(userIndex).setBooksTaken(booksTaken);
            writeJson();
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUserByName(User user){
        return users.get(users.indexOf(user));
    }
}
