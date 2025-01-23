package service;

import model.UserModel;

import org.springframework.stereotype.Service;
import repo.productRepo.ProductModelRepoInterface;
import repo.purchaseRepo.UserPurchaseRepoImpl;
import repo.purchaseRepo.UserPurchaseRepoInterface;
import repo.userRepo.UserModelRepoInterface;

import java.util.List;


@Service
public class UserService {

    private  ProductModelRepoInterface productRepo;
    private  UserModelRepoInterface userRepo;
    private UserPurchaseRepoInterface purchaseRepo;
    private UserPurchaseRepoImpl userPurchaseRepoimpl;
    public UserService(ProductModelRepoInterface productRepo,
                       UserModelRepoInterface userRepo,
                       UserPurchaseRepoInterface purchaseRepo
    ){
        this.productRepo=productRepo;
        this.userRepo=userRepo;
        this.purchaseRepo=purchaseRepo;
    }


    public void updateUserDetail(UserModel user){
        userRepo.updateById(user);
    }

    public UserModel saveNewUser(UserModel user) {
        return (UserModel) userRepo.save(user);
    }

    public void deleteTheAccount(String email){
        userRepo.deleteByEmailId(email);
    }

    public List<UserModel> getAllEmployees(){
        return userRepo.getAllEmployees();
    }

    public UserModel findUserByEmailId(String name){
        return (UserModel) userRepo.findByEmailId(name);
    }


}
