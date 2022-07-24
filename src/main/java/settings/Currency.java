package settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Currency {

    List<Curr> currency = Arrays.asList(
            new Curr("USD", true),
            new Curr("EUR", false),
            new Curr("PLN", false),
            new Curr("BTC", false)
    );

    public List<Curr> getCurrency() {
        return currency;
    }

    public void setCurrency(List<Curr> currency) {
        this.currency = currency;
    }

    public static class Curr {
        private String currName;
        private boolean currSelected;

        public Curr(String currName, boolean currSelected) {
            this.currName = currName;
            this.currSelected = currSelected;
        }

        public String getCurrName() {
            return currName;
        }

        public void setCurrName(String currName) {
            this.currName = currName;
        }

        public boolean isCurrSelected() {
            return currSelected;
        }

        public void setCurrSelected(boolean currSelected) {
            this.currSelected = currSelected;
        }
    }


    public String getCurrencyButtonsStatus(String currName) {
        for (Curr curr : currency) {
            if (curr.currName.equals(currName)) {
                if (curr.currSelected) {
                    return "✅";
                }
            }
        }
        return "";
    }
}
