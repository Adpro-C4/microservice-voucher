package id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.service;

import enums.NotificationStatus;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.model.Voucher;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.repository.VoucherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService{
    private final VoucherRepository voucherRepository;
    private final NotificationService notificationService;

    @Autowired
    public VoucherServiceImpl(VoucherRepository voucherRepository, NotificationService notificationService) {
        this.voucherRepository = voucherRepository;
        this.notificationService = notificationService;
    }

    @Override
    public Voucher save(Voucher voucher) {
        voucherRepository.save(voucher);
        notificationService.notify(NotificationStatus.CREATED.getValue(), voucher);
        return voucher;
    }

    @Override
    public void deleteById(Long id) {
        voucherRepository.deleteById(id);
    }

    @Override
    public List<Voucher> findAll() {
        return voucherRepository.findAll();
    }

    @Override
    public Voucher findVoucherById(Long id) {
        return voucherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Voucher with such id not found"));
    }

    @Override
    public List<Voucher> findAllVoucherByName(String name) {
        return voucherRepository.findAllByVoucherNameContainingIgnoreCase(name);
    }

    @Override
    public List<Voucher> findAllVoucherByDiscount(Double discount) {
        return voucherRepository.findAllByVoucherDiscount(discount);
    }

    @Override
    public List<Voucher> findAllVoucherByUsageQuota(Integer quota) {
        return voucherRepository.findAllByVoucherQuota(quota);
    }

}
