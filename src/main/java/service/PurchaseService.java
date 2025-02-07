package service;

import model.ProductModel;
import model.PurchaseModel;
import org.springframework.stereotype.Service;
import repo.productRepo.ProductModelRepoInterface;
import repo.purchaseRepo.UserPurchaseRepoInterface;
import repo.userRepo.UserModelRepoInterface;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseService {
    private ProductModelRepoInterface productRepo;
    private UserModelRepoInterface userRepo;
    private UserPurchaseRepoInterface purchaseRepo;

    public PurchaseService(ProductModelRepoInterface productRepo, UserModelRepoInterface userRepo, UserPurchaseRepoInterface purchaseRepo) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.purchaseRepo = purchaseRepo;

    }
    public PurchaseModel getByBillId(Long id){
        return (PurchaseModel) purchaseRepo.findUserByPurchaseId(id);
    }

    public List<PurchaseModel> getAllPurchasesByDate(Date date){
        return  purchaseRepo.findAllUserByPurchaseDate(date);
    }

    public void deleteBillsBefore(Date date){
        purchaseRepo.deleteBeforeDate(date);
    }

    public void deleteBillsAfter(Date date){
        purchaseRepo.deleteAfterDate(date);
    }

    public void deleteBillsCustom(Date from,Date to){
        purchaseRepo.deleteCustom(from,to);
    }
    public void deleteBill(Long id){
        purchaseRepo.delete(id);
    }

    public PurchaseModel calculateTheBill(PurchaseModel bill){
        List<ProductModel> productsPuchased = bill.getProducts();
        long total = productsPuchased.stream()
                .mapToLong(product ->
                        (long) (product.getPrice() - (product.getPrice() * product.getCount() * (product.getDiscount() / 100.0))))
                .sum();

        bill.setTotalAmount(total);
        return bill;
    }

    public PurchaseModel saveTheBill(PurchaseModel purchaseModel){
        List<ProductModel> billProducts=purchaseModel.getProducts();
        List<ProductModel> updatedproduct = billProducts.stream()
                .peek(product ->product.setStock(product.getStock()- product.getCount()))
                .collect(Collectors.toList());
        productRepo.updateAll(updatedproduct);
        return  (PurchaseModel)purchaseRepo.saveBill(purchaseModel);

    }

}
