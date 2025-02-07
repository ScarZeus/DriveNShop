package service;

import jakarta.servlet.http.HttpServletRequest;
import model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import repo.productRepo.ProductModelRepoInterface;
import repo.purchaseRepo.UserPurchaseRepoInterface;
import repo.userRepo.UserModelRepoInterface;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductModelRepoInterface productRepo;
    private UserModelRepoInterface userRepo;
    private UserPurchaseRepoInterface purchaseRepo;
    @Autowired
    private HttpServletRequest request;


    public ProductService(ProductModelRepoInterface productRepo, UserModelRepoInterface userRepo, UserPurchaseRepoInterface purchaseRepo){
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.purchaseRepo = purchaseRepo;

    }

    public ProductModel saveTheProduct(ProductModel product, MultipartFile[] imageData){
        product.setProductImageName(
                Arrays.stream(imageData)
                        .map(MultipartFile::getOriginalFilename)
                        .collect(Collectors.toList())
        );

        product.setProductImageType(
                Arrays.stream(imageData)
                        .map(MultipartFile::getContentType)
                        .collect(Collectors.toList())
        );

        product.setProductImage(
                Arrays.stream(imageData)
                        .map(image-> {
                            try {
                                return image.getBytes();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .collect(Collectors.toList())
        );
        System.out.println(product);

        return (ProductModel) productRepo.save(product);
    }

    public void updateProducts(ProductModel updateModel){
        productRepo.updateProduct(updateModel);
    }

    public void deleteByProductId(Long id){
        productRepo.deleteById(id);
    }

    public ProductModel findByProductID(Long id){
        return (ProductModel) productRepo.findById(id);
    }


    public List<ProductModel> getProductsBycategory(String category) {
        return productRepo.getAllByCategory(category);
    }

    public List<ProductModel> getSearchedProducts(String nameOfTheProduct){
        List<ProductModel> foundProducts= productRepo.findAllByName(nameOfTheProduct);
        return foundProducts.stream()
                .filter(product -> product.getStock() > 0) // Only keep products with stock > 0
                .collect(Collectors.toList());
    }

    public List<ProductModel> filterProducts(String productName,String brand , Long price){
        List<ProductModel> list = productRepo.findAllByName(productName);
        List<ProductModel> filteredList = list.stream().filter(product -> product.getPrice() <= price) // Check price
                .filter(product -> brand != null && brand.equalsIgnoreCase(product.getBrand())) // Check brand safely
                .collect(Collectors.toList());
        return filteredList;
    }






}
