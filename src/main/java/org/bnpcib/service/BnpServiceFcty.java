package org.bnpcib.service;

public enum BnpServiceFcty {
    INSTANCE;
    public static BnpServiceFcty get() {return INSTANCE;}

    private final GenerateSummaryService service = new GenerateSummaryServiceImpl();

    public GenerateSummaryService getService() {
        return service;
    }
}
