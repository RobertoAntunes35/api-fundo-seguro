package br.com.debtscredits.debtscreditsapi.modules.Debts.model;

import java.time.LocalDateTime;

import br.com.debtscredits.debtscreditsapi.modules.Categoria.model.Category;
import br.com.debtscredits.debtscreditsapi.modules.Debts.dto.DebtsRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data 
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DEBTS")
public class DebtsModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;
    
    @Column(name = "VALOR", nullable = false)
    private Double valor;

    @Column(name = "DATA_VENCIMENTO", nullable = false)
    private LocalDateTime data_vencimento;

    @Column(name = "DATA_ENTRADA", nullable = false)
    private LocalDateTime data_entrada;
    @PrePersist
    public void PrePersistDataEntrada() {
        data_entrada = LocalDateTime.now();
    }

    @Column(name = "PARCELAS", nullable = false)
    private boolean parcelas;

    @Column(name = "QUANTIDADE_PARCELAS", nullable = false)
    private Integer quantidade_parcelas;

    @Column(name = "STATUS", nullable = false)
    private String status;

    public void PrePersistStatus() {
        status = "A VENCER";
    }

    @Column(name = "TIPO_PAGAMENTO", nullable = false)
    private String tipo_pagamento;

    @Column(name = "PLANEJADO", nullable = false)
    private boolean planejado;

    @ManyToOne
    @JoinColumn(name = "FK_CATEGORY", nullable = false)
    private Category category;

    public static DebtsModel of(DebtsRequest request, Category category) {
        return DebtsModel 
            .builder()
            .nome(request.getNome())
            .valor(request.getValor())
            .data_vencimento(request.getData_vencimento())
            .data_entrada(request.getData_entrada())
            .parcelas(request.isParcelas())
            .quantidade_parcelas(request.getQuantidade_parcelas())
            .status(request.getStatus())
            .tipo_pagamento(request.getTipo_pagamento())
            .planejado(request.isPlanejado())
            .category(category)
            .build();
            // .builder()
            // .category(category)
            // .data_entrada(request.ge)
    }

}
