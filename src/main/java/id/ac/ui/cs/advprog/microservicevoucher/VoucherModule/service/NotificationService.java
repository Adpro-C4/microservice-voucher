package id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.service;

import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Voucher;

public interface NotificationService {
    public void notify(String status, Voucher voucher);
}
