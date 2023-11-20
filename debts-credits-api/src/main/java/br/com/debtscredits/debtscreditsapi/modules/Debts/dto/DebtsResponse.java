package br.com.debtscredits.debtscreditsapi.modules.Debts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.debtscredits.debtscreditsapi.modules.Categoria.dto.CategoryResponse;
import br.com.debtscredits.debtscreditsapi.modules.Debts.model.DebtsModel;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebtsResponse {
    private Integer id;
    private String nome;
    private Double valor;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_vencimento;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_entrada;
    private boolean parcelas;
    private Integer quantidade_parcelas;
    private String status;
    private boolean planejado;
    @JsonProperty("category")
    private CategoryResponse category;

    
    public static DebtsResponse of(DebtsModel debts) {
        return DebtsResponse
        .builder()
        .id(debts.getId())
        .nome(debts.getNome())
        .valor(debts.getValor())
        .data_vencimento(debts.getData_vencimento())
        .category(CategoryResponse.of(debts.getCategory()))
        .status(debts.getStatus())
        .planejado(debts.isPlanejado())
        .parcelas(debts.isParcelas())
        .data_entrada(debts.getData_entrada())
        .build();     
    }
}
