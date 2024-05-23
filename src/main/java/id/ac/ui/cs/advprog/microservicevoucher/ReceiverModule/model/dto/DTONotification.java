package id.ac.ui.cs.advprog.microservicevoucher.ReceiverModule.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DTONotification {
    String voucherName;
    Double voucherDiscount;
    Integer voucherQuota;
    String status;
    String message;

    public DTONotification(String voucherName, Double voucherDiscount, Integer voucherQuota, String status, String message) {
        this.voucherName = voucherName;
        this.voucherDiscount = voucherDiscount;
        this.voucherQuota = voucherQuota;
        this.status = status;
        this.message = message;
    }
}
