package com.herojoon.jpaproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "DIVI_DISC_INFO")
public class DiviDiscInfoJPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "basDt")
    private String basDt;

    @Column(name = "bpvtrCashDvdnTndnCtt", length = 4000)
    private String bpvtrCashDvdnTndnCtt;

    @Column(name = "bpvtrCashTdvdAmt")
    private String bpvtrCashTdvdAmt;

    @Column(name = "bpvtrIdvCrtmNpf")
    private String bpvtrIdvCrtmNpf;

    @Column(name = "bpvtrOnskCashDvdnAmt")
    private String bpvtrOnskCashDvdnAmt;

    @Column(name = "bpvtrOnskCashDvdnBnfRt")
    private String bpvtrOnskCashDvdnBnfRt;

    @Column(name = "bpvtrOnskStckDvdnAmt")
    private String bpvtrOnskStckDvdnAmt;

    @Column(name = "bpvtrOnskStckDvdnBnfRt")
    private String bpvtrOnskStckDvdnBnfRt;

    @Column(name = "bpvtrParPrc")
    private String bpvtrParPrc;

    @Column(name = "bpvtrPfstCashDvdnAmt")
    private String bpvtrPfstCashDvdnAmt;

    @Column(name = "bpvtrPfstCashDvdnBnfRt")
    private String bpvtrPfstCashDvdnBnfRt;

    @Column(name = "bpvtrPfstStckDvdnAmt")
    private String bpvtrPfstStckDvdnAmt;

    @Column(name = "bpvtrPfstStckDvdnBnfRt")
    private String bpvtrPfstStckDvdnBnfRt;

    @Column(name = "bpvtrPstcNpf")
    private String bpvtrPstcNpf;

    @Column(name = "bpvtrStckTdvdAmt")
    private String bpvtrStckTdvdAmt;

    @Column(name = "crno")
    private String crno;

    @Column(name = "crtmCashDvdnTndnCtt", length = 4000)
    private String crtmCashDvdnTndnCtt;

    @Column(name = "crtmCashTdvdAmt")
    private String crtmCashTdvdAmt;

    @Column(name = "crtmIdvCrtmNpf")
    private String crtmIdvCrtmNpf;

    @Column(name = "crtmOnskCashDvdnAmt")
    private String crtmOnskCashDvdnAmt;

    @Column(name = "crtmOnskCashDvdnBnfRt")
    private String crtmOnskCashDvdnBnfRt;

    @Column(name = "crtmOnskStckDvdnAmt")
    private String crtmOnskStckDvdnAmt;

    @Column(name = "crtmOnskStckDvdnBnfRt")
    private String crtmOnskStckDvdnBnfRt;

    @Column(name = "crtmParPrc")
    private String crtmParPrc;

    @Column(name = "crtmPfstCashDvdnAmt")
    private String crtmPfstCashDvdnAmt;

    @Column(name = "crtmPfstCashDvdnBnfRt")
    private String crtmPfstCashDvdnBnfRt;

    @Column(name = "crtmPfstStckDvdnAmt")
    private String crtmPfstStckDvdnAmt;

    @Column(name = "crtmPfstStckDvdnBnfRt")
    private String crtmPfstStckDvdnBnfRt;

    @Column(name = "crtmPstcNpf")
    private String crtmPstcNpf;

    @Column(name = "crtmStckTdvdAmt")
    private String crtmStckTdvdAmt;

    @Column(name = "enpCrtmNpf")
    private String enpCrtmNpf;

    @Column(name = "fnclCrtmNpf")
    private String fnclCrtmNpf;

    @Column(name = "pvtrCashDvdnTndnCtt", length = 4000)
    private String pvtrCashDvdnTndnCtt;

    @Column(name = "pvtrCashTdvdAmt")
    private String pvtrCashTdvdAmt;

    @Column(name = "pvtrCrtmNpf")
    private String pvtrCrtmNpf;

    @Column(name = "pvtrIdvCrtmNpf")
    private String pvtrIdvCrtmNpf;

    @Column(name = "pvtrOnskCashDvdnAmt")
    private String pvtrOnskCashDvdnAmt;

    @Column(name = "pvtrOnskCashDvdnBnfRt")
    private String pvtrOnskCashDvdnBnfRt;

    @Column(name = "pvtrOnskStckDvdnAmt")
    private String pvtrOnskStckDvdnAmt;

    @Column(name = "pvtrOnskStckDvdnBnfRt")
    private String pvtrOnskStckDvdnBnfRt;

    @Column(name = "pvtrParPrc")
    private String pvtrParPrc;

    @Column(name = "pvtrPfstCashDvdnAmt")
    private String pvtrPfstCashDvdnAmt;

    @Column(name = "pvtrPfstCashDvdnBnfRt")
    private String pvtrPfstCashDvdnBnfRt;

    @Column(name = "pvtrPfstStckDvdnAmt")
    private String pvtrPfstStckDvdnAmt;

    @Column(name = "pvtrPfstStckDvdnBnfRt")
    private String pvtrPfstStckDvdnBnfRt;

    @Column(name = "pvtrPstcNpf")
    private String pvtrPstcNpf;

    @Column(name = "pvtrStckTdvdAmt")
    private String pvtrStckTdvdAmt;

    @Column(name = "rptFileCtt", length = 4000)
    private String rptFileCtt;
}
