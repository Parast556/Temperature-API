package com.fahrenheittocelsius.temperature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperature")
public class TemperatureConversionController {
    @Autowired
    private TemperatureConversionService temperatureConversionService;
 
    @GetMapping("/celsius-to-fahrenheit/{celsius}")
    public double convertCelsiusToFahrenheit(@PathVariable("celsius") double celsius) {
        return temperatureConversionService.convertCelsiusToFahrenheit(celsius);
    }
}
