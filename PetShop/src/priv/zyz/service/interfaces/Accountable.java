package priv.zyz.service.interfaces;

import priv.zyz.entity.Account;

import java.util.List;

public interface Accountable {
    /**
     * 查询账单
     * @param storeId 传入一个商店id
     * @return  List<Account> 返回一个账单信息列表
     */
    List<Account> getAccount(int storeId);
}
