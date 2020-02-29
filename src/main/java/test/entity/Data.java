package test.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author 张伟
 * @date 2019/9/2 23:02
 */
public class Data {
    private String ID;
    private String PRIPID;
    private String ENTNAME;
    private String LICENCE_TYPE;
    private Date   START_TIME;
    private Date   END_TIME;
    private String REGORG;
    private String REGORG_ID;
    private String STATUS;
    private Timestamp ADDTIME;
    private Date   UPTIME;
    private String ADDUSER;
    private String LICENCE_NUM;
    private String SIGENR;
    private Date   PUBLIC_DATE;
    private String ADDRESS;
    private String BUSINESS_ADDRESS;
    private String ADMINISTRATION;
    private String ADMIN_PERSON;
    private String UNISCID;
    private String CONTENT;



    public Data(
                String ENTNAME,
                String LICENCE_TYPE,
                Date   START_TIME,
                Date   END_TIME,
                String REGORG,
                String REGORG_ID,
                String STATUS,
                Timestamp   ADDTIME,
                String ADDUSER,
                String LICENCE_NUM,
                String SIGENR,
                Date   PUBLIC_DATE,
                String ADDRESS,
                String BUSINESS_ADDRESS,
                String ADMINISTRATION,
                String ADMIN_PERSON,
                String UNISCID,
                String CONTENT
    ){
        this.ENTNAME=ENTNAME;
        this.LICENCE_TYPE=LICENCE_TYPE;
        this.START_TIME=START_TIME;
        this.END_TIME=END_TIME;
        this.REGORG=REGORG;
        this.REGORG_ID=REGORG_ID;
        this.STATUS=STATUS;
        this.ADDTIME=ADDTIME;
        this.ADDUSER=ADDUSER;
        this.LICENCE_NUM=LICENCE_NUM;
        this.SIGENR=SIGENR;
        this.PUBLIC_DATE=PUBLIC_DATE;
        this.ADDRESS=ADDRESS;
        this.BUSINESS_ADDRESS=BUSINESS_ADDRESS;
        this.ADMINISTRATION=ADMINISTRATION;
        this.ADMIN_PERSON=ADMIN_PERSON;
        this.UNISCID=UNISCID;
        this.CONTENT=CONTENT;

    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPRIPID() {
        return PRIPID;
    }

    public void setPRIPID(String PRIPID) {
        this.PRIPID = PRIPID;
    }

    public String getENTNAME() {
        return ENTNAME;
    }

    public void setENTNAME(String ENTNAME) {
        this.ENTNAME = ENTNAME;
    }

    public String getLICENCE_TYPE() {
        return LICENCE_TYPE;
    }

    public void setLICENCE_TYPE(String LICENCE_TYPE) {
        this.LICENCE_TYPE = LICENCE_TYPE;
    }

    public Date getSTART_TIME() {
        return START_TIME;
    }

    public void setSTART_TIME(Date START_TIME) {
        this.START_TIME = START_TIME;
    }

    public Date getEND_TIME() {
        return END_TIME;
    }

    public void setEND_TIME(Date END_TIME) {
        this.END_TIME = END_TIME;
    }

    public String getREGORG() {
        return REGORG;
    }

    public void setREGORG(String REGORG) {
        this.REGORG = REGORG;
    }

    public String getREGORG_ID() {
        return REGORG_ID;
    }

    public void setREGORG_ID(String REGORG_ID) {
        this.REGORG_ID = REGORG_ID;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public Timestamp getADDTIME() {
        return ADDTIME;
    }

    public void setADDTIME(Timestamp ADDTIME) {
        this.ADDTIME = ADDTIME;
    }

    public Date getUPTIME() {
        return UPTIME;
    }

    public void setUPTIME(Date UPTIME) {
        this.UPTIME = UPTIME;
    }

    public String getADDUSER() {
        return ADDUSER;
    }

    public void setADDUSER(String ADDUSER) {
        this.ADDUSER = ADDUSER;
    }

    public String getLICENCE_NUM() {
        return LICENCE_NUM;
    }

    public void setLICENCE_NUM(String LICENCE_NUM) {
        this.LICENCE_NUM = LICENCE_NUM;
    }

    public String getSIGENR() {
        return SIGENR;
    }

    public void setSIGENR(String SIGENR) {
        this.SIGENR = SIGENR;
    }

    public Date getPUBLIC_DATE() {
        return PUBLIC_DATE;
    }

    public void setPUBLIC_DATE(Date PUBLIC_DATE) {
        this.PUBLIC_DATE = PUBLIC_DATE;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getBUSINESS_ADDRESS() {
        return BUSINESS_ADDRESS;
    }

    public void setBUSINESS_ADDRESS(String BUSINESS_ADDRESS) {
        this.BUSINESS_ADDRESS = BUSINESS_ADDRESS;
    }

    public String getADMINISTRATION() {
        return ADMINISTRATION;
    }

    public void setADMINISTRATION(String ADMINISTRATION) {
        this.ADMINISTRATION = ADMINISTRATION;
    }

    public String getADMIN_PERSON() {
        return ADMIN_PERSON;
    }

    public void setADMIN_PERSON(String ADMIN_PERSON) {
        this.ADMIN_PERSON = ADMIN_PERSON;
    }

    public String getUNISCID() { return UNISCID;}

    public void setUNISCID(String UNISCID) { this.UNISCID = UNISCID; }

    @Override
    public String toString() {
        return "Data{" +
                "ID='" + ID + '\'' +
                ", PRIPID='" + PRIPID + '\'' +
                ", ENTNAME='" + ENTNAME + '\'' +
                ", LICENCE_TYPE='" + LICENCE_TYPE + '\'' +
                ", START_TIME=" + START_TIME +
                ", END_TIME=" + END_TIME +
                ", REGORG='" + REGORG + '\'' +
                ", REGORG_ID='" + REGORG_ID + '\'' +
                ", STATUS='" + STATUS + '\'' +
                ", ADDTIME=" + ADDTIME +
                ", UPTIME=" + UPTIME +
                ", ADDUSER='" + ADDUSER + '\'' +
                ", LICENCE_NUM='" + LICENCE_NUM + '\'' +
                ", SIGENR='" + SIGENR + '\'' +
                ", PUBLIC_DATE=" + PUBLIC_DATE +
                ", ADDRESS='" + ADDRESS + '\'' +
                ", BUSINESS_ADDRESS='" + BUSINESS_ADDRESS + '\'' +
                ", ADMINISTRATION='" + ADMINISTRATION + '\'' +
                ", ADMIN_PERSON='" + ADMIN_PERSON + '\'' +
                ", UNISCID='" + UNISCID + '\'' +
                ", CONTENT='" + CONTENT + '\'' +
                '}';
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

}
