package entity;

public class GeriBildirim {
    private int id;
    private String yorum;
    private Users user;

    public GeriBildirim() {
    }

    public GeriBildirim(int id, String yorum, Users user) {
        this.id = id;
        this.yorum = yorum;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYorum() {
        return yorum;
    }

    public void setYorum(String yorum) {
        this.yorum = yorum;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
