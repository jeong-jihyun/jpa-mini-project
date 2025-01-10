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
@Table(name = "OFFS_SECU_MARK_DELI_DISC_INFO")
public class OffsSecuMarkDeliDiscInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ARAS_BSIS_CTT", length=4000)
    private String arasBsisCtt;

    @Column(name = "BAS_DT")
    private String basDt;

    @Column(name = "CRNO")
    private String crno;

    @Column(name = "ETC_CTT", length=4000)
    private String etcCtt;

    @Column(name = "IVS_REF_CTT", length=4000)
    private String ivsRefCtt;

    @Column(name = "LSTG_ABOL_HCFH_SCED_CTT", length=4000)
    private String lstgAbolHcfhScedCtt;

    @Column(name = "LSTG_ABOL_ONSK_CNT")
    private int lstgAbolOnskCnt;

    @Column(name = "LSTG_ABOL_OTSH_CNT")
    private int lstgAbolOtshCnt;

    @Column(name = "LSTG_ABOL_RSN_CTT", length=4000)
    private String lstgAbolRsnCtt;

    @Column(name = "LSTG_XCHG_DWPL_NTNL_NM")
    private String lstgXchgDwplNtnlNm;

    @Column(name = "LWST_AFRM_DT")
    private String lwstAfrmDt;

    @Column(name = "RPT_FILE_CTT", length=4000)
    private String rptFileCtt;

    @Column(name = "STCK_CTT", length=4000)
    private String stckCtt;

    @Column(name = "TR_ED_DT")
    private String trEdDt;

}
