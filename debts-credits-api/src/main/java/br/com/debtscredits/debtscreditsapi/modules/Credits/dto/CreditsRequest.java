package br.com.debtscredits.debtscreditsapi.modules.Credits.dto;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CreditsRequest {
    private Integer id; 
    private String descricao;
    private String tipo_credito;
    private LocalDateTime date;
    private String metodo_pagamento;
    private Double valor;
    private Integer categoryId;
}
