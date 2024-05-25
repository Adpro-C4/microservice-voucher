package id.ac.ui.cs.advprog.microservicevoucher.receivermodule.repository;

import id.ac.ui.cs.advprog.microservicevoucher.receivermodule.model.CustomerNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerNotificationRepository extends JpaRepository<CustomerNotification, String> {
    CustomerNotification findByCustomerUsername(String username);
}
