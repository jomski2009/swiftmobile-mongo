package org.imanmobile.sms.core.domain;

import java.util.List;

/**
 * Created by jome on 2014/01/29.
 */
public class SmsResponseWrapper {
    private List<SmsResponse> results;

    public List<SmsResponse> getResults() {
        return results;
    }

    public void setResults(List<SmsResponse> results) {
        this.results = results;
    }
}
