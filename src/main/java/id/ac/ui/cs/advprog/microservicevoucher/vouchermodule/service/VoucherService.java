package id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.service;

import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.model.Voucher;

import java.util.List;

public interface VoucherService {
    public Voucher save(Voucher voucher);
    public void deleteById(Long id);
    public List<Voucher> findAll();
    public Voucher findVoucherById(Long id);
    public List<Voucher> findAllVoucherByName(String name);
    public List<Voucher> findAllVoucherByDiscount(Double discount);
    public List<Voucher> findAllVoucherByUsageQuota(Integer quota);

}
