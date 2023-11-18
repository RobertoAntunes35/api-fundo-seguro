package br.com.debtscredits.debtscreditsapi.modules.Credits.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return null;
    }

    @GetMapping("{description}")
    public List<CreditsResponse> findByDescription(@PathVariable Integer description) {
        return null;
    }

 }
