package io.github.bzdgn.sru.routes.process;

import io.github.bzdgn.sru.entity.FileMeta;
import io.github.bzdgn.sru.repo.DocumentRepository;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.github.bzdgn.sru.general.Constants.FILE_META_HEADER;

@Component
public class DbProcessor implements Processor {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public void process(Exchange exchange) throws Exception {
        FileMeta fileMeta = exchange.getIn().getHeader(FILE_META_HEADER, FileMeta.class);

        documentRepository.saveAndFlush(fileMeta);
    }
}
