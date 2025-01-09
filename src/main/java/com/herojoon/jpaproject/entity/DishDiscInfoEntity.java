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
@Table(name = "DISH_DISC_INFO")
public class DishDiscInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LAST_DSH_DT")
    private String lastDshDt;

    @Column(name = "DSH_RSN_CTT")
    private String dshRsnCtt;

    @Column(name = "DSH_OCCR_BNK_NM")
    private String dshOccrBnkNm;

    @Column(name = "IVS_REF_CTT")
    private String ivsRefCtt;

    @Column(name = "DSH_CTT")
    private String dshCtt;

    @Column(name = "BAS_DT")
    private String basDt;

    @Column(name = "DSH_AMT")
    private String dshAmt;

    @Column(name = "CRNO")
    private String crno;

    @Column(name = "RPT_FILE_CTT")
    @Lob
    private String rptFileCtt;

}
