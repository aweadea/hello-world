package priv.zyz.dao.impl;

import priv.zyz.entity.Pet;
import priv.zyz.util.DBHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于提取公共的方法
 */
public class PublicImpl extends DBHelper {
    /**
     *  查找宠物
     * @param sql 传入Sql语句
     * @return  返回宠物集合
     */
    public List<Pet> getPets(String sql){
        Object[] objs = {};
        List<Pet> lists = new ArrayList<>();
        try {
            ResultSet rs = select(sql,objs);
            while (rs.next()){
                Pet pet = new Pet();
                pet.setId(rs.getInt(1));
                pet.setName(rs.getString(2));
                pet.setTypeName(rs.getString(3));
                pet.setHealth(rs.getInt(4));
                pet.setLove(rs.getInt(5));
                pet.setBirthday(rs.getString(6));
                pet.setOwnerId(rs.getInt(7));
                pet.setStoreId(rs.getInt(8));
                lists.add(pet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
}
