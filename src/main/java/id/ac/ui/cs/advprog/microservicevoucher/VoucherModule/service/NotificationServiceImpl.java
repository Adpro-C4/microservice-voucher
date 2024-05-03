package id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.service;

import enums.NotificationStatus;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Notification;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Voucher;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.dto.DTOCustomer;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.repository.dto.DTOCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{
//    private final RestTemplate restTemplate;
//
//    public NotificationServiceImpl(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
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
    public void notify(NotificationStatus status, Voucher voucher) {
        Notification payload = new Notification(
                voucher.getVoucherName(),
                voucher.getVoucherDiscount(),
                voucher.getVoucherQuota(),
                status
        );

//        String url = "http://something.com/list";
//        ResponseEntity<List<DTOCustomer>> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<DTOCustomer>>() {}
//        );
//        List<DTOCustomer> customers = response.getBody();
//
        List<DTOCustomer> customers = customerRepository.findAll();
        for (DTOCustomer customer:customers) {
            customer.update(payload);
        }
        // TODO: implement sending notifications
        // 1. customer model
        // 2. pub async func 'update' in model for sending data through http request
        // 3. fetch List<DTOCustomer> customers
        // 4. trigger update for (DTOCustomer customer:customers)
    }
}
