package priv.zyz.service.impl;

import priv.zyz.dao.PetOwnerDao;
import priv.zyz.dao.impl.PetOwnerDaoImpl;
import priv.zyz.entity.Account;
import priv.zyz.entity.Pet;
import priv.zyz.entity.PetOwner;
import priv.zyz.service.PetOwnerService;

import java.util.List;
import java.util.Scanner;

public class PetOwnerServiceImpl implements PetOwnerService {
    Scanner input = new Scanner(System.in);
    PetOwnerDao petOwnerDao = new PetOwnerDaoImpl();

    /**
     * 查询宠物主人所有信息
     * @return  返回宠物主人类集合
     */
    @Override
    public List<PetOwner> getAllOwner() {
        return petOwnerDao.getAllOwner();
    }

    /**
     * 宠物主人登录方法
     * @param name  传入宠物主人登录名
     * @param pwd   传入宠物主人登录密码
     * @return  返回宠物主人实体类
     */
    @Override
    public PetOwner login(String name,String pwd) {
        return petOwnerDao.login(name,pwd);
    }

    /**
     * 买方法
     * @param pet   传入宠物实体类
     */
    @Override
    public void buy(Pet pet) {
        if(petOwnerDao.buy(pet)){
            System.out.println("台账正确插入一条信息");
        }
    }

    /**
     * 卖方法
     * @param pet 传入宠物实体列
     */
    @Override
    public void sell(Pet pet) {
        if (petOwnerDao.sell(pet)){
            System.out.println("台账正确插入一条信息");
        }
    }
}
