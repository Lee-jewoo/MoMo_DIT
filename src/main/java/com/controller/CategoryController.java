package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
	@GetMapping("/category")
    public String showCategoryPage() {
        return "category"; // 초기 카테고리 선택 화면
    }
}
