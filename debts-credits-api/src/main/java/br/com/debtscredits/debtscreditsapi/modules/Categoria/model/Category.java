package br.com.debtscredits.debtscreditsapi.modules.Categoria.model;

import br.com.debtscredits.debtscreditsapi.modules.Categoria.dto.CategoryRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name="TIPO", nullable = false)
    private String tipo;
    
    public static Category of(CategoryRequest request) {
        var response = new Category();
        BeanUtils.copyProperties(request, response);
        return  response;
    }
}
