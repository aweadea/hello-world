package priv.zyz.entity;

/**
 * 宠物类
 */
public class Pet {
    private int id; //宠物id
    private String name;    //宠物名
    private String typeName;    //宠物类型
    private int health;    //健康值
    private int love;    //亲密度
    private String birthday;    //出生日期
    private int ownerId;    //宠物主人id
    private int storeId;    //宠物所属商店id

    public Pet(int id, String name, String typeName, int health, int love, String birthday, int ownerId, int storeId) {
        this.id = id;
        this.name = name;
        this.typeName = typeName;
        this.health = health;
        this.love = love;
        this.birthday = birthday;
        this.ownerId = ownerId;
        this.storeId = storeId;
    }

    public Pet() {
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
