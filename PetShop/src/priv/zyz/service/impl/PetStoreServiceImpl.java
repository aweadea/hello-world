package priv.zyz.service.impl;

import priv.zyz.dao.PetStoreDao;
import priv.zyz.dao.impl.PetStoreDaoImpl;
import priv.zyz.entity.Account;
import priv.zyz.entity.Pet;
import priv.zyz.entity.PetStore;
import priv.zyz.service.PetStoreService;

import java.util.List;

public class PetStoreServiceImpl implements PetStoreService {
    PetStoreDao petStoreDao = new PetStoreDaoImpl();

    /**
     * 收费
     * @param pet   传入一个宠物实体类
     */
    @Override
    public void charge(Pet pet) {
        petStoreDao.sell(pet);  //调用本类卖方法来进行收费
    }

    /**
     *  商店登录方法
     * @param name  传入商店名称
     * @param pwd   传入商店密码
     * @return  返回商店实体类
     */
    @Override
    public PetStore login(String name,String pwd) {
        return petStoreDao.Login(name,pwd);
    }

    /**
     * 查询所有宠物商店姓名
     * @return  返回宠物商店集合
     */
    @Override
    public List<PetStore> getAllStore() {
        return petStoreDao.getAllStore();
    }

    /**
     *  购买方法
     * @param pet   传入宠物实体类
     */
    @Override
    public void buy(Pet pet) {
        petStoreDao.buy(pet);
    }

    /**
     * 卖出方法
     * @param pet   传入宠物实体类
     */
    @Override
    public void sell(Pet pet) {
        charge(pet);  //调用本类收费方法
    }

    /**
     * 查询账单
     * @param storeId 传入一个商店id
     * @return  返回商店集合
     */
    @Override
    public List<Account> getAccount(int storeId) {
        return petStoreDao.getAccount(storeId);
    }

    /**
     * 根据宠物类型查询宠物
     * @param petType   传入一个宠物类型
     * @return  返回宠物集合
     */
    @Override
    public List<Pet> breed(String petType) {
        return petStoreDao.breed(petType);
    }

    /**
     *  根据商店id获取宠物
     * @param storeId   传入商店id
     * @return  返回宠物集合
     */
    @Override
    public List<Pet> getPetsInStock(int storeId) {
        return petStoreDao.getPetsInStock(storeId);
    }

    /**
     * 根据商店id获取宠物品种
     * @param storeId   传入商店id
     * @return  List<Pet> 返回宠物集合
     */
    @Override
    public List<Pet> getPetBreed(int storeId) {
        return petStoreDao.getPetBreed(storeId);
    }

}
