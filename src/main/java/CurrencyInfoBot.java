import com.fasterxml.jackson.databind.ObjectMapper;
import keyboards.*;
import org.checkerframework.checker.units.qual.C;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import settings.*;
import settings.Currency;

import java.io.IOException;
import java.util.*;

import static settings.Settings.fileSettingsGsonCheck;

public class CurrencyInfoBot extends TelegramLongPollingBot {
    private static CurrencyInfoBot instance;
    public String value;
    private Currency currency = new Currency();

    private CurrencyInfoBot(String value) {
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.value = value;
    }

    public static CurrencyInfoBot getInstance(String value) {
        if (instance == null) {
            instance = new CurrencyInfoBot(value);
        }
        return instance;
    }

    @Override
//    public String getBotUsername() {
//        return "@CurrencyInfoProjectGroup1Bot";
//    }
    public String getBotUsername() {
        return "TestKabaBOT";
    }

    @Override
//    public String getBotToken() {
//        return "5416117406:AAE1XHQxbn8TIY2perQrAAiQsNcxlcth9Wo";
//    }
    public String getBotToken() {
        return "5110494726:AAHvvtZ2yxM8dnzpR730WBz4eeG7haGp9Kw";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            try {
                handleMessage(update.getMessage());
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if (update.hasCallbackQuery()) {
            try {
                handleQuery(update.getCallbackQuery());
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void handleMessage(Message message) throws TelegramApiException {
        long chatId = message.getChatId();
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity;
            commandEntity = message.getEntities().stream()
                    .filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command = message.getText()
                        .substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                if (command.equals(Buttons.START.getNameEN())) {
                    printMessage(chatId, MenuStart.keyboard(),
                            "Ласкаво просимо.Цей бот дозволить відслідкувати актуальні курси валют.");
                    Setting setting = new Setting(chatId);
                    Settings.settings.put(chatId, setting);
                    Settings.save();
                    try {
                        Map<Long, Setting> sss = new HashMap<>();
                        sss = new ObjectMapper().readValue(fileSettingsGsonCheck(), sss.getClass());
                        System.out.println(sss);
                        Setting setting1 = sss.get(603642450);
                        currency = setting1.getSelectedCurrency();
                        currency.getCurrency().stream()
                                .forEach(curr -> System.out.println(curr.isCurrSelected()));

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } else {
            printMessage(chatId, "Будь ласка впишіть /start або натисніть кнопку.");
        }
    }

    private void handleQuery(CallbackQuery buttonQuery) throws TelegramApiException {

        long chatId = buttonQuery.getMessage().getChatId();
        String dataButtonQuery = buttonQuery.getData();
        switch (dataButtonQuery) {
            case "GET_INFO":
                printMessage(chatId, Settings.getInfo(chatId));
                break;
            case "SETTINGS":
                printMessage(chatId, MenuSettings.keyboard(Settings.settings.get(chatId)), "Виберіть налаштування");
                break;
            case "BACK_TO_START":
                printMessage(chatId, MenuStart.keyboard(), "Щоб отримати інфо натисність кнопку");
                break;
            case "NumDecimalPlaces":
                updateMessage(buttonQuery, MenuNumDecimalPlaces.keyboard());
                break;
            case "Bank":
                updateMessage(buttonQuery, MenuBanks.keyboard());
                break;
            case "Currency":
                updateMessage(buttonQuery, MenuCurrency.keyboard());
                break;
            case "Notification":
                updateMessage(buttonQuery, MenuNotification.keyboard());
                break;
            case "Private":
                saveSelectBanks(Banks.PRIVATE);
                updateMessage(buttonQuery, MenuBanks.keyboard());
                break;
            case "NBU":
                saveSelectBanks(Banks.NBU);
                updateMessage(buttonQuery, MenuBanks.keyboard());
                break;
            case "Monobank":
                saveSelectBanks(Banks.MONO);
                updateMessage(buttonQuery, MenuBanks.keyboard());
                break;
            case "twoPlaces":
                saveSelectNumDecPlaces(NumberOfDecimalPlaces.TWO);
                updateMessage(buttonQuery, MenuNumDecimalPlaces.keyboard());
                break;
            case "threePlaces":
                saveSelectNumDecPlaces(NumberOfDecimalPlaces.THREE);
                updateMessage(buttonQuery, MenuNumDecimalPlaces.keyboard());
                break;
            case "fourPlaces":
                saveSelectNumDecPlaces(NumberOfDecimalPlaces.FOUR);
                updateMessage(buttonQuery, MenuNumDecimalPlaces.keyboard());
                break;
            case "9":
                saveSelectNotificationTime(NotificationTime.NINE);
                updateMessage(buttonQuery, MenuNotification.keyboard());
                break;
            case "10":
                saveSelectNotificationTime(NotificationTime.TEN);
                updateMessage(buttonQuery, MenuNotification.keyboard());
                break;
            case "11":
                saveSelectNotificationTime(NotificationTime.ELEVEN);
                updateMessage(buttonQuery, MenuNotification.keyboard());
                break;
            case "12":
                saveSelectNotificationTime(NotificationTime.TWELVE);
                updateMessage(buttonQuery, MenuNotification.keyboard());
                break;
            case "13":
                saveSelectNotificationTime(NotificationTime.THIRTEEN);
                updateMessage(buttonQuery, MenuNotification.keyboard());
                break;
            case "14":
                saveSelectNotificationTime(NotificationTime.FOURTEEN);
                updateMessage(buttonQuery, MenuNotification.keyboard());
                break;
            case "15":
                saveSelectNotificationTime(NotificationTime.FIFTEEN);
                updateMessage(buttonQuery, MenuNotification.keyboard());
                break;
            case "16":
                saveSelectNotificationTime(NotificationTime.SIXTEEN);
                updateMessage(buttonQuery, MenuNotification.keyboard());
                break;
            case "17":
                saveSelectNotificationTime(NotificationTime.SEVENTEEN);
                updateMessage(buttonQuery, MenuNotification.keyboard());
                break;
            case "18":
                saveSelectNotificationTime(NotificationTime.EIGHTEEN);
                updateMessage(buttonQuery, MenuNotification.keyboard());
                break;
            case "0":
                saveSelectNotificationTime(NotificationTime.SWICH_OFF);
                updateMessage(buttonQuery, MenuNotification.keyboard());
                break;
            case "USD":
                saveSelectionCurrency("USD");
                updateMessage(buttonQuery, MenuCurrency.keyboard());
                break;
            case "EUR":
                saveSelectionCurrency("EUR");
                updateMessage(buttonQuery, MenuCurrency.keyboard());
                break;
            case "PLN":
                saveSelectionCurrency("PLN");
                updateMessage(buttonQuery, MenuCurrency.keyboard());
                break;
            case "BTC":
                saveSelectionCurrency("BTC");
                updateMessage(buttonQuery, MenuCurrency.keyboard());
                break;
        }
    }

    private void saveSelectionCurrency(String currName) {
        for (Currency.Curr curr : currency.getCurrency()) {
            if (curr.getCurrName().equals(currName)) {
                curr.setCurrSelected(!curr.isCurrSelected());
            }
        }
    }

    private void saveSelectNumDecPlaces(NumberOfDecimalPlaces enumDate) {
        for (NumberOfDecimalPlaces date : NumberOfDecimalPlaces.values()) {
            if (date.name().equals(enumDate.name())) {
                enumDate.setSelect(true);
            } else {
                date.setSelect(false);
            }
        }
    }

    private void saveSelectNotificationTime(NotificationTime enumDate) {
        for (NotificationTime date : NotificationTime.values()) {
            if (date.name().equals(enumDate.name())) {
                enumDate.setSelect(true);
            } else {
                date.setSelect(false);
            }
        }
    }

    private void saveSelectBanks(Banks enumDate) {
        for (Banks date : Banks.values()) {
            if (date.name().equals(enumDate.name())) {
                enumDate.setSelect(true);
            } else {
                date.setSelect(false);
            }
        }
    }

    private void printMessage(Long chatID, InlineKeyboardMarkup keyboard, String text)
            throws TelegramApiException {
        execute(SendMessage.builder()
                .text(text)
                .chatId(chatID)
                .replyMarkup(keyboard)
                .build());
    }

    public void printMessage(Long chatID, String messageText) throws TelegramApiException {
        execute(SendMessage.builder()
                .text(messageText)
                .chatId(chatID)
                .build());
    }

    private void updateMessage(CallbackQuery buttonQuery, InlineKeyboardMarkup keyboard)
            throws TelegramApiException {
        long chatId = buttonQuery.getMessage().getChatId();
        int messageId = buttonQuery.getMessage().getMessageId();
        execute(EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(keyboard)
                .build());
    }

    private Buttons convertToButtons(String buttonQuery) {
        for (Buttons button : Buttons.values()) {
            if (button.getNameEN().equals(buttonQuery)) {
                return button;
            }
        }
        return null;
    }

    public void checkMainButtons(CallbackQuery buttonQuery) throws TelegramApiException {
        long chatId = buttonQuery.getMessage().getChatId();
        String dataButtonQuery = buttonQuery.getData();
        switch (Objects.requireNonNull(convertToButtons(dataButtonQuery))) {
            case GET_INFO:
                printMessage(chatId, Settings.getInfo(chatId));
                break;
            case SETTINGS:
                printMessage(chatId, MenuSettings.keyboard(Settings.settings.get(chatId)), "Виберіть налаштування");
                break;
            case BACK_TO_START:
                printMessage(chatId, MenuStart.keyboard(), "Щоб отримати інфо натисність кнопку");
                break;
            case NUM_DECIMAL_PLACES:
                updateMessage(buttonQuery, MenuNumDecimalPlaces.keyboard());
                break;
            case BANK:
                updateMessage(buttonQuery, MenuBanks.keyboard());
                break;
            case CURRENCY:
                updateMessage(buttonQuery, MenuCurrency.keyboard());
                break;
            case NOTIFICATION:
                updateMessage(buttonQuery, MenuNotification.keyboard());
                break;
        }
    }
}



