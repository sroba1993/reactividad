package com.example.reactividad.domain.usecase;

import com.example.reactividad.domain.usecase.in.IWebFluxUseCase;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;

@Service
public class WebFluxUseCase implements IWebFluxUseCase {


 public void defineReactor() {

     //retorna un dato

     Mono<String> mono = Mono.just("Alex");
     Mono<String> monoEmpty = Mono.empty();

     //Flux: Returns 0…N elements. A Flux can be endless, meaning that it can keep emitting elements forever. Also it can return a sequence of elements and then send a completion notification when it has returned all of its elements.

     Flux<String> flux = Flux.just("A", "B", "C");
     Flux<String> array = Flux.fromArray(new String[]{"A", "B", "C"});
     Flux<String> list = Flux.fromIterable(Arrays.asList("A", "B", "C"));
 }

 @Override
 public Mono<Integer> getCalculationSync() {
     return  Mono.just(5)
             .flatMap(a -> doSum(a, 3))
             .flatMap(result -> doSubtract(result, 2))
             .flatMap(result -> doMultiply(result, 4));
 }

 @Override
 public Mono<Integer> getCalculationAsync() {
     Mono<Integer> sum = doSum(5, 3);
     Mono<Integer> subtract = doSubtract(5, 2);
     Mono<Integer> multiply = doMultiply(8, 4);

     return Mono.zip(sum, subtract, multiply)
             .map(tuple -> tuple.getT1() + tuple.getT2() + tuple.getT3());
 }

 private Mono<Integer> doSum(Integer a, Integer b) {
     return Mono.just(a + b)
             .delayElement(Duration.ofSeconds(3));
 }

 private Mono<Integer> doSubtract(Integer a, Integer b) {
     return Mono.just(a - b)
             .delayElement(Duration.ofSeconds(3));
 }

 private Mono<Integer> doMultiply(Integer a, Integer b) {
     return Mono.just(a * b)
             .delayElement(Duration.ofSeconds(3));
 }

}
