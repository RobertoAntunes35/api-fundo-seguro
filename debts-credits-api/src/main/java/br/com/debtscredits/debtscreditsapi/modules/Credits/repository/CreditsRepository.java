package br.com.debtscredits.debtscreditsapi.modules.Credits.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import br.com.debtscredits.debtscreditsapi.modules.Credits.model.CreditsModel;

public interface CreditsRepository extends JpaRepository<CreditsModel, Integer>{
    List<CreditsModel> findByDescricaoIgnoreCaseContaining (String descricao);
    List<CreditsModel> findByCategoryId(Integer id);
    Boolean existsByCategoryId(Integer id);
}