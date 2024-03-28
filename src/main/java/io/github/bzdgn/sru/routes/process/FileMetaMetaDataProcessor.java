package io.github.bzdgn.sru.routes.process;


import io.github.bzdgn.sru.entity.FileMeta;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import static io.github.bzdgn.sru.general.Constants.*;

public class FileMetaMetaDataProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        FileMeta fileMeta = exchange.getIn().getHeader(FILE_META_HEADER, FileMeta.class);
        String docMetaData = exchange.getIn().getHeader(DOC_META_DATA_HEADER, String.class);

        fileMeta.setMetaData(docMetaData);

        exchange.getIn().setHeader(FILE_META_HEADER, fileMeta);
    }
}
