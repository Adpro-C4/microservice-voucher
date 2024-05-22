package id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Voucher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    public List<Voucher> findAllByVoucherNameContainingIgnoreCase(String voucherName);

    public List<Voucher> findAllByVoucherDiscount(double discount);

    public List<Voucher> findAllByVoucherQuota(int quota);
}
