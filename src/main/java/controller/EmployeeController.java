package controller;


import model.ImageModel;
import model.ProductModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.ProductService;
import service.PurchaseService;
import service.UserService;

import java.util.List;

@RestController
public class EmployeeController {

    private UserService userService;
    private ProductService productService;
    private PurchaseService purchaseService;

    public EmployeeController(UserService userService, ProductService productService, PurchaseService purchaseService) {
        this.userService = userService;
        this.productService = productService;
        this.purchaseService = purchaseService;
    }

    @GetMapping("/addProducts")
    public ResponseEntity<ProductModel> saveProducts(@RequestBody ProductModel product, @RequestBody List<ImageModel> images){

        return ResponseEntity.ok(product);
    }

}
