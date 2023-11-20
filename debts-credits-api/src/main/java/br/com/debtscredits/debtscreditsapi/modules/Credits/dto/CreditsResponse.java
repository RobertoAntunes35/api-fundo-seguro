package br.com.debtscredits.debtscreditsapi.modules.Credits.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.debtscredits.debtscreditsapi.modules.Categoria.dto.CategoryResponse;
import br.com.debtscredits.debtscreditsapi.modules.Credits.model.CreditsModel;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditsResponse {
    private Integer id; 
    private String descricao;
    private String tipo_credito;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime date_import;
    @JsonProperty("category")
    private CategoryResponse category;
    private String metodo_pagamento;
    private Double valor; 

    public static CreditsResponse of(CreditsModel credits) {
        return CreditsResponse
        .builder()
        .id(credits.getId())
        .descricao(credits.getDescricao())
        .tipo_credito(credits.getTipo_credito())
        .date_import(credits.getDate_import())
        .metodo_pagamento(credits.getMetodo_pagamento())
        .valor(credits.getValor())
        .category(CategoryResponse.of(credits.getCategory()))
        .build();        
    }
}
