package enums;

import lombok.Getter;

@Getter
public enum NotificationStatus {
    CREATED("CREATED", "New voucher available:\n"),
    UPDATED("UPDATED", "Remember to use the following voucher before it runs out:\n"),
    DELETED("DELETED", "Hope you didn't miss out on the following voucher:\n");

    private final String value;
    private final String msg;
    NotificationStatus(String value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public static boolean contains(String param) {
        for (NotificationStatus notificationStatus : NotificationStatus.values()) {
            if (notificationStatus.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}
