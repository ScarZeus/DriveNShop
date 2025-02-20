package controller;


import model.ProductModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.ProductService;
import service.PurchaseService;
import service.UserService;


@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private UserService userService;
    private ProductService productService;
    private PurchaseService purchaseService;

    public EmployeeController(UserService userService, ProductService productService, PurchaseService purchaseService) {
        this.userService = userService;
        this.productService = productService;
        this.purchaseService = purchaseService;
    }

    @PostMapping(value = "/addNewProduct")
    public ResponseEntity<ProductModel> addProduct(@RequestPart("data") ProductModel product
            , @RequestPart("file") MultipartFile imageData){
        return ResponseEntity.ok(productService.saveTheProduct(product,imageData));
    }


}
