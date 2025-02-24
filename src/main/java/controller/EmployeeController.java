package controller;


import model.ProductModel;
import model.PurchaseModel;
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

    @GetMapping("/getBill")
    public ResponseEntity<PurchaseModel> getBill(@RequestParam("billID") Long id){
        PurchaseModel billEntity = purchaseService.getByBillId(id);
        return ResponseEntity.ok(billEntity);
    }

    @PostMapping("/editBill")
    public ResponseEntity<PurchaseModel> billPayment(@RequestBody PurchaseModel billEntity){
        try{
            return ResponseEntity.ok(purchaseService.updateBill(billEntity));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(billEntity);
        }
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<?> deleteProduct(@RequestBody ProductModel product){
        productService.deleteByProductId(product.getId());
        return ResponseEntity.ok("Deleted Succcessfully");
    }


}
