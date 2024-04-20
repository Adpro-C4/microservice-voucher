package id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class Voucher {
    private Long voucherId;
    private String voucherName;
    @Setter
    private String voucherDesc;
    private Double voucherDiscount;
    private Integer voucherQuota;

    public Voucher(Long voucherId, String voucherName, String voucherDesc, Double voucherDiscount, Integer voucherQuota) {
        this.voucherId = voucherId;
        this.voucherName = voucherName;
        this.voucherDesc = voucherDesc;
        this.voucherDiscount = voucherDiscount;
        this.voucherQuota = voucherQuota;
    }

    public void setVoucherName(String newVoucherName) {
    }

    public void setVoucherDiscount(double newVoucherDiscount) {
    }

    public void setVoucherQuota(int newVoucherQuota) {
    }
}
