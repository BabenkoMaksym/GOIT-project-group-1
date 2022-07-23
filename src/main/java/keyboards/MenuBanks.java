package keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import settings.Banks;

import java.util.ArrayList;
import java.util.List;


public class MenuBanks {
    public static InlineKeyboardMarkup keyboard() {
        List<List<InlineKeyboardButton>> keyboardMenuBanks = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow4 = new ArrayList<>();
        InlineKeyboardButton buttonPrivat = InlineKeyboardButton.builder()
                .text(Banks.PRIVATE.getBankNameEN() + Banks.getButtonStatus(Banks.PRIVATE))
                .callbackData(Banks.PRIVATE.getBankNameEN())
                .build();
        InlineKeyboardButton buttonNBU = InlineKeyboardButton.builder()
                .text(Banks.NBU.getBankNameEN() + Banks.getButtonStatus(Banks.NBU))
                .callbackData(Banks.NBU.getBankNameEN())
                .build();
        InlineKeyboardButton buttonMonobank = InlineKeyboardButton.builder()
                .text(Banks.MONO.getBankNameEN() + Banks.getButtonStatus(Banks.MONO))
                .callbackData(Banks.MONO.getBankNameEN())
                .build();
        InlineKeyboardButton buttonBack = InlineKeyboardButton.builder()
                .text("↩️")
                .callbackData("BackToSettings")
                .build();
        keyboardMSetRow1.add(buttonPrivat);
        keyboardMSetRow2.add(buttonNBU);
        keyboardMSetRow3.add(buttonMonobank);
        keyboardMSetRow4.add(buttonBack);
        keyboardMenuBanks.add(keyboardMSetRow1);
        keyboardMenuBanks.add(keyboardMSetRow2);
        keyboardMenuBanks.add(keyboardMSetRow3);
        keyboardMenuBanks.add(keyboardMSetRow4);

        return InlineKeyboardMarkup.builder().keyboard(keyboardMenuBanks).build();
    }
}
