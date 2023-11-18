package br.com.debtscredits.debtscreditsapi.modules.supplier.dto;

import br.com.debtscredits.debtscreditsapi.modules.supplier.model.Supplier;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class SupplierResponse {
    private Integer id;
    private String description;

    public static SupplierResponse of(Supplier supplier) {
        var response = new SupplierResponse();
        BeanUtils.copyProperties(supplier, response);
        return  response;
    }
}
