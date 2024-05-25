package id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.service;

import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.model.Voucher;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.model.dto.DTOCustomer;

public interface NotificationService {
    public DTOCustomer acceptNotification(DTOCustomer customer);

    public DTOCustomer rejectNotification(DTOCustomer customer);

    public void notify(String status, Voucher voucher);
}
