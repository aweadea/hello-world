package priv.zyz.service;

import priv.zyz.entity.Pet;
import priv.zyz.entity.PetStore;
import priv.zyz.service.interfaces.Accountable;
import priv.zyz.service.interfaces.Breedable;
import priv.zyz.service.interfaces.Buyable;
import priv.zyz.service.interfaces.Sellable;

import java.util.List;

public interface PetStoreService extends Buyable, Sellable, Accountable, Breedable {
    /**
     * 根据商店id获取宠物
     * @param storeId   传入商店id
     * @return List<Pet> 返回宠物集合
     */
    List<Pet> getPetsInStock(int storeId);

    /**
     *  根据商店id获取宠物品种
     * @param storeId   传入商店id
     * @return List<Pet> 返回宠物集合
     */
    List<Pet> getPetBreed(int storeId);

    /**
     * 收费
     * @param pet   传入一个宠物实体类
     */
    void charge(Pet pet);

    /**
     *  商店登录方法
     * @param name  传入商店名称
     * @param pwd   传入商店密码
     * @return  返回商店实体类
     */
    PetStore login(String name,String pwd);

    /**
     * 查询所有宠物商店姓名
     * @return  返回宠物商店集合
     */
    List<PetStore> getAllStore();

}
