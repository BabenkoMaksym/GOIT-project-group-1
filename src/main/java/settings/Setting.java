package settings;

import org.checkerframework.checker.units.qual.C;

import java.util.List;

public class Setting {
    private final long chatId;
    public Setting(long chatId) {
        this.chatId = chatId;
    }
    private NumberOfDecimalPlaces numberOfDecimalPlaces = NumberOfDecimalPlaces.THREE;
    private Banks selectedBank = Banks.PRIVATE;
    private Currency selectedCurrency = new Currency();
    private NotificationTime notificationTime = NotificationTime.NINE;

    public long getChatId() {
        return chatId;
    }

    public int getNumberOfDecimalPlaces() {
        return numberOfDecimalPlaces.getIntNumber();
    }

    public void setNumberOfDecimalPlaces(NumberOfDecimalPlaces numberOfDecimalPlaces) {
        this.numberOfDecimalPlaces = numberOfDecimalPlaces;
    }

    public Banks getSelectedBank() {
        return selectedBank;
    }

    public void setSelectedBank(Banks selectedBank) {
        this.selectedBank = selectedBank;
    }

    public Currency getSelectedCurrency() {
        return selectedCurrency;
    }

    public void setSelectedCurrency(Currency selectedCurrency) {
        this.selectedCurrency = selectedCurrency;
    }

    public NotificationTime getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(NotificationTime notificationTime) {
        this.notificationTime = notificationTime;
    }

    @Override
    public String toString() {
        return "chatId=" + chatId +
                ", numberOfDecimalPlaces=" + numberOfDecimalPlaces +
                ", selectedBank=" + selectedBank +
                ", selectedCurrency=" + selectedCurrency +
                ", notificationTime=" + notificationTime +
                '}';
    }
}
