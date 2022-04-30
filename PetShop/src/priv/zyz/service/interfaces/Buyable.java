package priv.zyz.service.interfaces;

import priv.zyz.entity.Pet;

public interface Buyable {
    /**
     * 买
     * @param pet   宠物实体类
     */
    void buy(Pet pet);
}
