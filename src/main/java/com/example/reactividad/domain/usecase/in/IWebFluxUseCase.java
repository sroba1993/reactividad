package com.example.reactividad.domain.usecase.in;

import reactor.core.publisher.Mono;

public interface IWebFluxUseCase {

    Mono<Integer> getCalculationSync();

    Mono<Integer> getCalculationAsync();
}
