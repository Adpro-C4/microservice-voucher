package id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.model;

import enums.NotificationStatus;
import lombok.Getter;

@Getter
public class Notification {
    String voucherName;
    Double voucherDiscount;
    Integer voucherQuota;
    String status;
    String message;

    public Notification(String name, Double discount, Integer quota, String status) {
        this.voucherName = name;
        this.voucherDiscount = discount;
        this.voucherQuota = quota;
        setStatus(status);
    }

    public void setStatus(String status) {
        if (NotificationStatus.contains(status)) {
            this.status = status;
            this.message = String.format("%sVoucher Name: %s\nValue: %.2f%% off\nUsage Quota: %d", NotificationStatus.valueOf(status).getMsg(), this.voucherName, (this.voucherDiscount*100), this.voucherQuota);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
