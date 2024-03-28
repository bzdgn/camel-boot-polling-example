package io.github.bzdgn.sru.routes.process;

import io.github.bzdgn.sru.controller.DocumentController;
import io.github.bzdgn.sru.routes.SruQueryUtil;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.github.bzdgn.sru.general.Constants.*;
import static io.github.bzdgn.sru.routes.SruRoute.*;

public class PrepHTTPProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrepHTTPProcessor.class);

    private static final String C_PRODUCT_AREA = "\"officielepublicaties\"";
    private static final String W_ORGANIZATIONTYPE = "\"staten generaal\"";
    private static final String W_DOCUMENTSTATUS_1 = "\"Opgemaakt\"";
    private static final String W_DOCUMENTSTATUS_2 = "\"Opgemaakt na onopgemaakt\"";
    private static final String DT_TYPE = "\"Bijlage\"";
    private static final String W_PUBLICATIENAAM = "\"Staatsblad\"";

    @Override
    public void process(Exchange exchange) throws Exception {
        prepare(exchange);
        String host = exchange.getIn().getHeader(Exchange.HTTP_HOST, String.class);
        String baseUri = exchange.getIn().getHeader(Exchange.HTTP_BASE_URI, String.class);
        String path = exchange.getIn().getHeader(Exchange.HTTP_PATH, String.class);
        String method = exchange.getIn().getHeader(Exchange.HTTP_METHOD, String.class);
        String query = exchange.getIn().getHeader(Exchange.HTTP_QUERY, String.class);

        String template = "Host: %s\nBaseUri: %s\nPath: %s\nMethod: %s\nQuery: %s\n";

        LOGGER.info("Query: {}", template.formatted(host, baseUri, path, method, query));
    }

    private void prepare(Exchange exchange) {
        Integer startRecord = exchange.getIn().getHeader(START_RECORD_HEADER, Integer.class);

        if (startRecord == null) {
            startRecord = Integer.valueOf(1);
            exchange.getIn().setHeader(START_RECORD_HEADER, Integer.valueOf(1));
        }

        String operation = exchange.getIn().getHeader("operation", String.class);
        String version = exchange.getIn().getHeader("version", String.class);
        String recordSchema = exchange.getIn().getHeader("recordSchema", String.class);

        String dtDate = exchange.getIn().getHeader(DT_DATE_HEADER, String.class);
        String dtModified = exchange.getIn().getHeader(DT_MODIFIED_HEADER, String.class);
        String query = getSruRouteQuery(dtDate, dtModified);

        String httpQuery = String.format(HTTP_QUERY_TEMPLATE, operation, version, recordSchema, query, startRecord);

//        exchange.getIn().setHeader(Exchange.HTTP_BASE_URI, SRU_BASE);
        exchange.getIn().setHeader(Exchange.HTTP_HOST, SRU_HOST);
        exchange.getIn().setHeader(Exchange.HTTP_PATH, SRU_PATH);

        exchange.getIn().setHeader(Exchange.HTTP_QUERY, httpQuery);
    }

    private static String getSruRouteQuery(String dtDate, String dtModified) {
        return SruQueryUtil.getQuery(C_PRODUCT_AREA, W_ORGANIZATIONTYPE, W_DOCUMENTSTATUS_1,
                W_DOCUMENTSTATUS_2, DT_TYPE, W_PUBLICATIENAAM, dtDate, dtModified);
    }

}
