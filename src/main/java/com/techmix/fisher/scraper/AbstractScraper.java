package com.techmix.fisher.scraper;

import java.util.Collection;

/**
 * @author fisher
 * @since 4/17/16
 */
public abstract class AbstractScraper implements JsoupConnection, JsoupUtils {

    public abstract Collection getProducts();


}
