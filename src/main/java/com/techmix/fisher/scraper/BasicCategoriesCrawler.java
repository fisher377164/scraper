package com.techmix.fisher.scraper;

import org.jsoup.nodes.Document;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fisher
 * @since 4/17/16
 */
public class BasicCategoriesCrawler implements JsoupConnection, JsoupUtils {

    public List<String> getCategories(String url, String selector) {
        if (url.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        final Document document = getDocument(url, true);
        return document.select(selector)
                .stream()
                .map(this::getLink)
                .collect(Collectors.toList());
    }
}
