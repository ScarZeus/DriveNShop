package controller;

import model.ProductModel;
import model.PurchaseModel;
import model.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProductService;
import service.PurchaseService;
import service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;
    private ProductService productService;
    private PurchaseService purchaseService;

    public UserController(UserService userService, ProductService productService, PurchaseService purchaseService) {
        this.userService = userService;
        this.productService = productService;
        this.purchaseService = purchaseService;
    }
    @PostMapping("/save")
    public ResponseEntity<UserModel> saveNewUser(@RequestBody UserModel user){
        return ResponseEntity.ok(userService.saveNewUser(user));
    }
    @GetMapping("/getProducts/{category}")
    public ResponseEntity<List<ProductModel>> categoryProducts(@PathVariable("category") String category){
        List<ProductModel> products=productService.getProductsBycategory(category);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/saveBill")
    public ResponseEntity<PurchaseModel> saveBill(@RequestBody PurchaseModel purchaseModel){
        return ResponseEntity.ok(purchaseService.saveTheBill(purchaseModel));
    }

    @GetMapping("/calculateBill")
    public ResponseEntity<PurchaseModel> payBill(PurchaseModel model){
        PurchaseModel bill = purchaseService.calculateTheBill(model);
        return ResponseEntity.ok(bill);
    }

    @GetMapping("/getProductByName")
    public ResponseEntity<List<ProductModel>> getProductByName(@RequestParam("productName") String product){
        try {
            List<ProductModel> matchProduct=productService.getSearchedProducts(product);
            return ResponseEntity.ok(matchProduct);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }

    }


}
