package pro.sky.cw3.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class InfoController {
    @GetMapping
    public String hello() {
        return "Application is started";
    }

    @GetMapping("/info")
    public String info() {
        return "Inna Serebriakova, course work #3 CW3 \"Socks\", 29/03/2023, application in started";
    }
}
