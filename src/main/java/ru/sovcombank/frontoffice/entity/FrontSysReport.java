package ru.sovcombank.frontoffice.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FRONT_SYS_REPORT", schema = "FRONTOFFICE")
@Access(AccessType.FIELD)
@IdClass(FrontSysReportPK.class)
public class FrontSysReport {
    @Id
    @Column(name = "RE_APP_NAM")
    private String re_app_nam;

    @Id
    @Column(name = "RE_GRP_NAM")
    private String re_grp_nam;

    @Id
    @Column(name= "RE_RPT_NAM")
    private String re_rpt_nam;

    @Column(name = "RE_RPT_FMT")
    private String re_rpt_fmt;

    @Column(name = "RE_RPT_PRN")
    private String re_rpt_prn;

    @Column(name = "RE_CREATED", insertable = false, updatable = false)
    private Date re_created;

    @Column(name = "RE_CHANGED")
    private Date re_changed;

    @Column(name = "RE_RPT_EXT")
    private String re_rpt_ext;


    @Column(name = "RE_RPT_SCR")
    private String re_rpt_scr;


    public FrontSysReport() {
    }

    public String getRe_rpt_ext() {
        return re_rpt_ext;
    }


    public String getRe_rpt_scr() {
        return re_rpt_scr;
    }


/*    public void setRe_rpt_scr(String re_rpt_scr) {
        this.re_rpt_scr = re_rpt_scr;
    }*/

    public void setRe_rpt_ext(String re_rpt_ext) {

        this.re_rpt_ext = re_rpt_ext;
    }

    public FrontSysReport(String re_app_nam, String re_grp_nam, String re_rpt_nam, String re_rpt_fmt, String re_rpt_prn, String re_rpt_ext) {
        this.re_app_nam = re_app_nam;
        this.re_grp_nam = re_grp_nam;
        this.re_rpt_nam = re_rpt_nam;
        this.re_rpt_fmt = re_rpt_fmt;
        this.re_rpt_prn = re_rpt_prn;
        this.re_changed = new Date();
        this.re_rpt_ext = re_rpt_ext;
    }

    public String getRe_app_nam() {
        return re_app_nam;
    }

    public String getRe_grp_nam() {
        return re_grp_nam;
    }

    public String getRe_rpt_nam() {
        return re_rpt_nam;
    }

    public String getRe_rpt_fmt() {
        return re_rpt_fmt;
    }

    public String getRe_rpt_prn() {
        return re_rpt_prn;
    }

    public Date getRe_created() {
        return re_created;
    }

    public Date getRe_changed() {
        return re_changed;
    }

    public void setRe_app_nam(String re_app_nam) {
        this.re_app_nam = re_app_nam;
    }

    public void setRe_grp_nam(String re_grp_nam) {
        this.re_grp_nam = re_grp_nam;
    }

    public void setRe_rpt_nam(String re_rpt_nam) {
        this.re_rpt_nam = re_rpt_nam;
    }

    public void setRe_rpt_fmt(String re_rpt_fmt) {
        this.re_rpt_fmt = re_rpt_fmt;
    }

    public void setRe_rpt_prn(String re_rpt_prn) {
        this.re_rpt_prn = re_rpt_prn;
    }

    public void setRe_created(Date re_created) {
        this.re_created = re_created;
    }

    public void setRe_changed(Date re_changed) {
        this.re_changed = re_changed;
    }
}
