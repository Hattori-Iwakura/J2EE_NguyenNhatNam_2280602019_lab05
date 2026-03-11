package com.example.bt4.controller;

import com.example.bt4.service.CategoryService;
import com.example.bt4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("totalProducts", productService.getAll().size());
        model.addAttribute("totalCategories", categoryService.getAll().size());
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("featuredProducts", productService.getAll().subList(0, Math.min(6, productService.getAll().size())));
        return "home";
    }
}
