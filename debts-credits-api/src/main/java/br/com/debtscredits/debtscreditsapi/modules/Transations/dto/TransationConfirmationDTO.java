package br.com.debtscredits.debtscreditsapi.modules.Transations.dto;

import br.com.debtscredits.debtscreditsapi.modules.Transations.enums.TransationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransationConfirmationDTO {
    private String creditsId;
    private TransationStatus status;
}
