package id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model;

import enums.NotificationStatus;
import lombok.Getter;

@Getter
public class Notification {
    String voucherName;
    String voucherDiscount;
    String voucherQuota;
    NotificationStatus status;

    public Notification(String name, Double discount, Integer quota, NotificationStatus status) {
        this.voucherName = name;
        this.voucherDiscount = String.format("%.2f%%", (discount*100));
        this.voucherQuota = quota.toString();
        setStatus(status);
    }

    public void setStatus(NotificationStatus status) {
        if (NotificationStatus.contains(status.name())) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
