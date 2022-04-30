package priv.zyz.dao.impl;

import priv.zyz.dao.PetDao;
import priv.zyz.entity.Pet;
import priv.zyz.util.DBHelper;

import java.util.List;

public class  PetDaoImpl extends DBHelper implements PetDao {
    /**
     * 查询所有宠物信息
     * @return  返回宠物集合
     */
    @Override
    public List<Pet> getAllPet() {
        String sqlSelect = "SELECT * FROM pet";
        return new PublicImpl().getPets(sqlSelect);
    }
}
