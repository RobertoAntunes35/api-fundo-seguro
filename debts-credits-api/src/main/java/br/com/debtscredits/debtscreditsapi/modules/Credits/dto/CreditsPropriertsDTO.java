package br.com.debtscredits.debtscreditsapi.modules.Credits.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditsPropriertsDTO {
    private Integer creditsId;
    private String descricao; 
    private String metodo_pagamento;
    private String fk_category;
    private double valor;

}
