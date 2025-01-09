package com.herojoon.jpaproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataGoKrService {
    private final StocInfoService stocInfoService;
    private final DiscInfoService discInfoService;

    /**
     * API 호출 및 처리
     * @throws IOException IOException
     */
    public void callAndProcessApi() throws IOException{
        // 배당공시정보조회
        stocInfoService.StocIssuInfo();
        // 의무보호예수반환내역조회
        stocInfoService.LockUpRetuInfo();

        // 유상증자결정공시정보조회
        discInfoService.CapiIncrWithConsDiscInfo();
        // 배당공시정보조회
        discInfoService.DiviDiscInfo();
        // 무상증자결정공시정보조회
        discInfoService.BonuIssuDiscInfo();
        // 유무상증자결정공시정보조회
        discInfoService.CapiIncrWithConsBonuIssuDiscInfo();
        // 주주총회소집공고공시정보조회
        discInfoService.GeneMeetStocPublNotiDiscInfo();
        // 자산양수도(기타)_풋백옵션공시정보조회
        discInfoService.AsseTranPutBackOptiDiscInfo();

        discInfoService.DishDiscInfo();

    }
}
