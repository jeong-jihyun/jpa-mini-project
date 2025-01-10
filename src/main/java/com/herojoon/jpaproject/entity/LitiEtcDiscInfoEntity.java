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
@Table(name = "LITI_ETC_DISC_INFO")
public class LitiEtcDiscInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BAS_DT")
    private String basDt;

    @Column(name = "CRNO")
    private String crno;

    @Column(name = "IVS_REF_CTT", length=4000)
    private String ivsRefCtt;

    @Column(name = "LWST_AFRM_DT")
    private String lwstAfrmDt;

    @Column(name = "LWST_APLPN_NM", length=4000, nullable = true)
    private String lwstAplpnNm;

    @Column(name = "LWST_CLM_CTT", length=4000)
    private String lwstClmCtt;

    @Column(name = "LWST_ICDT_NM")
    private String lwstIcdtNm;

    @Column(name = "LWST_JURD_CURT_NM")
    private String lwstJurdCurtNm;

    @Column(name = "LWST_RSLT_HCFH_CTPN_CTT", length=4000)
    private String lwstRsltHcfhCtpnCtt;

    @Column(name = "LWST_SGGS_DT")
    private String lwstSggsDt;

    @Column(name = "RPT_FILE_CTT", length=4000)
    private String rptFileCtt;

}
