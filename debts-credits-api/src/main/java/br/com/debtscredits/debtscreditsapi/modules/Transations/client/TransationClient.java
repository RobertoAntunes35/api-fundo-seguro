package br.com.debtscredits.debtscreditsapi.modules.Transations.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.debtscredits.debtscreditsapi.modules.Transations.dto.TransationResponseDTO;

import java.util.Optional;

@FeignClient(
    name = "salesClient",
    contextId = "salesCliente",
    url = "${app-config.services.transation}"
)
public interface TransationClient {
    @GetMapping("credits/{creditsId}")
    Optional<TransationResponseDTO> findByCreditsId(@PathVariable Integer creditsId);
}
