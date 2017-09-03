package demo.model;

public enum FundType {
    POLISH("polish"),
    FOREIGN("foreign"),
    MONEY("money")
    ;

    private String code;

    FundType(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
