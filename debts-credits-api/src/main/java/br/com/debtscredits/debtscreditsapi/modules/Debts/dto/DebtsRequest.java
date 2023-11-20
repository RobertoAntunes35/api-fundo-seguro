package br.com.debtscredits.debtscreditsapi.modules.Debts.dto;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DebtsRequest {
    private String nome;
    private Double valor;
    @JsonProperty("dateVencimento")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_vencimento;
    @JsonProperty("dateEntrada")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_entrada;
    private boolean parcelas;
    private Integer quantidade_parcelas;
    private String status;
    private boolean planejado;
    @JsonProperty("category")
    private Integer categoryId;
    private String tipo_pagamento;

}


