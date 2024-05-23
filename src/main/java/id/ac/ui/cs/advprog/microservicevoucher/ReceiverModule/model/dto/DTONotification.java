package id.ac.ui.cs.advprog.microservicevoucher.ReceiverModule.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DTONotification {
    String voucherName;
    String voucherDiscount;
    String voucherQuota;
    String status;
    String message;
}
