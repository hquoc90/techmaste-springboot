package vn.techmaster.buoi2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.buoi2.model.Product;
import vn.techmaster.buoi2.model.request.ProductReq;
import vn.techmaster.buoi2.service.ProductService;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    private ConcurrentHashMap<String, Product> productList;

    @GetMapping(value="/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Product> showList(){

        return productService.showListProduct();
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ConcurrentHashMap<String, Product> showList2(){

        return productService.showListProduct2();
    }
    @GetMapping( value="/sortbyname", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Product> sortByName(){
        return productService.sortByName();
    }
    @GetMapping( value="/sortbyprice", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Product> sortByPrice(){
        return productService.sortByPrice();
    }

    @PostMapping
    public  Product createProd(@RequestBody ProductReq productReq){
            return productService.addProduct(productReq);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductReq productReq, @PathVariable String id){
        if (productService.updateProd(productReq, id)) {
            return ResponseEntity.ok("success");

        } return   ResponseEntity.ok(" fail");

    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> removeProduct(@PathVariable("id") String id){
        if (productService.isRemoveProduct(id)) {
            return ResponseEntity.ok("Delete ok");
        }
        return   ResponseEntity.ok("Delete fail");
    }


}
