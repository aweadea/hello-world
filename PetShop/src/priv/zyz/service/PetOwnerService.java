package priv.zyz.service;

import priv.zyz.entity.PetOwner;
import priv.zyz.service.interfaces.*;

import java.util.List;

public interface PetOwnerService extends Buyable, Sellable{
    /**
     * 返回所有宠物主人信息
     * @return  返回主人姓名集合
     */
    List<PetOwner> getAllOwner();

    /**
     * 登录方法
     * @return 返回一个主人实体类
     */
    PetOwner login(String name,String pwd);

}
