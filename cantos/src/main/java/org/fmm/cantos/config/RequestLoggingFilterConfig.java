package org.fmm.cantos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingFilterConfig {

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter
          = new CommonsRequestLoggingFilter();
        
        filter.setIncludeQueryString(true);
        filter.setIncludeClientInfo(false);
        filter.setIncludePayload(true);
        filter.setIncludeHeaders(true);

        filter.setMaxPayloadLength(10000);
        filter.setAfterMessagePrefix("[FMMP] REQUEST DATA: ");
        return filter;
    }

}
