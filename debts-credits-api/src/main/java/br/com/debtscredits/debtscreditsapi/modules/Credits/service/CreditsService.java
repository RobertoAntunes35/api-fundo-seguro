package br.com.debtscredits.debtscreditsapi.modules.Credits.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.debtscredits.debtscreditsapi.config.SucessResponse;
import br.com.debtscredits.debtscreditsapi.config.exception.ValidationException;
import br.com.debtscredits.debtscreditsapi.modules.Categoria.service.CategoryService;
import br.com.debtscredits.debtscreditsapi.modules.Credits.dto.CreditsDTO;
import br.com.debtscredits.debtscreditsapi.modules.Credits.dto.CreditsRequest;
import br.com.debtscredits.debtscreditsapi.modules.Credits.dto.CreditsResponse;
import br.com.debtscredits.debtscreditsapi.modules.Credits.model.CreditsModel;
import br.com.debtscredits.debtscreditsapi.modules.Credits.repository.CreditsRepository;
import br.com.debtscredits.debtscreditsapi.modules.Transations.dto.TransationConfirmationDTO;
import br.com.debtscredits.debtscreditsapi.modules.Transations.enums.TransationStatus;
import br.com.debtscredits.debtscreditsapi.modules.Transations.rabbitmq.TransationConfirmationSender;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.util.ObjectUtils.isEmpty;
@Slf4j
@Service
public class CreditsService {
    
   @Autowired
   private CreditsRepository creditsRepository;

   @Autowired
   private CategoryService categoryService;

   @Autowired
   private TransationConfirmationSender transationConfirmationSender;

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

   public List<CreditsResponse> findByCategoryId(Integer categoryId) {
      if(isEmpty(categoryId)) {
         throw new ValidationException("The category ID name must be informed");
      }
      return creditsRepository
      .findByCategoryId(categoryId)
      .stream()
      .map(CreditsResponse::of)
      .collect(Collectors.toList());
   }

   public Boolean existByCategoryId(Integer categoryId) {
      return creditsRepository.existsByCategoryId(categoryId);
   } 

   public SucessResponse delete(Integer id) {
      if (isEmpty(id)) {
         throw new ValidationException("The id wasn't informed to remove the credits");
      }
      creditsRepository.deleteById(id);
      return SucessResponse.create("The credits was deleted");
   }
   
   public CreditsResponse update(CreditsRequest request, Integer id) {
      validateDataIdCreditsInformed(id);
      validateCategoryIdInformed(request);
      var category = categoryService.findById(request.getCategoryId());
      var credits = creditsRepository.save(CreditsModel.of(request, category));
      credits.setId(id);
      return CreditsResponse.of(credits);
   }

   public void updateCreditsTransation(CreditsDTO credits) {
      log.info("Message {}", credits);
      try {
         validateTransationData(credits);
         updatedCredits(credits);
         
      } catch (Exception ex) {
         ex.getMessage();
      }
   }
   
   private void validateTransationData(CreditsDTO credits) {
      if(isEmpty(credits) || isEmpty(credits.getCreditsIdTransation())) {
         throw new ValidationException("The credits data or transation ID cannot be null");

      }
   }

   @Transactional
   private void updatedCredits(CreditsDTO credits) {
      try {
         var approvedTransation = new TransationConfirmationDTO(credits.getCreditsIdTransation(), TransationStatus.APPROVED);
         var creditsSending = credits.getCredits();
         log.info("credits Sending: {}",creditsSending);
         transationConfirmationSender.sendTransationConfirmationMessage(approvedTransation);

      } catch (Exception ex) {
         log.error("Error while trying to transation att {}",ex.getMessage(), ex);
         var rejectedMessage = new TransationConfirmationDTO(credits.getCreditsIdTransation(), TransationStatus.REJECTED);
         transationConfirmationSender.sendTransationConfirmationMessage(rejectedMessage);
      }
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
    private void validateDataIdCreditsInformed(Integer id) {
      if (isEmpty(id)) {
         throw new ValidationException("The ID to the credits wasn't informed");
      }
    }
}
