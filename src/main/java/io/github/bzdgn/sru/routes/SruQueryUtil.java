package io.github.bzdgn.sru.routes;

public class SruQueryUtil {

    private static final String QUERY_TEMPLATE = "c.product-area==%s and (w.organisatietype==%s" +
            " and ( w.documentstatus==%s or w.documentstatus==%s" +
            " or dt.type==%s )) or ( w.publicatienaam==%s )" +
            " and dt.date>=%s and dt.modified>=%s";

    public static String getQuery(String productArea, String organisatieType, String documentStatus1,
                            String documentStatus2, String dtType, String publicateNaam,
                            String dtDate, String dtModified) {
        return String.format(QUERY_TEMPLATE, productArea, organisatieType, documentStatus1,
                documentStatus2, dtType, publicateNaam, dtDate, dtModified);
    }

}
