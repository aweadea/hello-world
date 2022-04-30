package priv.zyz.test;

import priv.zyz.entity.Account;
import priv.zyz.entity.Pet;
import priv.zyz.entity.PetOwner;
import priv.zyz.entity.PetStore;
import priv.zyz.service.AccountService;
import priv.zyz.service.PetOwnerService;
import priv.zyz.service.PetService;
import priv.zyz.service.PetStoreService;
import priv.zyz.service.impl.AccountServiceImpl;
import priv.zyz.service.impl.PetOwnerServiceImpl;
import priv.zyz.service.impl.PetServiceImpl;
import priv.zyz.service.impl.PetStoreServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Methods {
    PetOwnerService petOwnerService = new PetOwnerServiceImpl();
    PetStoreService petStoreService = new PetStoreServiceImpl();
    PetService petService = new PetServiceImpl();
    AccountService accountService = new AccountServiceImpl();
    Scanner input = new Scanner(System.in);
    List<Pet> petList = new ArrayList<>();
    List<Account> accountList = new ArrayList<>();

    public void startPetShop(){
        System.out.println("宠物商店启动");
        showInfo();
        System.out.println("请选择登录模式，输入1为宠物主人登录，输入2为宠物商店登录");
        switch (input.nextInt()){
            case 1:
                ownerLogin();
                break;
            case 2:
                storeLogin();
                break;
        }
    }

    /**
     * 显示信息的方法
     */
    void showInfo(){
        System.out.println("Wonderland醒来，所有宠物从MYSQL中醒来");
        System.out.println("*************************************");
        showPetName();
        System.out.println("*************************************\n");
        System.out.println("所有主人从MYSQL中醒来");
        System.out.println("*************************************");
        showOwnerName();
        System.out.println("*************************************\n");

        System.out.println("所有宠物商店从MYSQL中醒来");
        System.out.println("*************************************");
        showStoreName();
        System.out.println("*************************************\n");
    }

    /**
     * 显示宠物姓名
     */
    void showPetName(){
        System.out.println("序号\t宠物姓名");
        petList = petService.getAllPet();
        int i = 0;
        for (Pet pets: petList) {
            System.out.println((++i)+"\t"+pets.getName());
        }

    }

    /**
     * 显示宠物主人姓名
     */
    void showOwnerName(){
        System.out.println("序号\t主人姓名");
        for (PetOwner petOwner: petOwnerService.getAllOwner()) {
            System.out.println(petOwner.getId()+"\t"+petOwner.getName());
        }
    }

    /**
     * 显示宠物商店名称
     */
    void showStoreName(){
        System.out.println("序号\t宠物商店名称");
        for (PetStore petStore: petStoreService.getAllStore()) {
            System.out.println(petStore.getId()+"\t"+petStore.getName());
        }
    }



    /**
     * 主人登录方法
     */
    void ownerLogin(){
        System.out.println("请先登录，请您输入主人的名字：");
        String name = input.next();
        System.out.println("请您输入主人的密码：");
        String pwd = input.next();
        PetOwner petOwner = petOwnerService.login(name,pwd);
        if(petOwner != null){
            System.out.println("-----恭喜您登录成功-----");
            System.out.println("-----你的基本信息：-----");
            System.out.println("姓名："+petOwner.getName());
            System.out.println("元宝数："+petOwner.getMoney());
            System.out.println("登录成功，您可以购买和卖出宠物，如果您想购买宠物请输入1，如果想卖出宠物请输入2");
            System.out.println("1.购买宠物");
            System.out.println("2.卖出宠物");
            int num = input.nextInt();
            if(num == 1){
                System.out.println("-----请输入要购买的范围：只输入选择项的序号-----");
                System.out.println("1.库存宠物");
                System.out.println("2.新培育宠物");
                int choose = input.nextInt();
                if(choose == 1){
                    ownerBuy(petOwner,"库存");
                }else if(choose == 2){
                    ownerBuy(petOwner,"新培育");
                }
            }else if(num == 2){
                ownerSell(petOwner);
            }
        }else{
            System.out.println("登录失败");
        }
    }

    /**
     * 主人买宠物的方法
     * @param petOwner  传入登录主人
     * @param type  购买方式
     */
    void ownerBuy(PetOwner petOwner,String type){
        System.out.println("----以下是"+type+"宠物----");
        System.out.println("序号\t宠物名称\t类型\t元宝数");
        List<Integer> numbers = new ArrayList<>();
        for (Pet pets: petList) {
            if(pets.getOwnerId() == 0){
                numbers.add(pets.getId());
                System.out.println(pets.getId()+"\t"+pets.getName()+"\t\t"+pets.getTypeName()+"\t140");
            }
        }
        System.out.println("-----请选择要购买哪一个宠物，并输入选择项的序号------");
        int num = input.nextInt();
        if(numbers.contains(num)){
            Pet pet = new Pet();
            pet.setId(num);
            pet.setOwnerId(petOwner.getId());
            for (Pet pets: petList) {
                if(pets.getId() == num){
                    pet.setStoreId(pets.getStoreId());
                    break;
                }
            }
            petOwnerService.buy(pet);
            petStoreService.sell(pet);
            if(accountService.addAccount(pet,1,140)){
                System.out.println("账单添加成功");
            }else{
                System.out.println("账单添加失败");
            }

        }else{
            System.out.println("没有该序号宠物");
        }
    }

    /**
     * 主人卖宠物的方法
     * @param petOwner  传入登录主人
     */
    void ownerSell(PetOwner petOwner){
        System.out.println("-----我的宠物列表------");
        System.out.println("序号\t宠物名称\t类型");
        for (Pet pet: petList) {
            if(pet.getOwnerId() == petOwner.getId()){
                System.out.println(pet.getId()+"\t"+pet.getName()+"\t"+pet.getTypeName());
            }
        }
        System.out.println("----输入要出售的宠物序号-----");
        int outPet = input.nextInt();
        for (Pet pets: petList) {
            if(pets.getId() == outPet){
                System.out.println("宠物名称叫："+pets.getName()+",宠物类别是："+pets.getTypeName());
                System.out.println("请确认是否卖出，y表示卖出，n表示不买卖");
                String choose = input.next();
                if(choose.equals("y")){
                    System.out.println("以下是现有宠物商店，请选择您要卖给买家序号");
                    showStoreName();
                    int storeId = input.nextInt();
                    Pet pet = new Pet();
                    pet.setId(outPet);
                    pet.setOwnerId(petOwner.getId());
                    pet.setStoreId(storeId);
                    petOwnerService.sell(pet);
                    petStoreService.buy(pet);
                    if(accountService.addAccount(pet,2,140)){
                        System.out.println("账单添加成功");
                    }else{
                        System.out.println("账单添加失败");
                    }
                    break;
                }else{
                    System.out.println("您选择不卖");
                }
            }
        }
        //System.out.println("没有当前序号宠物");
    }

    /**
     * 商店登录方法
     */
    void storeLogin(){
        System.out.println("请先登录，请您输入商店的名字：");
        String name = input.next();
        System.out.println("请您输入商店的密码：");
        String pwd = input.next();
        PetStore petStore = petStoreService.login(name,pwd);
        if (petStore!=null){
            System.out.println("-----恭喜您登录成功-----");
            System.out.println("-----你的基本信息：-----");
            System.out.println("商店名称："+petStore.getName());
            System.out.println("余额："+petStore.getBalance());
            System.out.println("您可以做如下操作：");
            System.out.println("1.查询本商店宠物");
            System.out.println("2.查询本商店宠物品种");
            System.out.println("3.按宠物类型查询宠物");
            System.out.println("4.查询账单");
            System.out.println("5.购买宠物");
            System.out.println("请选择：");
            int choose = input.nextInt();
            switch (choose){
                case 1:
                    showPetsByStoreId(petStore);
                    break;
                case 2:
                    showPetsBreedByStoreId(petStore);
                    break;
                case 3:
                    showBreedByType(petStore);
                    break;
                case 4:
                    showAccount(petStore);
                    break;
                case 5:
                    storeBuy(petStore);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 根据宠物商店Id查询宠物
     * @param petStore  传入宠物商店实体类
     */
    void showPetsByStoreId(PetStore petStore){
        petList = petStoreService.getPetsInStock(petStore.getId());
        System.out.println("序号\t宠物姓名");
        for (Pet pet: petList) {
            System.out.println(pet.getId()+"\t"+pet.getName());
        }
    }

    /**
     * 根据宠物商店id查询宠物品种
     * @param petStore  传入宠物商店实体类
     */
    void showPetsBreedByStoreId(PetStore petStore){
        petList = petStoreService.getPetsInStock(petStore.getId());
        System.out.println("拥有品种：");
        petList.forEach((x)-> System.out.println(x.getTypeName()));
    }

    /**
     * 根据宠物商店id查询账单
     * @param petStore  传入一个宠物商店实体类
     */
    void showAccount(PetStore petStore){
        accountList = petStoreService.getAccount(petStore.getId());
        System.out.println("账单编号\t账单类型\t宠物编号\t卖家编号\t买家编号\t交易金额\t交易时间");
        for (Account account : accountList) {
            System.out.println(account.getId()+"\t\t"+account.getDealType()+"\t\t"
            +account.getPetId()+"\t\t"+account.getSellerId()+"\t\t"
            +account.getBuyerId()+"\t\t"+account.getPrice()+"\t\t"
            +account.getDealTime());
        }
        System.out.println("账单类型：1为宠物商店卖给主人,2为主人卖给商店,3为商店购买商店");
    }

    /**
     * 宠物商店买宠物方法
     * @param petStore  传入宠物商店实体类
     */
    void storeBuy(PetStore petStore){
        System.out.println("----其它商店库存宠物----");
        System.out.println("序号\t宠物名称\t类型\t元宝数");
        List<Integer> numbers = new ArrayList<>();  //存储宠物id，后面使用List的contains方法判断是否有该宠物
        for (Pet pets: petList) {
            if(pets.getOwnerId() == 0 && pets.getStoreId() != petStore.getId()){    //判断主人为空，并且不是本商店宠物
                numbers.add(pets.getId());
                System.out.println(pets.getId()+"\t"+pets.getName()+"\t\t"+pets.getTypeName()+"\t140");
            }
        }
        System.out.println("-----请选择要购买哪一个宠物，并输入选择项的序号------");
        int num = input.nextInt();
        if(numbers.contains(num)){
            Pet pet1 = new Pet();    //买商店使用
            pet1.setId(num);
            pet1.setStoreId(petStore.getId());
            Pet pet2 = new Pet();   //卖商店使用
            for (Pet pets: petList) {   //用于查询卖家商店id
                if(pets.getId() == num){
                    pet2.setStoreId(pets.getStoreId());
                    break;
                }
            }
            petStoreService.buy(pet1);
            petStoreService.sell(pet2);
            Pet pet3 = new Pet();   //添加账单使用
            pet3.setId(num);
            pet3.setStoreId(pet2.getStoreId()); //得到卖家商店id
            pet3.setOwnerId(pet1.getStoreId()); //得到买家商店id
            if(accountService.addAccount(pet3,3,140)){
                System.out.println("账单添加成功");
            }else{
                System.out.println("账单添加失败");
            }
        }else{
            System.out.println("没有该序号宠物");
        }


    }

    /**
     * 根据宠物类型查询宠物
     * @param petStore 传入宠物商店实体类
     */
    void showBreedByType(PetStore petStore){
        System.out.println("输入您要查询的宠物类型");
        petList = petStoreService.breed(input.next());
        System.out.println("序号\t宠物姓名");
        for (Pet pet: petList) {
            if(pet.getStoreId() == petStore.getId()){
                System.out.println(pet.getId()+"\t"+pet.getName());
            }
        }
    }
}
