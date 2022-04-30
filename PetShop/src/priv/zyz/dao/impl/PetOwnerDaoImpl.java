package priv.zyz.dao.impl;

import priv.zyz.dao.PetOwnerDao;
import priv.zyz.entity.Pet;
import priv.zyz.entity.PetOwner;
import priv.zyz.util.DBHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PetOwnerDaoImpl extends DBHelper implements PetOwnerDao {
    /**
     * 查询所有宠物主人信息
     * @return  返回主人类集合
     */
    @Override
    public List<PetOwner> getAllOwner() {

        String sqlSelect = "SELECT * FROM petowner";
        Object[] objs = {};
        List<PetOwner> lists = new ArrayList<>();
        try {
            ResultSet rs = select(sqlSelect,objs);
            while(rs.next()){
                PetOwner petOwner = new PetOwner();
                petOwner.setId(rs.getInt(1));
                petOwner.setName(rs.getString(2));
                petOwner.setPassWord(rs.getString(3));
                petOwner.setMoney(rs.getInt(4));
                lists.add(petOwner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    /**
     * 主人登录方法
     * @param name  传入主人登录名
     * @param pwd   传入主人登录密码
     * @return  返回主人实体类
     */
    @Override
public PetOwner login(String name,String pwd) {
        String sqlSelect = "SELECT * FROM petowner WHERE name = ? AND password = ?";
        Object[] objs = {name,pwd};
        PetOwner petOwner = new PetOwner();
        try {
            ResultSet rs = select(sqlSelect,objs);
            if (rs.next()){
                petOwner.setId(rs.getInt(1));
                petOwner.setName(rs.getString(2));
                petOwner.setPassWord(rs.getString(3));
                petOwner.setMoney(rs.getInt(4));
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return petOwner;
    }

    /**
     * 买方法
     * @param pet   传入宠物实体类
     * @return  返回boolean值用于判断是否执行成功
     */
    @Override
    public boolean buy(Pet pet) {
        String sqlUpdate = "UPDATE pet SET owner_id = ? WHERE id = ?";
        Object[] objs = {pet.getOwnerId(),pet.getId()};
        int num = 0;
        try {
            num = update(sqlUpdate,objs);
            objs = new Object[]{pet.getOwnerId()};
            String sqlUpdate1 = "UPDATE petowner SET money = money-140 WHERE id = ?";
            num = update(sqlUpdate1,objs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num>0;
    }

    /**
     *  卖方法
     * @param pet 传入宠物实体类
     * @return  返回boolean值用于判断是否执行成功
     */
    @Override
    public boolean sell(Pet pet) {
        String sqlUpdate = "UPDATE pet SET owner_id = null WHERE id = ?";
        Object[] objs = {pet.getId()};
        int num = 0;
        try {
            num = update(sqlUpdate,objs);
            objs = new Object[]{pet.getOwnerId()};
            String sqlUpdate1 = "UPDATE petowner SET money = money+140 WHERE id = ?";
            num = update(sqlUpdate1,objs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num>0;
    }
}
