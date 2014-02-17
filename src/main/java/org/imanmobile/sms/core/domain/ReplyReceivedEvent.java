package org.imanmobile.sms.core.domain;

import org.springframework.context.ApplicationEvent;

/**
 * Created by jome on 2014/02/06.
 */
public class ReplyReceivedEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public ReplyReceivedEvent(Object source) {
        super(source);
    }

    public String message() {
        return "My event message";
    }
}
