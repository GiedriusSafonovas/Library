package lt.visma.library;

public class User {
    private String userName;
    private int booksTaken;

    User(){}

    User(String username){
        this.userName = username;
    }

    public String getName() {
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public int getBooksTaken() {
        return booksTaken;
    }

    public void setBooksTaken(int booksTaken) {
        this.booksTaken = booksTaken;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User){
            if(this.userName.equals(((User) obj).userName)){
                return true;
            }
        }
        return false;
    }
}
