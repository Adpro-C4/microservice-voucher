package id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.service;

import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Notification;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Voucher;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.dto.DTOCustomer;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.repository.dto.DTOCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    DTOCustomerRepository customerRepository;

    @Override
    public DTOCustomer acceptNotification(DTOCustomer customer) {
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public DTOCustomer rejectNotification(DTOCustomer customer) {
        customerRepository.delete(customer);
        return customer;
    }

    @Override
    public void notify(String status, Voucher voucher) {
        Notification payload = new Notification(
                voucher.getVoucherName(),
                voucher.getVoucherDiscount(),
                voucher.getVoucherQuota(),
                status
        );

        List<DTOCustomer> customers = customerRepository.findAll();
        for (DTOCustomer customer:customers) {
            CompletableFuture<Void> future = customer.update(payload);
            // Optionally, you can add a callback or combine with other futures
            future.thenRun(() -> System.out.println("Update completed for customer"));
        }
    }
}
