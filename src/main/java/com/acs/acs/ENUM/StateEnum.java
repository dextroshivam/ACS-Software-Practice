package com.acs.acs.ENUM;

public enum StateEnum {
    ANDHRA_PRADESH("AP"),
    ARUNACHAL_PRADESH("AR"),
    ASSAM("AS"),
    BIHAR("BR"),
    CHHATTISGARH("CG"),
    GOA("GA"),
    GUJARAT("GJ"),
    HARYANA("HR"),
    HIMACHAL_PRADESH("HP"),
    JHARKHAND("JH"),
    KARNATAKA("KA"),
    KERALA("KL"),
    MADHYA_PRADESH("MP"),
    MAHARASHTRA("MH"),
    MANIPUR("MN"),
    MEGHALAYA("ML"),
    MIZORAM("MZ"),
    NAGALAND("NL"),
    ODISHA("OR"),
    PUNJAB("PB"),
    RAJASTHAN("RJ"),
    SIKKIM("SK"),
    TAMIL_NADU("TN"),
    TELANGANA("TG"),
    TRIPURA("TR"),
    UTTAR_PRADESH("UP"),
    UTTARAKHAND("UK"),
    WEST_BENGAL("WB"),

    // Union Territories
    ANDAMAN_AND_NICOBAR_ISLANDS("AN"),
    CHANDIGARH("CH"),
    DADRA_AND_NAGAR_HAVELI_AND_DAMAN_AND_DIU("DN"),
    DELHI("DL"),
    JAMMU_AND_KASHMIR("JK"),
    LADAKH("LA"),
    LAKSHADWEEP("LD"),
    PUDUCHERRY("PY");

    private final String shortCode;



    StateEnum(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getShortCode() {
        return shortCode;
    }

    // Method to get the enum by state name
    public static StateEnum getByName(String stateName) {
        for (StateEnum state : StateEnum.values()) {
            if (state.name().replace("_", " ").equalsIgnoreCase(stateName)) {
                return state;
            }
        }
        throw new IllegalArgumentException("No state found with name: " + stateName);
    }

    // Method to get the state short code by state name
    public static String getShortCodeByState(String stateName) {
        return getByName(stateName).getShortCode();
    }
}
