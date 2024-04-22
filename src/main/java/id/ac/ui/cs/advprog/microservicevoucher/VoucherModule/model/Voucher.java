package id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private String voucherDescription;

    @Column(name = "voucher_discount", nullable = false)
    private Double voucherDiscount;

    @Column(name = "voucher_quota", nullable = false)
    private Integer voucherQuota;

    public Voucher(String voucherName, String voucherDescription, Double voucherDiscount, Integer voucherQuota) {
        setVoucherName(voucherName);
        this.voucherDescription = voucherDescription;
        setVoucherDiscount(voucherDiscount);
        setVoucherQuota(voucherQuota);
    }

    public Voucher() {}

    public void setVoucherName(String newVoucherName) {
        if (newVoucherName.isBlank()) {
            throw new IllegalArgumentException("Voucher Name can't be empty");
        }
        this.voucherName = newVoucherName;
    }

    public void setVoucherDiscount(double newVoucherDiscount) {
        if (newVoucherDiscount > 1.0 || newVoucherDiscount < 0.0) {
            throw new IllegalArgumentException("Discount should be between 0 and 1");
        }
        this.voucherDiscount = newVoucherDiscount;
    }

    public void setVoucherQuota(int newVoucherQuota) {
        if (newVoucherQuota == 0 || newVoucherQuota < -1) {
            throw new IllegalArgumentException("Quota should either be at least 1 for finite use or -1 for infinite use");
        }
        this.voucherQuota = newVoucherQuota;
    }
}
