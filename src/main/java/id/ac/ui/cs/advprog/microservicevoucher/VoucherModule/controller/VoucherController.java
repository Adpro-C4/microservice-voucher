package id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.controller;

import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Voucher;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private VoucherService service;

    @GetMapping("/list")
    public List<Voucher> findAll() {
        return service.findAll();
    }

    @PostMapping("/create")
    public Voucher createVoucher(@RequestBody Voucher voucher) {
        return service.save(voucher);
    }

    @PostMapping("/update")
    public Voucher updateVoucher(@RequestBody Voucher voucher) {
        return service.save(voucher);
    }

    @PostMapping("/delete")
    public void deleteVoucherById(@RequestParam Long id) {
        service.deleteById(id);
    }

}
