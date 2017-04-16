package ru.sovcombank.frontoffice.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FrontSysReportPK implements Serializable {
    private String re_app_nam;
    private String re_grp_nam;
    private String re_rpt_nam;

    public FrontSysReportPK() {
    }

    public FrontSysReportPK(String re_app_nam, String re_grp_nam, String re_rpt_nam) {
        this.re_app_nam = re_app_nam;
        this.re_grp_nam = re_grp_nam;
        this.re_rpt_nam = re_rpt_nam;
    }
}
