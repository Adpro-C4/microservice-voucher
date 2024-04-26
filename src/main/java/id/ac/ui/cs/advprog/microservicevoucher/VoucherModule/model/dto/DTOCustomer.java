package id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.dto;

import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Notification;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Embeddable
public class DTOCustomer {
    @Id
    private String userId;
    private String fullname;
    private String username;
    private String phoneNumber;
    private String email;

    public void update(Notification payload) {
        // TODO: implement send request

    }
}