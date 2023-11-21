package br.com.debtscredits.debtscreditsapi.modules.Credits.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// import br.com.debtscredits.debtscreditsapi.modules.Credits.dto.CreditsPropriertsDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditsDTO {
    private String creditsIdTransation;
    private List<CreditsPropriertsDTO> credits;
}
