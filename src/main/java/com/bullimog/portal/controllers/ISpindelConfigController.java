package com.bullimog.portal.controllers;

import com.bullimog.portal.connectors.FileConnector;
import com.bullimog.portal.models.Calibration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.HashMap;

@Controller
public class ISpindelConfigController implements WebMvcConfigurer {

    @Autowired @Qualifier("calibration")
    FileConnector cfc;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/result").setViewName("result");
    }

    @GetMapping(value = "/ispindel-config")
    public ModelAndView configGet() {
        HashMap<String, Object> params = new HashMap<>();
        Calibration calibration = cfc.readContents(Calibration.class).
                orElse(new Calibration(0.0,0.0,0.0,0.0, 0.0));
        params.put("calibration", calibration);
        return new ModelAndView("iSpindelConfigForm", params);
    }

    @PostMapping(value = "/ispindel-config")
    public String checkCalibrationInfo(@Valid Calibration calibration, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "iSpindelConfigForm";
        }

        cfc.writeContents(calibration);
        return "redirect:/result";
    }
}
