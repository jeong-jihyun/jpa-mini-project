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
@Table(name = "OFFS_SECU_MARK_LIST_DISC_INFO")
public class OffsSecuMarkListDiscInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ARAS_BSIS_CTT", length=4000)
    private String arasBsisCtt;

    @Column(name = "BAS_DT")
    private String basDt;

    @Column(name = "CRNO")
    private String crno;

    @Column(name = "DR_ISIN_CD_NM")
    private String drIsinCdNm;

    @Column(name = "ENP_CORP_NM")
    private String enpCorpNm;

    @Column(name = "ENP_OVSE_XCHG_LSTG_DT")
    private String enpOvseXchgLstgDt;

    @Column(name = "ETC_CTT", length=4000)
    private String etcCtt;

    @Column(name = "IVS_REF_CTT", length=4000)
    private String ivsRefCtt;

    @Column(name = "LSTG_ONSK_CNT")
    private String lstgOnskCnt;

    @Column(name = "LSTG_OTSH_CNT")
    private int lstgOtshCnt;

    @Column(name = "LSTG_XCHG_DWPL_NTNL_NM")
    private String lstgXchgDwplNtnlNm;

    @Column(name = "LWST_AFRM_DT")
    private String lwstAfrmDt;

    @Column(name = "RPT_FILE_CTT", length=4000)
    private String rptFileCtt;

    @Column(name = "STCK_CTT", length=4000)
    private String stckCtt;

}
