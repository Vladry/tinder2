package vlad.dao;

public class User {
    private Long id;
    private String name;
    private int age;
    private Long groupId;
    private String login;
    private String password;

    public User(Long id, String name, int age, Long groupId, String login, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.groupId = groupId;
        this.login = login;
        this.password = password;
    }

    public User(String name, int age, Long groupId, String login, String password) {
        this.name = name;
        this.age = age;
        this.groupId = groupId;
        this.login = login;
        this.password = password;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", groupId=" + groupId +
                ", login=" + login +
                ", pass=" + password +
                '}';
    }
}
