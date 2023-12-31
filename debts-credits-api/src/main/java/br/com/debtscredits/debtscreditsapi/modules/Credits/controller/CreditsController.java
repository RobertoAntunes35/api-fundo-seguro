package br.com.debtscredits.debtscreditsapi.modules.Credits.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.debtscredits.debtscreditsapi.config.SucessResponse;
import br.com.debtscredits.debtscreditsapi.modules.Credits.dto.CreditsRequest;
import br.com.debtscredits.debtscreditsapi.modules.Credits.dto.CreditsResponse;
import br.com.debtscredits.debtscreditsapi.modules.Credits.service.CreditsService;


@RestController
@RequestMapping("api/credits")
public class CreditsController {
    @Autowired
    private CreditsService creditsService;

    @PostMapping
    public CreditsResponse save(@RequestBody CreditsRequest request) {
        return creditsService.save(request);
    }
    @GetMapping
    public List<CreditsResponse> findAll() {
        return creditsService.findAll();
    }

    @GetMapping("{description}")
    public List<CreditsResponse> findByDescription(@PathVariable String description) {
        return creditsService.findByDescricao(description);
    }

    @GetMapping("id/{id}")
    public CreditsResponse findById(@PathVariable Integer id) {
        return creditsService.findByIdResponse(id);
    }

    @GetMapping("category/{categoryId}") 
    public List<CreditsResponse> findByCategoryId(@PathVariable Integer categoryId) {
        return creditsService.findByCategoryId(categoryId);
    }
    @PutMapping("{id}")
    public CreditsResponse update(@RequestBody CreditsRequest request, @PathVariable Integer id) {
        return creditsService.update(request, id);
    }

    @DeleteMapping("{id}") 
    public SucessResponse delete(@PathVariable Integer id) {
        creditsService.delete(id);
        return SucessResponse.create("The credits was deleted");
    }
}

