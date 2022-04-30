package priv.zyz.dao.impl;

import priv.zyz.dao.PetStoreDao;
import priv.zyz.entity.Account;
import priv.zyz.entity.Pet;
import priv.zyz.entity.PetStore;
import priv.zyz.util.DBHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PetStoreDaoImpl extends DBHelper implements PetStoreDao {
    /**
     * 查询所有宠物商店信息
     * @return  返回宠物商店集合
     */
    @Override
    public List<PetStore> getAllStore() {
        String sqlSelect = "SELECT * FROM PetStore";
        Object[] objs = {};
        List<PetStore> lists = new ArrayList<>();
        try {
            ResultSet rs = select(sqlSelect,objs);
            while(rs.next()){
                PetStore petStore = new PetStore();
                petStore.setId(rs.getInt(1));
                petStore.setName(rs.getString(2));
                petStore.setPassWord(rs.getString(3));
                petStore.setBalance(rs.getInt(4));
                lists.add(petStore);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    /**
     * 卖方法
     * @param pet   传入宠物实体类
     * @return 返回boolean值用于判断是否成功
     */
    @Override
    public boolean sell(Pet pet) {
        return charge(pet,"+");     //调用本类收费方法
    }

    /**
     * 交易方法
     * @param pet 传入宠物实体类
     * @param type  传入交易类型
     * @return   返回boolean值用于判断是否成功
     */
    @Override
    public boolean charge(Pet pet,String type) {
        String sqlUpdate = "UPDATE petstore SET balance = balance"+type+"140 WHERE id = ?";
        Object[] objs = {pet.getStoreId()};
        try {
            return update(sqlUpdate,objs)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 买方法
     * @param pet   传入一个宠物实体类
     * @return  返回boolean值用于判断是否成功
     */
    @Override
    public boolean buy(Pet pet) {
        String sqlUpdate = "UPDATE pet SET store_id = ? WHERE id = ?";
        Object[] objs = {pet.getStoreId(),pet.getId()};
        try {
            update(sqlUpdate,objs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return charge(pet,"-");
    }

    /**
     * 宠物商店登录的方法
     * @param name  传入宠物商店名称
     * @param pwd   传入宠物商店密码
     * @return  返回一个宠物商店实体类
     */
    @Override
    public PetStore Login(String name, String pwd) {
        String sqlSelect = "SELECT * FROM petstore WHERE name = ? AND password = ?";
        Object[] objs = {name,pwd};
        PetStore petStore = new PetStore();
        try {
            ResultSet rs = select(sqlSelect,objs);
            if (rs.next()){
                petStore.setId(rs.getInt(1));
                petStore.setName(rs.getString(2));
                petStore.setPassWord(rs.getString(3));
                petStore.setBalance(rs.getInt(4));
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return petStore;
    }

    /**
     * 根据商店id查询宠物
     * @param storeId   传入商店id
     * @return  返回宠物集合
     */
    @Override
    public List<Pet> getPetsInStock(int storeId) {
        String sqlSelect = "SELECT * FROM pet WHERE store_id = " + storeId;
        return new PublicImpl().getPets(sqlSelect);
    }

    /**
     * 根据商店id查询宠物品种
     * @param storeId   传入商店id
     * @return  返回宠物集合
     */
    @Override
    public List<Pet> getPetBreed(int storeId) {
        String sqlSelect = "SELECT * FROM pet WHERE store_id = " + storeId +"GROUP BY type_name";
        return new PublicImpl().getPets(sqlSelect);
    }

    /**
     * 根据宠物商店id查询账单
     * @param storeId   传入商店id
     * @return  返回账单集合
     */
    @Override
    public List<Account> getAccount(int storeId) {
        String sqlSelect = "SELECT * FRO WHERE seller_id = ? OR buyer_id = ?";
        Object[] objs = {storeId,storeId};
        List<Account> lists = new ArrayList<>();
        try {
            ResultSet rs = select(sqlSelect,objs);
            while (rs.next()){
                Account account = new Account();
                account.setId(rs.getInt(1));
                account.setDealType(rs.getInt(2));
                account.setPetId(rs.getInt(3));
                account.setSellerId(rs.getInt(4));
                account.setBuyerId(rs.getInt(5));
                account.setPrice(rs.getInt(6));
                account.setDealTime(rs.getString(7));
                lists.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    /**
     * 根据宠物类型查询宠物
     * @param petType   传入宠物类型
     * @return  返回宠物集合
     */
    @Override
    public List<Pet> breed(String petType) {
        String sqlSelect = "SELECT * FROM pet WHERE type_name = '"+petType+"'";
        return new PublicImpl().getPets(sqlSelect);
    }
}
