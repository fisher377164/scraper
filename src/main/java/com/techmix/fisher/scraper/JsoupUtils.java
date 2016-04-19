package com.techmix.fisher.scraper;

import org.jsoup.nodes.Element;

/**
 * @author fisher
 * @since 4/17/16
 */
public interface JsoupUtils {

    default String getLink(Element element) {
        if (element == null) {
            return "";
        }
        return element.attr("abs:href").trim();
    }

    default String getText(Element element) {
        if (element == null) {
            return "";
        }
        return element.text().trim();
    }
}
