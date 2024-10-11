package com.bannguoiminhyeu.bannguoiyeu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bannguoiminhyeu.bannguoiyeu.models.Product;
import com.bannguoiminhyeu.bannguoiyeu.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping({ "", "/" })
    public String showProductList(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products/index";
    }

    @GetMapping("/{id}")
    public String showProductDetails(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "error/404";
        }
        model.addAttribute("product", product);
        return "products/product-details";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        System.out.println("Search query: " + query);
        List<Product> products = productService.searchProducts(query);
        model.addAttribute("products", products);
        model.addAttribute("query", query);
        return "products/search"; 
    }
}
