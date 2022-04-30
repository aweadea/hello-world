package priv.zyz.service;

import priv.zyz.entity.Pet;

public interface AccountService {
    /**
     * 添加账单信息
     * @param pet   传入宠物实体类
     * @param deal  传入交易类型
     * @param money 传入交易金额
     * @return  返回boolean值用于判断是否执行成功
     */
    boolean addAccount(Pet pet, int deal, int money);
}
