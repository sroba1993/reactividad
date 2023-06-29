package com.example.reactividad.infraestructure;

import com.example.reactividad.domain.usecase.in.IWebFluxUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/calculation", produces = MediaType.APPLICATION_JSON_VALUE)
public class WebFluxController {

    @Autowired
    IWebFluxUseCase useCase;

    @GetMapping("/sync")
    public Mono<Integer> getCalculationSync(){
        return useCase.getCalculationSync();
    }

    @GetMapping("/async")
    public Mono<Integer> getCalculationAsync(){
        return useCase.getCalculationAsync();
    }

}
