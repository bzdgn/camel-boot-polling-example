package io.github.bzdgn.sru.general;

import org.apache.camel.support.builder.Namespaces;

public class Constants {

    // Headers
    public static final String FILE_META_HEADER = "FILE_META_HEADER";
    public static final String SRU_RECORD_POSITION_HEADER = "SRU_RECORD_POSITION_HEADER";
    public static final String START_RECORD_HEADER = "START_RECORD_HEADER";
    public static final String NUMBER_OF_RECORDS_HEADER = "NUMBER_OF_RECORDS_HEADER";
    public static final String DT_DATE_HEADER = "DT_DATE_HEADER";
    public static final String DT_MODIFIED_HEADER = "DT_MODIFIED_HEADER";


    public static final String DOC_IDENTIFIER_HEADER = "DOC_IDENTIFIER_HEADER";
    public static final String DOC_TITLE_HEADER = "DOC_TITLE_HEADER";
    public static final String DOC_PREFERRED_URL_HEADER = "DOC_PREFERRED_URL_HEADER";
    public static final String DOC_PDF_URL_HEADER = "DOC_PDF_URL_HEADER";
    public static final String DOC_META_DATA_HEADER = "DOC_META_DATA_HEADER";

    // defaults
    public static final String DT_DATE_DEFAULT = "\"2024-03-27\"";
    public static final String DT_MODIFIED_DEFAULT = "\"2024-03-27\"";

    // Namespaces
    public static final Namespaces SRU_RESPONSE_NS = new Namespaces("sru", "http://docs.oasis-open.org/ns/search-ws/sruResponse");
    public static final Namespaces GZD_NS = new Namespaces("gzd", "http://standaarden.overheid.nl/sru");
    public static final Namespaces DCTERMS_NS = new Namespaces("dcterms", "http://purl.org/dc/terms/");

    // Constants
    public static final String HTTP_QUERY_TEMPLATE = "?operation=%s&version=%s&recordSchema=%s&query=%s&startRecord=%s";



}
