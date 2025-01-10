package com.herojoon.jpaproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "OUTS_DIRE_HUMA_RESO_AFFA_REPO")
public class OutsDireHumaResoAffaRepoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BAS_DT")
    private String basDt;

    @Column(name = "CRNO")
    private String crno;

    @Column(name = "IVS_REF_CTT", length = 4000)
    private String ivsRefCtt;

    @Column(name = "LGSC_CORP_YN")
    private String lgscCorpYn;

    @Column(name = "OTDR_CRR_CTT", length = 4000)
    private String otdrCrrCtt;

    @Column(name = "OTDR_DSMS_DT", length = 4000)
    private String otdrDsmsDt;

    @Column(name = "OTDR_DSMS_RSN_CTT", length = 4000)
    private String otdrDsmsRsnCtt;

    @Column(name = "OTDR_FNM", length = 4000)
    private String otdrFnm;

    @Column(name = "OTDR_MNG_CTT", length = 4000)
    private String otdrMngCtt;

    @Column(name = "OTDR_NEW_DSGN_YN")
    private String otdrNewDsgnYn;

    @Column(name = "OTDR_NOW_OCPT_NM", length = 4000)
    private String otdrNowOcptNm;

    @Column(name = "OTDR_RTO")
    private String otdrRto;

    @Column(name = "OTDR_TRM_STTG_DT")
    private String otdrTrmSttgDt;

    @Column(name = "OTDR_TRM_XPRY_DT")
    private String otdrTrmXpryDt;

    @Column(name = "RPT_FILE_CTT", length = 4000)
    private String rptFileCtt;

    @Column(name = "TDRT_CNT")
    private int tdrtCnt;

    @Column(name = "TOTD_CNT")
    private int totdCnt;

}
