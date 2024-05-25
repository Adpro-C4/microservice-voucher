package id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.service;

import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.model.Notification;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.model.Voucher;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.model.dto.DTOCustomer;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.repository.dto.DTOCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Service
public class NotificationServiceImpl implements NotificationService{
    private final DTOCustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final String notificationUpdateUri;
    private static final Logger logger = Logger.getLogger(NotificationServiceImpl.class.getName());

    @Autowired
    public NotificationServiceImpl(DTOCustomerRepository customerRepository, RestTemplate restTemplate, @Value("${notification.update.uri}") String notificationUpdateUri) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
        this.notificationUpdateUri = notificationUpdateUri;
    }

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
                CompletableFuture<Void> future = customer.update(payload, uri, restTemplate, logger);
                future.thenRun(() -> logger.info(String.format("Update completed for customer %s%n", customer.getUsername())));
            } catch (RestClientException ignored) {
            }
        }
    }
}
