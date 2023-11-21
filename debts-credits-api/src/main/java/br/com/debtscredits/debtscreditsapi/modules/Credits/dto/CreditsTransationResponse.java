package br.com.debtscredits.debtscreditsapi.modules.Credits.dto;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.debtscredits.debtscreditsapi.modules.Categoria.dto.CategoryResponse;
import br.com.debtscredits.debtscreditsapi.modules.Credits.model.CreditsModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CreditsTransationResponse {
    private Integer id; 
    private String descricao;
    private String tipo_credito;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime date_import;
    @JsonProperty("category")
    private CategoryResponse category;
    private String metodo_pagamento;
    private Double valor; 
    // private List<String> transation;

    public static CreditsTransationResponse of(CreditsModel credits) {
        return CreditsTransationResponse
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
