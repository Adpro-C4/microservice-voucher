package id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class Voucher {
    private Long voucherId;
    private String voucherName;
    private String voucherDesc;
    private Double voucherDiscount;
    private Integer voucherQuota;

    public Voucher(Long voucherId, String voucherName, String voucherDesc, Double voucherDiscount, Integer voucherQuota) {
    }
}
