package id.ac.ui.cs.advprog.microservicevoucher.ReceiverModule.model.dto;

import enums.NotificationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
public class DTONotification {
    @Setter
    String voucherName;
    @Setter
    Double voucherDiscount;
    @Setter
    Integer voucherQuota;
    String status;
    String message;

    public DTONotification(String voucherName, Double voucherDiscount, Integer voucherQuota, String status, String message) {
        this.voucherName = voucherName;
        this.voucherDiscount = voucherDiscount;
        this.voucherQuota = voucherQuota;
        setStatus(status);
        setMessage(message);
    }

    public void setStatus(String status) {
        if (!NotificationStatus.contains(status)) {throw new IllegalArgumentException();}
        this.status = status;
    }

    public void setMessage(String message) {
        String expectedMessage = String.format("%sVoucher Name: %s\nValue: %.2f%% off\nUsage Quota: %d", NotificationStatus.valueOf(this.status).getMsg(), this.voucherName, (this.voucherDiscount*100), this.voucherQuota);
        if (message.equals(expectedMessage)) {
            this.message = message;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
