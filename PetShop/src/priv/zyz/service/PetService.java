package priv.zyz.service;
import priv.zyz.entity.Pet;

import java.util.List;

public interface PetService {
    /**
     * 查询所有宠物姓名
     * @return  返回宠物类集合
     */
    List<Pet> getAllPet();
}
