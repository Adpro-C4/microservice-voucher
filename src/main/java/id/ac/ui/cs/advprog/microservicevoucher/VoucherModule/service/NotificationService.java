package id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.service;

import enums.NotificationStatus;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Voucher;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.dto.DTOCustomer;

public interface NotificationService {
    public DTOCustomer acceptNotification(DTOCustomer customer);

    public DTOCustomer rejectNotification(DTOCustomer customer);

    public void notify(NotificationStatus status, Voucher voucher);
}
