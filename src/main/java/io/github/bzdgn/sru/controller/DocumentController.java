package io.github.bzdgn.sru.controller;

import io.github.bzdgn.sru.dto.TriggerActionRequest;
import io.github.bzdgn.sru.dto.TriggerActionResponse;
import io.github.bzdgn.sru.entity.FileMeta;
import io.github.bzdgn.sru.service.DocumentService;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.github.bzdgn.sru.general.Constants.*;

@RestController
@RequestMapping("/api/v1")
public class DocumentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private ProducerTemplate template;

    @Autowired
    private DocumentService documentService;

    @GetMapping("documents")
    public List<FileMeta> getAll() {
        return documentService.getAllFileMeta();
    }

    @PostMapping("poll")
    public TriggerActionResponse pollTrigger(@RequestBody TriggerActionRequest triggerActionRequest) {
        template.asyncSendBody("direct:sru-main-route", triggerActionRequest);

        StringBuilder sb = new StringBuilder();

        String dtDate = triggerActionRequest.getDtDate();
        if (dtDate == null || dtDate.isEmpty()) {
            triggerActionRequest.setDtDate(DT_DATE_DEFAULT);

            sb.append("DT_DATE cannot be empty, Default DT_DATE is used, ");
            LOGGER.info("Default DT_DATE is used: {}", triggerActionRequest.getDtDate());
        }

        String dtModified = triggerActionRequest.getDtDate();
        if (dtDate == null || dtDate.isEmpty()) {
            triggerActionRequest.setDtModified(DT_MODIFIED_DEFAULT);

            sb.append("DT_MODIFIED cannot be empty, Default DT_MODIFIED is used, ");
            LOGGER.info("Default DT_MODIFIED is used: {}", triggerActionRequest.getDtModified());
        }

        sb.append("TriggerAction : " + triggerActionRequest);

        String status = sb.toString();
        LOGGER.info("The main route is triggered: {}", status);

        return new TriggerActionResponse("Action is triggered. " + status);
    }

}
