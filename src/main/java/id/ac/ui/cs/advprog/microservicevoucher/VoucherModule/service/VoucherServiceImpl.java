package id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.service;

import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Voucher;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.repository.VoucherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService{
    @Autowired
    VoucherRepository voucherRepository;

    @Override
    public Voucher save(Voucher voucher) {
        voucherRepository.save(voucher);
        return voucher;
    }

    @Override
    public Voucher delete(Voucher voucher) {
        voucherRepository.deleteById(voucher.getVoucherId());
        return voucher;
    }

    @Override
    public List<Voucher> findAll() {
        return voucherRepository.findAll();
    }

    @Override
    public Voucher findVoucherById(Long id) {
        return voucherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Voucher with such id not found"));
    }

    @Override
    public List<Voucher> findAllVoucherByName(String name) {
        String[] nameSplit = name.split(" ");

        Iterator<Voucher> vouchers = voucherRepository.findAll().iterator();
        List<Voucher> result = new ArrayList<>();
        while (vouchers.hasNext()) {
            Voucher current = vouchers.next();
            String currentName = current.getVoucherName();
            boolean includeCurrent = true;
            for (String subName : nameSplit) {
                if (!currentName.contains(subName)) {
                    includeCurrent = false;
                    break;
                }
            }
            if (includeCurrent) {
                result.add(current);
            }
        }
        return result;
    }

    @Override
    public List<Voucher> findAllVoucherByDiscount(Double discount) {
        Iterator<Voucher> vouchers = voucherRepository.findAll().iterator();
        List<Voucher> result = new ArrayList<>();

        while (vouchers.hasNext()) {
            Voucher current = vouchers.next();
            if (current.getVoucherDiscount().equals(discount)) {
                result.add(current);
            }
        }
        return result;
    }

    @Override
    public List<Voucher> findAllVoucherByUsageQuota(Integer quota) {
        Iterator<Voucher> vouchers = voucherRepository.findAll().iterator();
        List<Voucher> result = new ArrayList<>();

        while (vouchers.hasNext()) {
            Voucher current = vouchers.next();
            if (current.getVoucherQuota().equals(quota)) {
                result.add(current);
            }
        }
        return result;
    }

}
