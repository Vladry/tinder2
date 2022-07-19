package vlad.domain;

public class User {
    private Long id;
    private String name;
    private String email;
    private String login;
    private String avatar;

    public User(){};
    public User(Long id, String name){this.id = id; this.name = name;};
    public User(Long id, String name, String email, String login, String avatar){
        this.id = id;
        this.name = name;
        this.email = email;
        this.login = login;
        this.avatar = avatar;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString(){
        String formattedStr = "\nUser id:%d, name:%s, login:%s, email:%s, avatar:%s";
        return String.format(formattedStr, this.id, this.name, this.login, this.email, this.avatar);
    }
}
