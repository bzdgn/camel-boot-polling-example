package io.github.bzdgn.sru.routes.process;

import io.github.bzdgn.sru.dto.TriggerActionRequest;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import static io.github.bzdgn.sru.general.Constants.DT_DATE_HEADER;
import static io.github.bzdgn.sru.general.Constants.DT_MODIFIED_HEADER;

public class InitProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        TriggerActionRequest triggerActionRequest = exchange.getIn().getBody(TriggerActionRequest.class);

        exchange.getIn().setHeader(DT_DATE_HEADER, triggerActionRequest.getDtDate());
        exchange.getIn().setHeader(DT_MODIFIED_HEADER, triggerActionRequest.getDtModified());
    }

}
