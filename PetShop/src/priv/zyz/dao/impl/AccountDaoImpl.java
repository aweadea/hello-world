package priv.zyz.dao.impl;

import priv.zyz.dao.AccountDao;
import priv.zyz.entity.Pet;
import priv.zyz.util.DBHelper;

public class AccountDaoImpl extends DBHelper implements AccountDao {

    /**
     * 添加账单信息
     * @param pet   传入宠物实体类
     * @param deal  传入交易类型
     * @param money 传入交易金额
     * @return  返回boolean值用于判断是否添加成功
     */
    @Override
    public boolean addAccount(Pet pet, int deal, int money) {
        String sqlInsert = "INSERT INTO account (deal_type,pet_id,seller_id,buyer_id,price) " +
                "values (?,?,?,?,?)";
        Object[] objs = {};
        if(deal == 1 || deal == 3){
            objs = new Object[]{deal,pet.getId(),pet.getStoreId(),pet.getOwnerId(),money};
        }else if(deal == 2){
            objs = new Object[]{deal,pet.getId(),pet.getOwnerId(),pet.getStoreId(),money};
        }
        try {
            return update(sqlInsert,objs)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
