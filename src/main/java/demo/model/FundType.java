package demo.model;

public enum FundType {
    POLISH("polskie"),
    FOREIGN("zagraniczne"),
    MONEY("pieniezne")
    ;

    private String code;

    FundType(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
