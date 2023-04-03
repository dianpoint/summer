package com.dianpoint.summer.test.event;

import com.dianpoint.summer.context.ApplicationEvent;
import com.dianpoint.summer.context.ApplicationListener;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author wangyi
 * @date 2023/3/30
 */
public class TestEventListener extends ApplicationListener {

    void onApplicationEvent(ApplicationEvent event) {
        String value = EventThreadLocal.getValue();
        String eventValue = event.getSource().toString();
        assertThat(value).isEqualTo(eventValue);
    }
}
