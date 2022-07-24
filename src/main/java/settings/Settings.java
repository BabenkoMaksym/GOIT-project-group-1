package settings;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import serviceClasses.Bank;
import serviceClasses.CurrencyDataBase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Settings {
    public static Map<Long, Setting> settings = new HashMap<>();
    private static final Gson settingGson = new Gson();
    private static final String SETTING_GSON_PATH = "src/main/resources/settings.json";

    private static Object monitor = new Object();

    public static void save() {
        synchronized (monitor) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileSettingsGsonCheck()))) {
                writer.write(settingGson.toJson(settings));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void load() {
        synchronized (monitor) {
            try {
                settings = new ObjectMapper().readValue(fileSettingsGsonCheck(), new TypeReference<>() {
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static File fileSettingsGsonCheck() {
        File settingGsonFile = new File(SETTING_GSON_PATH);
        if (!settingGsonFile.exists()) {
            System.out.println("Create Path for Gson file Settings - " + settingGsonFile.getParentFile().mkdirs());
            try {
                System.out.println("Create new Gson file Settings - " + settingGsonFile.createNewFile());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return settingGsonFile;
    }


    public static String getInfo(Long chatId) {
        StringBuilder messageToUser = new StringBuilder();
        Setting userSetting = settings.get(chatId);
        String bankName = userSetting.getSelectedBank().getBankNameUA();
        messageToUser.append(bankName).append("\n");
        int numberDecPlaces = userSetting.getNumberOfDecimalPlaces();
        int notificationHour = userSetting.getNotificationTime().getTime();
        List<Currency.Curr> currency1 = userSetting.getSelectedCurrency().getCurrency();
        Bank bankInfo = CurrencyDataBase.getCurrentInfo(userSetting.getSelectedBank());
        for (Currency.Curr curr : currency1) {
            messageToUser.append("Курс купівлі ")
                    .append(curr.getCurrName())
                    .append(" - ")
                    .append(Math.round(bankInfo.getBuyRate(currency) * Math.pow(10, numberDecPlaces))
                            / Math.pow(10, numberDecPlaces))
                    .append("\n");
            messageToUser.append("Курс продажу ")
                    .append(currency.getCurrencyName())
                    .append(" - ")
                    .append(Math.round(bankInfo.getSellRate(currency) * Math.pow(10, numberDecPlaces))
                            / Math.pow(10, numberDecPlaces))
                    .append("\n");
        }
        return messageToUser.toString();
    }
}
