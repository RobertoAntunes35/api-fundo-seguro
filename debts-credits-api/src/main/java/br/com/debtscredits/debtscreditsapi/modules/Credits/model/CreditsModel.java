package br.com.debtscredits.debtscreditsapi.modules.Credits.model;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import br.com.debtscredits.debtscreditsapi.modules.Categoria.model.Category;
import br.com.debtscredits.debtscreditsapi.modules.Credits.dto.CreditsRequest;
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
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CREDITS")
public class CreditsModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id; 
    
    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "TIPO_CREDITO", nullable = false)
    private String tipo_credito;

    @Column(name = "DATE_IMPORT", nullable = false, updatable = false)
    private LocalDateTime date_import;

    @PrePersist
    public void PrePersist() {
        date_import = LocalDateTime.now();
    }

    @Column(name = "METODO_PAGAMENTO", nullable = false)
    private String metodo_pagamento;

    @ManyToOne
    @JoinColumn(name = "FK_CATEGORY", nullable = false)
    private Category category;

    @Column(name = "VALOR", nullable = false)
    private Double valor; 

    public static CreditsModel of(CreditsRequest request,Category category) {
                return CreditsModel
                .builder()
                .category(category)
                .descricao(request.getDescricao())
                .valor(request.getValor())
                .build();
            }
}
