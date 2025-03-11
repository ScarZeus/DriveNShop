package service;

import AppExceptions.EntityNotFoundException;
import model.CartItem;
import model.CartModel;
import model.ProductModel;
import model.PurchaseModel;
import org.springframework.stereotype.Service;
import repo.productRepo.ProductModelRepoInterface;
import repo.purchaseRepo.UserPurchaseRepoInterface;
import repo.userRepo.UserModelRepoInterface;

import java.util.ArrayList;
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

    public PurchaseModel getBillByid(long id){
        return (PurchaseModel) purchaseRepo.getBillById(id);
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

    public PurchaseModel saveTheBill(PurchaseModel bill){
        purchaseRepo.update(bill);
        return bill;
    }

    public PurchaseModel calculateBill(CartModel cartItems){
        List<CartItem> products=cartItems.getCartItems();
        List<ProductModel> purchasedItem = new ArrayList<>();
        long total=0;
        long totalDiscount=0;
        //All the Product has been added to the list
        //update the count of the product Model stock count by substracting the productmodel stock count
        for(CartItem item : products){
            ProductModel product = item.getProduct();
            product.setStock(product.getStock()-item.getCount());
             total += item.getCount()*(product.getPrice() - product.getPrice() * (product.getDiscount()/100));
             totalDiscount +=item.getCount()*(product.getPrice()*(product.getDiscount()/100));
            purchasedItem.add(product);
        }
        //create a product model name for PurchaseModel  for the bill to store bill information
        PurchaseModel bill=  new PurchaseModel();
        bill.setUsername(cartItems.getUser().getUser_name());
        bill.setTotalAmount(total);
        bill.setDiscountAmount(totalDiscount);
        bill.setProducts(purchasedItem);
        //update the products in the Model
        return  (PurchaseModel)purchaseRepo.saveBill(bill);

    }


    public PurchaseModel updateBill(PurchaseModel billEntity) throws EntityNotFoundException {
       PurchaseModel model =(PurchaseModel) purchaseRepo.findUserByPurchaseId(billEntity.getId());
       if(model!=null){
           purchaseRepo.delete(billEntity);
           purchaseRepo.saveBill(billEntity);
           return (PurchaseModel) purchaseRepo.findUserByPurchaseId(billEntity.getId());
       }
       else{
           throw new EntityNotFoundException("Bill not Found");
       }
    }
}
