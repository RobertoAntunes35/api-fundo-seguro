package br.com.debtscredits.debtscreditsapi.modules.Debts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import br.com.debtscredits.debtscreditsapi.modules.Debts.model.DebtsModel;

public interface DebtsRepository extends JpaRepository<DebtsModel, Integer>{
    List<DebtsModel> findByNomeIgnoreCaseContaining (String descricao);
}