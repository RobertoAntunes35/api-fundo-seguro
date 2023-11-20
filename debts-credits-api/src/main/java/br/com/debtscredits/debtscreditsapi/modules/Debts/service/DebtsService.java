package br.com.debtscredits.debtscreditsapi.modules.Debts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.debtscredits.debtscreditsapi.config.exception.ValidationException;
import br.com.debtscredits.debtscreditsapi.modules.Categoria.service.CategoryService;
import br.com.debtscredits.debtscreditsapi.modules.Debts.dto.DebtsRequest;
import br.com.debtscredits.debtscreditsapi.modules.Debts.dto.DebtsResponse;
import br.com.debtscredits.debtscreditsapi.modules.Debts.model.DebtsModel;
import br.com.debtscredits.debtscreditsapi.modules.Debts.repository.DebtsRepository;
import static org.springframework.util.ObjectUtils.isEmpty;



@Service
public class DebtsService {
    
   @Autowired
   private DebtsRepository debtsRepository;

   @Autowired
   private CategoryService categoryService;


   public List<DebtsResponse> findByNome(String nome) {
      if(isEmpty(nome)) {
         throw new ValidationException("The nome must be informed");
      }
      return debtsRepository
            .findByNomeIgnoreCaseContaining(nome)
            .stream()
            .map(DebtsResponse::of)
            .collect(Collectors.toList());
   }    

   public List<DebtsResponse>findAll() {
      return debtsRepository
              .findAll()
              .stream()
              .map(DebtsResponse::of)
              .collect(Collectors.toList());
   }

   public DebtsResponse findByIdResponse(Integer id) {
      return DebtsResponse.of(findById(id));
   }

   public DebtsModel findById(Integer id) {
      if (isEmpty(id)) {
         throw new ValidationException("The credits ID to category was not informed");
      }
      return debtsRepository
            .findById(id)
            .orElseThrow(() -> new ValidationException("The debts ID must be informed"));
   }

   public DebtsResponse save(DebtsRequest request) {
    validateCategoryIdInformed(request);
    validateDataDebtsNomeInformed(request);
      
    var category = categoryService.findById(request.getCategoryId());
    var debts = debtsRepository.save(DebtsModel.of(request, category));
    return DebtsResponse.of(debts);
}
   private void validateCategoryIdInformed(DebtsRequest request) {
    if (isEmpty(request.getCategoryId())) {
        throw new ValidationException("The category id wasn't informed");
    }
   }
    private void validateDataDebtsNomeInformed(DebtsRequest request) {
        if(isEmpty(request.getNome())) {
            throw new ValidationException("The description credits wasn't not informed");
        }
    }
}
