package com.bullimog.portal.controllers;

import com.bullimog.portal.connectors.FileConnector;
import com.bullimog.portal.models.FermentConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.HashMap;

@Controller
public class FermenterConfigController implements WebMvcConfigurer {
    @Autowired @Qualifier("ferment-config")
    FileConnector fermentConfigFileConnector;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/result").setViewName("result");
    }

    @GetMapping(value = "/fermenter-config")
    public ModelAndView configGet() {
        HashMap<String, Object> params = new HashMap<>();
        FermentConfig fermentConfig = fermentConfigFileConnector.readContents(FermentConfig.class).
                orElse(new FermentConfig("none", 0.0, 100.00, 60000, 0.0, 0.0, 0.0 ));
        params.put("fermentConfig", fermentConfig);
        return new ModelAndView("fermenter-config-form", params);
    }

    @PostMapping(value = "/fermenter-config")
    public String checkFermenterConfig(@Valid FermentConfig fermentConfig, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "fermenter-config-form";
        }

        fermentConfigFileConnector.writeContents(fermentConfig);
        return "redirect:/result";
    }
}
