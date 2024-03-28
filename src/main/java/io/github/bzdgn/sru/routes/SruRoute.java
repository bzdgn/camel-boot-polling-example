package io.github.bzdgn.sru.routes;

//import io.github.bzdgn.oep.routes.process.DBProcessor;
import io.github.bzdgn.sru.routes.process.DbProcessor;
import io.github.bzdgn.sru.routes.process.FileMetaInitProcessor;
import io.github.bzdgn.sru.routes.process.FileMetaMetaDataProcessor;
import io.github.bzdgn.sru.routes.process.PrepHTTPProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.language.xpath.XPathBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.github.bzdgn.sru.general.Constants.*;

@Component("SruRoute")
public class SruRoute extends RouteBuilder {

    @Autowired
    private DbProcessor dbProcessor;

    public static final String SRU_HOST = "https://repository.overheid.nl";
    public static final String SRU_PATH = "/sru";
    public static final String SRU_CONFIG_OPERATION = "searchRetrieve";
    public static final String SRU_CONFIG_VERSION = "2.0";
    public static final String SRU_CONFIG_RECORD_SCHEMA = "gzd";

    public SruRoute() {}

    private static final XPathBuilder xPathBuilder = XPathBuilder.xpath("//sru:record")
            .namespaces(SRU_RESPONSE_NS)
//            .logNamespaces()
            ;

    @Override
    public void configure() throws Exception {
        //@formatter:off
        from("direct:sru-main-route")
                .routeId("sru-main-route")
//                .throttle(1)
                .removeHeaders("*")
                .log("Starting SRU Route")
                .setHeader("operation", simple(SRU_CONFIG_OPERATION, String.class))
                .setHeader("version", simple(SRU_CONFIG_VERSION, String.class))
                .setHeader("recordSchema", simple(SRU_CONFIG_RECORD_SCHEMA, String.class))
                .loopDoWhile(header(START_RECORD_HEADER).isNotEqualTo(0))
                    .process(new PrepHTTPProcessor())
                    .to(SRU_HOST)
                    .choice()
                        .when(simple("${in.headers." + NUMBER_OF_RECORDS_HEADER + "} == null", String.class))
                            .setHeader(NUMBER_OF_RECORDS_HEADER, xpath("//sru:numberOfRecords/text()", Integer.class, SRU_RESPONSE_NS))
                        .otherwise()
                    .end()
                    .log("Start Record Header: ${in.headers." + START_RECORD_HEADER + "} / ${in.headers." + NUMBER_OF_RECORDS_HEADER + "}")
                    .log("==============================")
                    .split(xPathBuilder)
                        .setHeader(SRU_RECORD_POSITION_HEADER).xpath("//sru:recordPosition/text()", Integer.class, SRU_RESPONSE_NS)
                        .log("Record Position Header: ${in.headers." + SRU_RECORD_POSITION_HEADER + "} / ${in.headers." + NUMBER_OF_RECORDS_HEADER + "}")
                        .to("direct:sru-record-route")
                    .end()
                    .setHeader(START_RECORD_HEADER).xpath("//sru:nextRecordPosition/text()", Integer.class, SRU_RESPONSE_NS)
                .end();
        //@formatter:on

        //@formatter:off
        from("direct:sru-record-route")
                .routeId("sru-record-route")
//                .throttle(1)
                // fullfill the FileMetaData headers
                .setHeader(DOC_IDENTIFIER_HEADER, xpath("//dcterms:identifier/text()", String.class, DCTERMS_NS))
                .setHeader(DOC_TITLE_HEADER, xpath("//dcterms:title/text()", String.class, DCTERMS_NS))
                .setHeader(DOC_PREFERRED_URL_HEADER, xpath("//gzd:enrichedData/gzd:preferredUrl/text()", String.class, GZD_NS))
                .setHeader(DOC_PDF_URL_HEADER, xpath("//gzd:enrichedData/gzd:itemUrl[@manifestation='pdf']/text()", String.class, GZD_NS))
                .process(new FileMetaInitProcessor())
                // now prepare to visit xml metadata url
                .removeHeader(Exchange.HTTP_PATH)
                .removeHeader(Exchange.HTTP_QUERY)
                .setHeader(Exchange.CONTENT_TYPE, constant("application/xml;charset=UTF-8"))
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader(Exchange.HTTP_URI, xpath("//gzd:enrichedData/gzd:itemUrl[@manifestation='metadata']/text()", String.class, GZD_NS))
                .toD("${in.headers." + Exchange.HTTP_URI + "}")
                .to("direct:sru-metadata-route");
        //@formatter:on

        //@formatter:off
        from("direct:sru-metadata-route")
                .routeId("sru-metadata-route")
//                .throttle(1)
                .convertBodyTo(String. class)
                .setHeader(DOC_META_DATA_HEADER, body())
                .process(new FileMetaMetaDataProcessor())
                .setBody(xpath("/metadata_gegevens/metadata[@name='DC.title']/@content", String.class))
                .log("FileMeta: ${in.headers." + FILE_META_HEADER + "}")
                .process(dbProcessor)
                .log("${body} saved to db");
        //@formatter:on
    }
}
