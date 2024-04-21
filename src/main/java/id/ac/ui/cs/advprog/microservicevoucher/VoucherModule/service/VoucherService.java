package id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.service;

import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Voucher;

import java.util.List;

public interface VoucherService {
    public Voucher save(Voucher voucher);
    public Voucher delete(Voucher voucher);
    public List<Voucher> findAll();
    public Voucher findVoucherById(Long id);
    public List<Voucher> findAllVoucherByName(String name);
    public List<Voucher> findAllVoucherByDiscount(Double discount);
    public List<Voucher> findAllVoucherByUsageQuota(Integer quota);
}
