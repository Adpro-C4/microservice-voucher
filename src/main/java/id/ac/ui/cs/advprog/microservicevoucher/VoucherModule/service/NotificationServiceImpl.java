package id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.service;

import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Notification;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Voucher;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.dto.DTOCustomer;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.repository.dto.DTOCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    DTOCustomerRepository customerRepository;

    @Autowired
    RestTemplate restTemplate;

    @Value("${notification.update.uri}")
    private String notificationUpdateUri;

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
            try {
                String uri = notificationUpdateUri+customer.getUsername();
                CompletableFuture<Void> future = customer.update(payload, uri, restTemplate);
                future.thenRun(() -> System.out.printf("Update completed for customer %s%n", customer.getUsername()));
            } catch (RestClientException ignored) {
            }
        }
    }
}
