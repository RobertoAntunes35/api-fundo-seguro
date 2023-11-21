package br.com.debtscredits.debtscreditsapi;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;



@EnableFeignClients()
@EnableRabbit
@SpringBootApplication
public class DebtsCreditsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DebtsCreditsApiApplication.class, args);
		System.out.println("APLICACAO EM ANDAMENTO");

	}
}
