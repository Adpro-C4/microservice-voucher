package id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.dto;

import enums.NotificationStatus;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Notification;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

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
        RestTemplate restTemplate = new RestTemplate();
        String uri = "";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Notification> requestEntity = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<Void> responseEntity = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    Void.class
            );

            HttpStatusCode statusCode = responseEntity.getStatusCode();
            int statusCodeValue = statusCode.value();
            System.out.println("Status Code: " + statusCodeValue);

            if (statusCodeValue == HttpStatus.OK.value()) {
                System.out.println("Sent:\n" + responseEntity.getBody());
            } else {
                System.out.println("Nothing was sent");
            }
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}