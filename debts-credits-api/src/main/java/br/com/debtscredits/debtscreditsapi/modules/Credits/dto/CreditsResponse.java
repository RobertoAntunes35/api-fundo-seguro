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
    private LocalDateTime date;
    @JsonProperty("date_import")
    @JsonFormat(pattern = "dd/MM/yyy HH:mm:ss")
    private String metodo_pagamento;
    private CategoryResponse category;
    private Double valor;

    public static CreditsResponse of(CreditsModel credits) {
        return CreditsResponse.builder()
                    .tipo_credito(credits.getTipo_credito())
                    .date(credits.getDate_import())
                    .category(CategoryResponse.of(credits.getCategory()))
                    .id(credits.getId())
                    .descricao(credits.getDescricao())
                    .valor(credits.getValor())
                    .build();
    }
}
