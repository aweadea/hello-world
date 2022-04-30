package priv.zyz.service.interfaces;

import priv.zyz.entity.Pet;

import java.util.List;

public interface Breedable {
    /**
     *  根据宠物类型查询宠物
     * @param petType   传入一个宠物类型
     * @return  List<Pet> 返回一个宠物
     */
    List<Pet> breed(String petType);
}
