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
@Table(name = "DISS_REAS_DISC_INFO")
public class DissReasDiscInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AUDT_CMBR_ATND_YN")
    private String audtCmbrAtndYn;

    @Column(name = "BAS_DT")
    private String basDt;

    @Column(name = "CORP_DSON_CTT", length=4000)
    private String corpDsonCtt;

    @Column(name = "CORP_DSON_RSN_CTT", length=4000)
    private String corpDsonRsnCtt;

    @Column(name = "CORP_DSON_RSN_OCCR_DT")
    private String corpDsonRsnOccrDt;

    @Column(name = "CRNO")
    private String crno;

    @Column(name = "IVS_REF_CTT", length=4000)
    private String ivsRefCtt;

    @Column(name = "OTDR_ABNC_NOPE_CNT")
    private int otdrAbncNopeCnt;

    @Column(name = "OTDR_ATND_NOPE_CNT")
    private int otdrAtndNopeCnt;

    @Column(name = "RPT_FILE_CTT", length=4000)
    private String rptFileCtt;

}
