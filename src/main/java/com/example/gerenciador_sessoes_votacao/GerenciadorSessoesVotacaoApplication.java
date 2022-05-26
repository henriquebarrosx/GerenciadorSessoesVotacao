package com.example.gerenciador_sessoes_votacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.example.gerenciador_sessoes_votacao.v1.repositories")
@SpringBootApplication
public class GerenciadorSessoesVotacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorSessoesVotacaoApplication.class, args);
	}

}
