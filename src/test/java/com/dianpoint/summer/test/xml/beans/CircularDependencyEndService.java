package com.dianpoint.summer.test.xml.beans;

/**
 * @author wangyi
 * @date 2023/3/26
 */
public class CircularDependencyEndService {

    private CircularDependencyStartService circularDependencyStartService;

    private String remark;

    public CircularDependencyEndService(final String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
    }

    public CircularDependencyStartService getCircularDependencyStartService() {
        return this.circularDependencyStartService;
    }

    public void setCircularDependencyStartService(final CircularDependencyStartService circularDependencyStartService) {
        this.circularDependencyStartService = circularDependencyStartService;
    }
}
