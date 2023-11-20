package br.com.debtscredits.debtscreditsapi.modules.Credits.dto;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CreditsRequest {
    private String descricao;
    @JsonProperty("date_import")
    private LocalDateTime date_import;
    private String tipo_credito;
    private String metodo_pagamento;
    private Double valor;
    private Integer categoryId;

    
}
