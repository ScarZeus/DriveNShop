package service;

import jakarta.servlet.http.HttpServletRequest;
import model.ImageModel;
import model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import repo.imageRepo.ImageModelRepoImpl;
import repo.productRepo.ProductModelRepoInterface;
import repo.purchaseRepo.UserPurchaseRepoInterface;
import repo.userRepo.UserModelRepoInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductModelRepoInterface productRepo;
    private UserModelRepoInterface userRepo;
    private UserPurchaseRepoInterface purchaseRepo;
    private ImageModelRepoImpl imageRepo;

    @Autowired
    private HttpServletRequest request;


    public ProductService(ProductModelRepoInterface productRepo, UserModelRepoInterface userRepo, UserPurchaseRepoInterface purchaseRepo, ImageModelRepoImpl imageRepo) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.purchaseRepo = purchaseRepo;
        this.imageRepo = imageRepo;
    }

    public ProductModel saveTheProduct(ProductModel product){
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

    public List<ImageModel> saveImage(List<ImageModel> imageList,Long productId){
        String url =ServletUriComponentsBuilder.fromRequest(request).toUriString();
        ProductModel product = (ProductModel) productRepo.findById(productId);
        if (product == null) {
            throw new RuntimeException("Product not found with ID: " + productId);
        }
        imageList.forEach(item-> item.setProductRef(product));
        List<String> urls = product.getImages() == null ? new ArrayList<>() : product.getImages();
        List<ImageModel> saved = imageRepo.saveImages(imageList);
        for(ImageModel index: saved ){
            urls.add(url+"/image/"+product.getId()+"/"+index.getId());
        }
        product.setImages(urls);
        productRepo.updateProduct(product);
        return imageList;
    }

    public void deleteImage(Long imageId,Long prodId){
        ProductModel product=(ProductModel) productRepo.findById(prodId);
        product.setImages(null);
        productRepo.updateProduct(product);
        imageRepo.deleteImages(imageId);
    }



}
