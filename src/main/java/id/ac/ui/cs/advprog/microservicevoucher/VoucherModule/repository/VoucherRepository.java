package id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Voucher;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
}
