package priv.zyz.service.impl;

import priv.zyz.dao.PetDao;
import priv.zyz.dao.impl.PetDaoImpl;
import priv.zyz.entity.Pet;
import priv.zyz.service.PetService;

import java.util.List;

public class PetServiceImpl implements PetService {
    PetDao petDao = new PetDaoImpl();

    /**
     * 查询宠物所有信息
     * @return  返回宠物类集合
     */
    @Override
    public List<Pet> getAllPet() {
        return petDao.getAllPet();
    }
}
