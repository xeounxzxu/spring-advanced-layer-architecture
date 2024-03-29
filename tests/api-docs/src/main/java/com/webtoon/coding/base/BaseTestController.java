package com.webtoon.coding.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@Tag("restdocs")
@ExtendWith(value = { SpringExtension.class, RestDocumentationExtension.class })
@Import(RestDocsTestConfiguration.class)
public abstract class BaseTestController {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected RestDocumentationResultHandler restDocs;

    @BeforeEach
    void setUp(final WebApplicationContext context, final RestDocumentationContextProvider provider) {

        this.mockMvc = webAppContextSetup(context)
            .apply(documentationConfiguration(provider).uris().withScheme("http").withHost("localhost").withPort(8080))
            .alwaysDo(print())
            .alwaysDo(restDocs)
            .build();
    }

}
