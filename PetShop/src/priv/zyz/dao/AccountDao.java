package priv.zyz.dao;

import priv.zyz.entity.Pet;

public interface AccountDao {
    /**
     * 添加一条账单信息
     * @param pet   传入宠物实体类
     * @param deal  传入交易类型
     * @param money 传入交易金额
     * @return  返回boolean用于判断是否添加成功
     */
    boolean addAccount(Pet pet, int deal, int money);
}
