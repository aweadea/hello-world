package priv.zyz.service.impl;

import priv.zyz.dao.AccountDao;
import priv.zyz.dao.impl.AccountDaoImpl;
import priv.zyz.entity.Pet;
import priv.zyz.service.AccountService;

public class AccountServiceImpl implements AccountService {
    AccountDao accountDao = new AccountDaoImpl();

    /**
     * 添加账单信息
     * @param pet   传入宠物实体类
     * @param deal  传入交易类型
     * @param money 传入交易金额
     * @return 返回boolean值用于判断是否执行成功
     */
    @Override
    public boolean addAccount(Pet pet, int deal, int money) {
        return accountDao.addAccount(pet,deal,money);
    }
}
