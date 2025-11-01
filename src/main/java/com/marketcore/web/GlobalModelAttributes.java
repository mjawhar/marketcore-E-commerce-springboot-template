package com.marketcore.web;

import com.marketcore.model.Category;
import com.marketcore.repository.CategoryRepository;
import com.marketcore.repository.SiteSettingRepository;
import com.marketcore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAttributes {

    private final CategoryRepository categoryRepository;
    private final SiteSettingRepository settingRepository;
    private final ProductService productService;

    @ModelAttribute("topCategories")
    public List<Category> topCategories(){
        return categoryRepository.findByParentIsNull();
    }

    @ModelAttribute("childrenByParent")
    public Map<Long, List<Category>> childrenByParent(){
        return categoryRepository.findAll().stream()
                .filter(c -> c.getParent()!=null)
                ...
    }



    @ModelAttribute("currencySymbol")
    public String currencySymbol(){
        SiteSetting s = settingRepository.findById(1L).orElse(null);
        return s != null ? s.getCurrencySymbol() : "EUR";
        ....
}
