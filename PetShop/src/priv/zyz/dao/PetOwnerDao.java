package priv.zyz.dao;

import priv.zyz.entity.Pet;
import priv.zyz.entity.PetOwner;

import java.util.List;

public interface PetOwnerDao {
    /**
     * 查询所有宠物主人信息
     * @return  返回一个主人类集合
     */
    List<PetOwner> getAllOwner();

    /**
     * 主人登录方法
     * @param name  传入主人登录名
     * @param pwd   传入主人登录密码
     * @return  返回主人实体类
     */
    PetOwner login(String name,String pwd);

    /**
     * 买方法
     * @param pet   传入宠物实体类
     * @return  返回boolean值用于判断是否执行成功
     */
    boolean buy(Pet pet);

    /**
     * 卖方法
     * @param pet 传入宠物实体类
     * @return 返回boolean值用于判断是否执行成功
     */
    boolean sell(Pet pet);

}
