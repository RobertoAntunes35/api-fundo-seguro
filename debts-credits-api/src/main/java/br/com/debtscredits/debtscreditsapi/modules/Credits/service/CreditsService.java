package br.com.debtscredits.debtscreditsapi.modules.Credits.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.debtscredits.debtscreditsapi.config.exception.ValidationException;
import br.com.debtscredits.debtscreditsapi.modules.Categoria.service.CategoryService;
import br.com.debtscredits.debtscreditsapi.modules.Credits.dto.CreditsRequest;
import br.com.debtscredits.debtscreditsapi.modules.Credits.dto.CreditsResponse;
import br.com.debtscredits.debtscreditsapi.modules.Credits.model.CreditsModel;
import br.com.debtscredits.debtscreditsapi.modules.Credits.repository.CreditsRepository;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class CreditsService {
    
   @Autowired
   private CreditsRepository creditsRepository;

   @Autowired
   private CategoryService categoryService;


   public List<CreditsResponse> findByDescricao(String description) {
      if (isEmpty(description)) {
         throw new ValidationException("The description must be informed");
      }
      return creditsRepository
              .findByDescricaoIgnoreCaseContaining(description)
              .stream()
              .map(CreditsResponse::of)
              .collect(Collectors.toList());
   }    

   public List<CreditsResponse>findAll() {
      return creditsRepository
              .findAll()
              .stream()
              .map(CreditsResponse::of)
              .collect(Collectors.toList());
   }

   public CreditsResponse findByIdResponse(Integer id) {
      return CreditsResponse.of(findById(id));
   }

   public CreditsModel findById(Integer id) {
      if (isEmpty(id)) {
         throw new ValidationException("The credits ID to category was not informed");
      }
      return creditsRepository
              .findById(id)
              .orElseThrow(() -> new ValidationException("There's no supplier fot the given ID"));
   }

   public CreditsResponse save(CreditsRequest request) {
    validateCategoryIdInformed(request);
    validateDataCreditsDescriptionInformed(request);
      
    var category = categoryService.findById(request.getCategoryId());
    var credits = creditsRepository.save(CreditsModel.of(request, category));
    return CreditsResponse.of(credits);
}
   private void validateCategoryIdInformed(CreditsRequest request) {
    if (isEmpty(request.getCategoryId())) {
        throw new ValidationException("The category id wasn't informed");
    }
   }
    private void validateDataCreditsDescriptionInformed(CreditsRequest request) {
        if(isEmpty(request.getDescricao())) {
            throw new ValidationException("The description credits wasn't not informed");
        }
    }
}
