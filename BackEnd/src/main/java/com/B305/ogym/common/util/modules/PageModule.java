package com.B305.ogym.common.util.modules;

import com.B305.ogym.common.util.PageDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.data.domain.Page;

public class PageModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public PageModule() {
        addDeserializer(Page.class, new PageDeserializer());
    }
}
