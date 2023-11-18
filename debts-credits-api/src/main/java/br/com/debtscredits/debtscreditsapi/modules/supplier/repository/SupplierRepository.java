package br.com.debtscredits.debtscreditsapi.modules.supplier.repository;

import br.com.debtscredits.debtscreditsapi.modules.supplier.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}
