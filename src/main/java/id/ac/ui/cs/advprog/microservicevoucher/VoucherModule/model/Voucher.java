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
        if (newVoucherName.isBlank()) {
            throw new IllegalArgumentException();
        }
        this.voucherName = newVoucherName;
    }

    public void setVoucherDiscount(double newVoucherDiscount) {
        if (newVoucherDiscount > 1.0 || newVoucherDiscount < 0.0) {
            throw new IllegalArgumentException();
        }
        this.voucherDiscount = newVoucherDiscount;
    }

    public void setVoucherQuota(int newVoucherQuota) {
        if (newVoucherQuota == 0 || newVoucherQuota < -1) {
            throw new IllegalArgumentException();
        }
        this.voucherQuota = newVoucherQuota;
    }
}
