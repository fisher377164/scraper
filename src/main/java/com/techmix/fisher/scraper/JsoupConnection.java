package com.techmix.fisher.scraper;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;
import java.util.Objects;

/**
 * @author fisher
 * @since 4/17/16
 */

public interface JsoupConnection {

    default String getResponse(String url) {
        try {
            System.out.println("Fetching: " + url);
            return Jsoup.connect(url)
                    .maxBodySize(0)
                    .timeout(0)
                    .get().body().text();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    default Document getDocument(String url) {
        try {
            System.out.println("Fetching: " + url);
            return Jsoup.connect(url)
                    .maxBodySize(0)
                    .header("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:43.0) Gecko/20100101 Firefox/43.0")
                    .followRedirects(true)
                    .timeout(0)
                    .get();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Jsoup.parse("");
        }
    }

    default Document getDocument(String url, boolean ignoreErrors) {
        try {
            System.out.println("Fetching: " + url);
            return Jsoup.connect(url)
                    .maxBodySize(0)
                    .timeout(0)
                    .header("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:43.0) Gecko/20100101 Firefox/43.0")
                    .followRedirects(true)
                    .ignoreContentType(ignoreErrors)
                    .ignoreHttpErrors(ignoreErrors)
                    .get();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Jsoup.parse("");
        }
    }

    default Document getDocument(String url, Map<String, String> headers) {
        try {
            System.out.println("Fetching: " + url);
            final Connection connect = Jsoup.connect(url);
            for (String header : headers.keySet()) {
                connect.header(header, headers.get(header));
            }
            return connect.maxBodySize(0)
                    .timeout(0)
                    .get();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Jsoup.parse("");
        }
    }

    default Document parseToDocument(String content) {
        if (Objects.isNull(content)) {
            return Jsoup.parse("");
        }
        return Jsoup.parse(content);
    }

}
