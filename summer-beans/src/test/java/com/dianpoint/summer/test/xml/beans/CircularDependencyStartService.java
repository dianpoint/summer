package com.dianpoint.summer.test.xml.beans;

/**
 * @author wangyi
 * @date 2023/3/26
 */
public class CircularDependencyStartService {

    private CircularDependencyEndService circularDependencyEndService;

    private String remark;

    public CircularDependencyStartService(final String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
    }

    public CircularDependencyEndService getCircularDependencyEndService() {
        return this.circularDependencyEndService;
    }

    public void setCircularDependencyEndService(final CircularDependencyEndService circularDependencyEndService) {
        this.circularDependencyEndService = circularDependencyEndService;
    }
}
