package settings;

public enum Banks {
    PRIVATE("ПриватБанк", "Private", true),
    MONO("МоноБанк", "Monobank", false),
    NBU("НБУ", "NBU", false);

    private String bankNameUA;
    private String bankNameEN;
    private boolean select;

    Banks(String bankNameUA, String bankNameEN, boolean select) {
        this.bankNameUA = bankNameUA;
        this.bankNameEN = bankNameEN;
        this.select = select;
    }

    public String getBankNameEN() {
        return bankNameEN;
    }

    public void setBankNameEN(String bankNameEN) {
        this.bankNameEN = bankNameEN;
    }

    public String getBankNameUA() {
        return bankNameUA;
    }

    public void setBankNameUA(String bankNameUA) {
        this.bankNameUA = bankNameUA;
    }

    public boolean isSelect() { return select; }

    public void setSelect(boolean select) { this.select = select; }

    public static String getButtonStatus(Banks button){
        if(button.isSelect()){
            return "✅";
        }
        return "";
    }
}
