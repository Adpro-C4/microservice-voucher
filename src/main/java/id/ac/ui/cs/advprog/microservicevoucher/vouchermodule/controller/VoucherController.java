package id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.controller;

import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.model.Voucher;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voucher")
public class VoucherController {
    private final VoucherService service;

    @Autowired
    public VoucherController(VoucherService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public List<Voucher> findAll() {
        return service.findAll();
    }

    @GetMapping("/id")
    public Voucher findById(@RequestParam Long id) {
        return service.findVoucherById(id);
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
