package controller;

import model.PurchaseModel;
import model.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProductService;
import service.PurchaseService;
import service.UserService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
   private ProductService productService;
   private PurchaseService purchaseService;

    public AdminController(UserService userService, ProductService productService, PurchaseService purchaseService) {
        this.userService = userService;
        this.productService = productService;
        this.purchaseService = purchaseService;
    }

    @PostMapping("/addNewEmployee")
    public ResponseEntity<?> addEmployee(@RequestBody UserModel user){
       userService.updateUserDetail(user);
       return ResponseEntity.ok("Employee Added Successfully");
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<UserModel>> getAllEmployees(){
        return ResponseEntity.ok(userService.getAllEmployees()
        );
    }

    @GetMapping("/getAllBillsByDate/{date}")
    public ResponseEntity<List<PurchaseModel>> getBillsByDate(@PathVariable("date") Date date){
        return ResponseEntity.ok(purchaseService.getAllPurchasesByDate(date));
    }

    @GetMapping("/getBillById/{id}")
    public ResponseEntity<PurchaseModel> getBillId(@PathVariable("id") Long id){
        return ResponseEntity.ok(purchaseService.getByBillId(id));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteBillId(@PathVariable("id") Long id){
        purchaseService.deleteBill(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
    @DeleteMapping("/deleteCustom")
    public ResponseEntity<?> deleteCustom(@RequestParam Date from,@RequestParam Date to){
        purchaseService.deleteBillsCustom(from,to);
        return ResponseEntity.ok("Deleted Successfully");
    }
    @DeleteMapping("/deleteAfter/{date}")
    public ResponseEntity<?> deleteAfter(@PathVariable("date") Date date){
        purchaseService.deleteBillsAfter(date);
        return ResponseEntity.ok("Deleted Successfully");
    }
    @DeleteMapping("/deleteBefore/{date}")
    public ResponseEntity<?> deleteBefore(@PathVariable("date") Date date){
        purchaseService.deleteBillsBefore(date);
        return ResponseEntity.ok("Deleted Successfully");
    }


}
