package priv.zyz.dao;

import priv.zyz.entity.Account;
import priv.zyz.entity.Pet;
import priv.zyz.entity.PetStore;

import java.util.List;

public interface PetStoreDao {
    /**
     *  查询所有宠物商店信息
     * @return  返回一个宠物商店姓名集合
     */
    List<PetStore> getAllStore();

    /**
     * 买方法
     * @param pet   传入宠物实体类
     * @return  返回boolean判断是否成功
     */
    boolean sell(Pet pet);

    /**
     * 收费方法
     * @param pet 传入宠物实体类
     * @param type  传入交易类型
     * @return  返回boolean判断是否成功
     */
    boolean charge(Pet pet,String type);

    /**
     * 买方法
     * @param pet   传入一个宠物实体类
     * @return  返回boolean判断是否成功
     */
    boolean buy(Pet pet);

    /**
     * 宠物商店登录方法
     * @param name  传入宠物商店名称
     * @param pwd   传入宠物商店密码
     * @return  返回一个宠物商店实体类
     */
    PetStore Login(String name,String pwd);

    /**
     * 根据商店id查询宠物
     * @param storeId   传入商店id
     * @return  返回宠物集合
     */
    List<Pet> getPetsInStock(int storeId);

    /**
     * 根据商店id查询宠物品种
     * @param storeId   传入商店id
     * @return  返回宠物集合
     */
    List<Pet> getPetBreed(int storeId);

    /**
     * 根据商店id查询账单
     * @param storeId   传入商店id
     * @return  返回账单集合
     */
    List<Account> getAccount(int storeId);

    /**
     * 根据宠物类型查询宠物
     * @param petType   传入宠物类型
     * @return  返回宠物集合
     */
    List<Pet> breed(String petType);
}
