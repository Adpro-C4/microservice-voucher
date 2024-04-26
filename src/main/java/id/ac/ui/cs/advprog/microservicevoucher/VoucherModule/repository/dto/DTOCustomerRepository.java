package id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.repository.dto;

import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.dto.DTOCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DTOCustomerRepository extends JpaRepository<DTOCustomer, String> {
}
