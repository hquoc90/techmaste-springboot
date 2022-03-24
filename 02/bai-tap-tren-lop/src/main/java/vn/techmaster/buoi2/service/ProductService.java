package vn.techmaster.buoi2.service;

import org.springframework.stereotype.Service;
import vn.techmaster.buoi2.model.Product;
import vn.techmaster.buoi2.model.request.ProductReq;

import java.awt.print.Book;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductService {
    private ConcurrentHashMap<String, Product> productList;

    public ProductService(){
        productList =new ConcurrentHashMap<>();
        Product p=new Product("00-11","hoang", "QUoc", 13);
        Product p1=new Product("00-12","choang", "QUoc", 12);
        Product p2=new Product("00-13","ahoang", "QUoc", 11);
        productList.put("00-11",p);
        productList.put("00-12",p1);
        productList.put("00-13",p2);
    }
    public List<Product> showListProduct(){
        List<Product> rs=new ArrayList<Product>(productList.values());

        return rs;
    }

    //showlist  test
    public ConcurrentHashMap<String, Product> showListProduct2(){
        return productList;

    }

    public Product addProduct(ProductReq productReq){

        String uuid = UUID.randomUUID().toString();
        Product p=new Product();
        p.setId(uuid);
        p.setName(productReq.getName());
        p.setManufacturer(productReq.getManufacturer());
        p.setPrice(productReq.getPrice());
        productList.put(uuid,p);
        System.out.println("Them thanh cong");
        return  p;
    }
    public boolean isRemoveProduct(String id){
        //check id
        if (productList.get(id)!=null) {
            productList.remove(id);
            return true;
        }
        return false;
    }

    public boolean updateProd(ProductReq productReq, String id){

        List<Product> list=productList.values().stream().toList();
        for (Product  product :
            list) {
            if (product.getId().equals(id)) {
                product.setName(productReq.getName());
                product.setManufacturer(product.getManufacturer());
                product.setPrice(productReq.getPrice());
                return true;
            }
        }
        return false;
    }
    public List<Product> sortByName(){

         List<Product> rs=new ArrayList<Product>(productList.values());

        Collections.sort(rs, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return rs;
    } public List<Product> sortByPrice(){

        List<Product> rs=new ArrayList<Product>(productList.values());

        Collections.sort(rs, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getPrice()-o2.getPrice();
            }
        });
        return rs;
    }
}
