package br.com.debtscredits.debtscreditsapi.modules.Debts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.debtscredits.debtscreditsapi.modules.Debts.dto.DebtsRequest;
import br.com.debtscredits.debtscreditsapi.modules.Debts.dto.DebtsResponse;
import br.com.debtscredits.debtscreditsapi.modules.Debts.service.DebtsService;


@RestController
@RequestMapping("api/debts")
public class DebtsController {
    @Autowired
    private DebtsService debtsService;

    @PostMapping
    public DebtsResponse save(@RequestBody DebtsRequest request) {
        return debtsService.save(request);
    }
    @GetMapping
    public List<DebtsResponse> findAll() {
        return debtsService.findAll();
    }

    @GetMapping("{nome}")
    public List<DebtsResponse> findByDescription(@PathVariable String nome) {
        return debtsService.findByNome(nome);
    }

    @GetMapping("id/{id}")
    public DebtsResponse findById(@PathVariable Integer id) {
        return debtsService.findByIdResponse(id);
    }

 }

