package priv.zyz.entity;

/**
 * 宠物主人类
 */
public class PetOwner {
    private int id;     //主人id
    private String name;     //主人姓名
    private String passWord;     //主人登录密码
    private int money;     //主人元宝

    public PetOwner(int id, String name, String passWord, int money) {
        this.id = id;
        this.name = name;
        this.passWord = passWord;
        this.money = money;
    }

    public PetOwner() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
