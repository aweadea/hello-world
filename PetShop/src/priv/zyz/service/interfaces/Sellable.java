package priv.zyz.service.interfaces;

import priv.zyz.entity.Pet;

public interface Sellable {
    /**
     *  卖
     * @param pet
     */
    void sell(Pet pet);
}
