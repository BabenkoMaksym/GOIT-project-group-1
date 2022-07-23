package settings;

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
    public static Map<Long, Setting> settings = new HashMap<Long, Setting>();
    private static final Gson settingGson = new Gson();
    private static final String SETTING_GSON_PATH = "src/main/resources/settings.json";

    public synchronized static void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileSettingsGsonCheck()))){
            writer.write(settingGson.toJson(settings));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void load() {
        if (settings.isEmpty()) {
            settings = settingGson.fromJson(fileSettingsGsonCheck().toString(), settings.getClass());
        }
    }

    private static File fileSettingsGsonCheck() {
        File settingGsonFile = new File(SETTING_GSON_PATH);
        if (!settingGsonFile.exists()) {
            System.out.println("Create Path for Gson file Serrings - " + settingGsonFile.getParentFile().mkdirs());
            try {
                System.out.println("Create new Gson file Settings - " + settingGsonFile.createNewFile());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return settingGsonFile;
    }



    public static String getInfo (Long chatId) {
        StringBuilder messageToUser = new StringBuilder();
        Setting userSetting = settings.get(chatId);
        String bankName = userSetting.getSelectedBank().getBankNameUA();
        messageToUser.append(bankName).append("\n");
        int numberDecPlaces = userSetting.getNumberOfDecimalPlaces();
        int notificationHour = userSetting.getNotificationTime().getTime();
        List<Currency> currencies = userSetting.getSelectedCurrency();
        Bank bankInfo = CurrencyDataBase.getCurrentInfo(userSetting.getSelectedBank());
        for (Currency currency: currencies){
            messageToUser.append("Курс купівлі ")
                    .append(currency.getCurrencyName())
                    .append(" - ")
                    .append(Math.round(bankInfo.getBuyRate(currency) * Math.pow(10,numberDecPlaces))
                            /Math.pow(10,numberDecPlaces))
                    .append("\n");
            messageToUser.append("Курс продажу ")
                    .append(currency.getCurrencyName())
                    .append(" - ")
                    .append(Math.round(bankInfo.getSellRate(currency) * Math.pow(10,numberDecPlaces))
                            /Math.pow(10,numberDecPlaces))
                    .append("\n");
        }
        return messageToUser.toString();
    }
}
