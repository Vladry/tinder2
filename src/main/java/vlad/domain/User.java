package vlad.domain;

public class User {
    Long id;
    String name;
    String email;
    String login;

    public User(){};
    public User(Long id, String name){this.id = id; this.name = name;};
    public User(Long id, String name, String email, String login){
        this.id = id;
        this.name = name;
        this.email = email;
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
