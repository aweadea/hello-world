package priv.zyz.entity;

/**
 * 宠物商店类
 */
public class PetStore {
    private int id;     //宠物商店id
    private String name;     //宠物商店名称
    private String passWord;     //宠物商店登录密码
    private int balance;     //宠物商店结余

    public PetStore(int id, String name, String passWord, int balance) {
        this.id = id;
        this.name = name;
        this.passWord = passWord;
        this.balance = balance;
    }

    public PetStore() {
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

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
