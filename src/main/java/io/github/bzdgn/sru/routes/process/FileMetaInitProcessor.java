package io.github.bzdgn.sru.routes.process;


import io.github.bzdgn.sru.entity.FileMeta;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import static io.github.bzdgn.sru.general.Constants.*;

public class FileMetaInitProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
//        FileMeta fileMeta = exchange.getIn().getHeader(FILE_META_HEADER, FileMeta.class);

        String docIdentifier = exchange.getIn().getHeader(DOC_IDENTIFIER_HEADER, String.class);
        String docTitle = exchange.getIn().getHeader(DOC_TITLE_HEADER, String.class);
        String docPreferredUrl = exchange.getIn().getHeader(DOC_PREFERRED_URL_HEADER, String.class);
        String docPdfUrl = exchange.getIn().getHeader(DOC_PDF_URL_HEADER, String.class);
//        String docMetaData = exchange.getIn().getHeader(DOC_META_DATA_HEADER, String.class);

        FileMeta fileMeta = new FileMeta(docIdentifier, docTitle, docPreferredUrl, docPdfUrl);

        exchange.getIn().setHeader(FILE_META_HEADER, fileMeta);
    }
}
