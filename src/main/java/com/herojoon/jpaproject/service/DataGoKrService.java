package com.herojoon.jpaproject.service;

import com.herojoon.jpaproject.entity.CapiIncrWithConsDiscInfoJPO;
import com.herojoon.jpaproject.entity.StocIssuInfoJPO;
import com.herojoon.jpaproject.repository.CapiIncrWithConsDiscInfoRepository;
import com.herojoon.jpaproject.repository.StocIssuInfoRepository;
import com.herojoon.jpaproject.util.BatchUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataGoKrService {
    private final StocInfoService stocInfoService;
    private final DiscInfoService discInfoService;

    public static final String USER_AGENT = "Mozilla/5.0";
    @Value("${api.service.key}")
    private String serviceKey;
    @Value("${api.service.url}")
    private String serviceUrl;

    /**
     * API 호출 및 처리
     * @throws IOException IOException
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException SAXException
     */
    public void callAndProcessApi() throws IOException, ParserConfigurationException, SAXException {
        // 배당공시정보조회
        stocInfoService.StocIssuInfo();

        // 유상증자결정공시정보조회
        discInfoService.CapiIncrWithConsDiscInfo();
    }
}
