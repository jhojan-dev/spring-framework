package com.jhojan.curso.springboot.webapp.controllers;

import com.jhojan.curso.springboot.webapp.models.dto.ParamDTO;
import com.jhojan.curso.springboot.webapp.models.dto.ParamMixDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/params")
public class RequestParamController {

    @GetMapping("/foo")
    public ParamDTO foo(
            @RequestParam(required = false, defaultValue = "A message", name = "message")
            String message
    ) {ParamDTO dto = new ParamDTO();
        dto
        .setMessage(message);
        return dto;
    }

    @GetMapping("/bar")
    public ParamMixDTO bar(@RequestParam String message, @RequestParam Integer code) {
        return new ParamMixDTO(message, code);
    }

}
