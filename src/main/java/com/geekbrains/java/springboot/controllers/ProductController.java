package com.geekbrains.java.springboot.controllers;

import com.geekbrains.java.springboot.models.Product;
import com.geekbrains.java.springboot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public String getAllProducts(Model model, @RequestParam(required = false) Integer maxPrice) {
        model.addAttribute("products", productService.getProductWithMaxPriceFiltering(maxPrice));
        return "products-all";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productService.delete(id);
        return "redirect:/products/all";
    }

    @GetMapping("/find")
    @ResponseBody
    public Product findProduct(@RequestParam long id) {
        return productService.find(id);
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", productService.find(id));
        return "products-edit";
    }
}
