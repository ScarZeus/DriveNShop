package service;

import AppExceptions.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import repo.productRepo.ProductModelRepoInterface;
import repo.purchaseRepo.UserPurchaseRepoInterface;
import repo.userRepo.UserModelRepoInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductModelRepoInterface productRepo;
    private UserModelRepoInterface userRepo;
    private UserPurchaseRepoInterface purchaseRepo;



    public ProductService(ProductModelRepoInterface productRepo, UserModelRepoInterface userRepo, UserPurchaseRepoInterface purchaseRepo){
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.purchaseRepo = purchaseRepo;

    }

    public ProductModel saveTheProduct(ProductModel product, MultipartFile imageData) {

        product.setImageType(imageData.getContentType());
        try {
            product.setImageData(imageData.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        product.setImageName(imageData.getContentType());
        return (ProductModel)  productRepo.save(product);
    }

    public void updateProducts(ProductModel updateModel){
        productRepo.updateProduct(updateModel);
    }

    public void deleteByProduct(ProductModel removalItem) throws EntityNotFoundException{
        ProductModel product=findByProductID(removalItem.getId());
        if(product!=null){
            productRepo.deleteById(product);
        }
        else{
            throw new EntityNotFoundException("Product Not Found");
        }
    }

    public ProductModel findByProductID(Long id){
        return (ProductModel) productRepo.findById(id);
    }


    public List<ProductModel> getProductsBycategory(String category) {
        return productRepo.getAllByCategory(category);
    }

    public List<ProductModel> getSearchedProducts(String nameOfTheProduct) throws EntityNotFoundException{
        List<ProductModel> foundProducts= productRepo.findAllByName(nameOfTheProduct);
        if(foundProducts!=null){
            return foundProducts.stream()
                    .filter(product -> product.getStock() > 0) // Only keep products with stock > 0
                    .collect(Collectors.toList());
        }
       else {
           throw new EntityNotFoundException("No Matching Requests");
        }
    }

    public List<ProductModel> filterProducts(String productName,String brand , Long price){
        List<ProductModel> list = productRepo.findAllByName(productName);
        List<ProductModel> filteredList = list.stream().filter(product -> product.getPrice() <= price) // Check price
                .filter(product -> brand != null && brand.equalsIgnoreCase(product.getBrand())) // Check brand safely
                .collect(Collectors.toList());
        return filteredList;
    }



}
