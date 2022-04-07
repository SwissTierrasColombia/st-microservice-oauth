package com.ai.st.microservice.oauth.services.tracing;

import com.newrelic.api.agent.NewRelic;

public class SCMTracing {

    public static void setTransactionName(String transactionName) {
        NewRelic.setTransactionName("SCM", transactionName);
    }

    public static void sendError(String message) {
        NewRelic.noticeError(message);
    }

    public static void addCustomParameter(TracingKeyword key, Number value) {
        NewRelic.addCustomParameter(key.getValue(), value);
    }

    public static void addCustomParameter(TracingKeyword key, String value) {
        NewRelic.addCustomParameter(key.getValue(), value);
    }

    public static void addCustomParameter(TracingKeyword key, boolean value) {
        NewRelic.addCustomParameter(key.getValue(), value);
    }

}
