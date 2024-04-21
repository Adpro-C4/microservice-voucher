package id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Entity
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voucherId;

    @Column(name = "voucher_name", nullable = false)
    private String voucherName;

    @Setter
    @Column(name = "voucher_desc")
    private String voucherDesc;

    @Column(name = "voucher_discount", nullable = false)
    private Double voucherDiscount;

    @Column(name = "voucher_quota", nullable = false)
    private Integer voucherQuota;

    public Voucher(Long voucherId, String voucherName, String voucherDesc, Double voucherDiscount, Integer voucherQuota) {
        this.voucherId = voucherId;
        setVoucherName(voucherName);
        this.voucherDesc = voucherDesc;
        setVoucherDiscount(voucherDiscount);
        setVoucherQuota(voucherQuota);
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
