package id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.service;

import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Notification;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Voucher;

public class NotificationServiceImpl implements NotificationService{
    @Override
    public void notify(String status, Voucher voucher) {
        Notification payload = new Notification(
                voucher.getVoucherName(),
                voucher.getVoucherDiscount(),
                voucher.getVoucherQuota(),
                status
        );

        // TODO: implement sending notifications
        // 1. customer model
        // 2. pub async func 'update' in model for sending data through http request
        // 3. fetch List<DTOCustomer> customers
        // 4. trigger update for (DTOCustomer customer:customers)
    }
}
