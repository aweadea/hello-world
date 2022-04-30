package priv.zyz.dao;

import priv.zyz.entity.Pet;

import java.util.List;

public interface PetDao {
    /**
     * 查询所有宠物信息
     * @return  返回一个宠物姓名集合
     */
    List<Pet> getAllPet();
}
