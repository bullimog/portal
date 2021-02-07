package com.bullimog.portal.controllers;

import com.bullimog.portal.models.Calibration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.bullimog.portal.connectors.CalibrationFileConnector;

import javax.validation.Valid;
import java.util.HashMap;

@Controller
public class ISpindelConfigController implements WebMvcConfigurer {

    @Autowired
    CalibrationFileConnector cfc;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/result").setViewName("result");
    }

    @GetMapping(value = "/config")
    public ModelAndView configGet() {
        HashMap<String, Object> params = new HashMap<>();
        Calibration calibration = cfc.readCalibration();
        params.put("calibration", calibration);
        return new ModelAndView("iSpindelConfigForm", params);
    }

    @PostMapping(value = "/config")
    public String checkCalibrationInfo(@Valid Calibration calibration, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "iSpindelConfigForm";
        }

        cfc.writeCalibration(calibration);
        return "redirect:/result";
    }
}
