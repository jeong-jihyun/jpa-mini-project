package com.herojoon.jpaproject.service;

import com.herojoon.jpaproject.entity.LockUpRetuInfoJPO;
import com.herojoon.jpaproject.entity.StocIssuInfoJPO;
import com.herojoon.jpaproject.repository.LockUpRetuInfoRepository;
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
public class StocInfoService {
    private final StocIssuInfoRepository stocIssuInfoRepository;
    private final LockUpRetuInfoRepository lockUpRetuInfoRepository;

    public static final String USER_AGENT = "Mozilla/5.0";
    @Value("${api.service.key}")
    private String serviceKey;
    @Value("${api.service.url}")
    private String serviceUrl;
    /**
     * 1.금융위원회_주식발행정보 (주식발행내역조회)
     * 기준일자, 법인등록번호, 주식발행회사명을 통하여 주식발행일자, 주식발행차수, 주식발행사유코드등을 조회하는 주식발행내역조회 기능
     * @throws IOException IOException
     */
    public void StocIssuInfo() throws IOException {
        int pageNo = 1;
        int numOfRows = 2000;
        int totalCount = 0;

        do {
            String urlStr = serviceUrl + "/1160100/service/GetStocIssuInfoService_V2/getStocIssuInfo_V2?serviceKey=" + serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&resultType=xml&basDt=";
            URL url = new URL(urlStr);
            log.info("StocIssuInfo urlStr:{}", urlStr);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("CONTENT-TYPE", "text/xml");
            con.setDoOutput(true);
            con.setConnectTimeout(10000);
            con.setReadTimeout(5000);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String inputline;
                while ((inputline = in.readLine()) != null) {
                    response.append(inputline.trim());
                }

                log.info("StocIssuInfo totalCount:{}, pageNo: {}, pageSize:{}", totalCount, pageNo, Math.ceil((double) totalCount / numOfRows));
                if (pageNo == 1) {
                    totalCount = BatchUtil.getTotalCount(response.toString());
                }
                if (totalCount == (int) stocIssuInfoRepository.count()) {
                    break;
                }else{
                    this.StockInfoProcessResponse(response.toString());
                }
            } catch (IOException ex) {
                log.error("Error occurred while calling API", ex);
                throw ex;
            } catch (ParserConfigurationException | SAXException e) {
                throw new RuntimeException(e);
            } finally {
                con.disconnect();
            }
            pageNo++;
        } while (pageNo <= Math.ceil((double) totalCount / numOfRows));
    }

    /**
     * 1. 금융위원회_주식발행정보 (주식발행내역조회) - API 응답 처리
     * @param responseBody responseBody
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException SAXException
     * @throws IOException IOException
     */
    private void StockInfoProcessResponse(String responseBody) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(responseBody)));
        document.getDocumentElement().normalize();
        NodeList childList = document.getElementsByTagName("item");
        for (int i = 0; i < childList.getLength(); i++) {
            Node item = childList.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) item;
                stocIssuInfoRepository.save(StocIssuInfoJPO.builder()
                        .basDt(BatchUtil.getTagValue("basDt", element))
                        .crno(BatchUtil.getTagValue("crno", element))
                        .isinCd(BatchUtil.getTagValue("isinCd", element))
                        .isinCdNm(BatchUtil.getTagValue("isinCdNm", element))
                        .issuStckCnt(BatchUtil.getTagValue("issuStckCnt", element))
                        .lstgDt(BatchUtil.getTagValue("lstgDt", element))
                        .scrsDcd(BatchUtil.getTagValue("scrsDcd", element))
                        .scrsItmsKcd(BatchUtil.getTagValue("scrsItmsKcd", element))
                        .scrsItmsKcdNm(BatchUtil.getTagValue("scrsItmsKcdNm", element))
                        .stckIssuCmpyNm(BatchUtil.getTagValue("stckIssuCmpyNm", element))
                        .stckIssuDcnt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("stckIssuDcnt", element))))
                        .stckIssuDt(BatchUtil.getTagValue("stckIssuDt", element))
                        .stckIssuRcd(BatchUtil.getTagValue("stckIssuRcd", element))
                        .stckIssuRcdNm(BatchUtil.getTagValue("stckIssuRcdNm", element))
                        .stckIssuSqno(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("stckIssuSqno", element))))
                        .build());
            } else {
                log.info("Empty node found.");
            }
        }
    }

    /**
     * 2.금융위원회_의무보호예수반환정보 (의무보호예수반환내역조회)
     * @throws IOException IOException
     */
    public void LockUpRetuInfo() throws IOException {
        int pageNo = 1;
        int numOfRows = 2000;
        int totalCount = 0;

        do {
            String urlStr = serviceUrl + "/1160100/service/GetStocIssuInfoService_V2/getLockUpRetuInfo_V2?serviceKey=" + serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&resultType=xml&basDt=";
            URL url = new URL(urlStr);
            log.info("LockUpRetuInfo urlStr:{}", urlStr);

            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("CONTENT-TYPE", "text/xml");
            con.setDoOutput(true);
            con.setConnectTimeout(10000);
            con.setReadTimeout(5000);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String inputline;
                while ((inputline = in.readLine()) != null) {
                    response.append(inputline.trim());
                }

                log.info("LockUpRetuInfo totalCount:{}, pageNo: {}, pageSize:{}", totalCount, pageNo, Math.ceil((double) totalCount / numOfRows));
                if (pageNo == 1) {
                    totalCount = BatchUtil.getTotalCount(response.toString());
                }
                if (totalCount == (int) lockUpRetuInfoRepository.count()) {
                    break;
                }else{
                    this.LockUpRetuInfoProcessResponse(response.toString());
                }
            } catch (IOException ex) {
                log.error("Error occurred while calling API", ex);
                throw ex;
            } catch (ParserConfigurationException | SAXException e) {
                throw new RuntimeException(e);
            } finally {
                con.disconnect();
            }
            pageNo++;
        } while (pageNo <= Math.ceil((double) totalCount / numOfRows));
    }

    /**
     * 2.금융위원회_의무보호예수반환정보 (의무보호예수반환내역조회) - API 응답 처리
     * @param responseBody responseBody
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException SAXException
     * @throws IOException IOException
     */
    private void LockUpRetuInfoProcessResponse(String responseBody) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(responseBody)));
        document.getDocumentElement().normalize();
        NodeList childList = document.getElementsByTagName("item");
        for (int i = 0; i < childList.getLength(); i++) {
            Node item = childList.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) item;
                lockUpRetuInfoRepository.save(LockUpRetuInfoJPO.builder()
                        .afrsRsqtCnt(BatchUtil.getTagValue("afrsRsqtCnt", element))
                        .basDt(BatchUtil.getTagValue("basDt", element))
                        .crno(BatchUtil.getTagValue("crno", element))
                        .isinCd(BatchUtil.getTagValue("isinCd", element))
                        .isinCdNm(BatchUtil.getTagValue("isinCdNm", element))
                        .itmsShrtnCd(BatchUtil.getTagValue("itmsShrtnCd", element))
                        .lblProtTsumIssuStckCnt(BatchUtil.getTagValue("lblProtTsumIssuStckCnt", element))
                        .lkupRegDt(BatchUtil.getTagValue("lkupRegDt", element))
                        .lkupRegStckCnt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("lkupRegStckCnt", element))))
                        .lstgDcd(BatchUtil.getTagValue("lstgDcd", element))
                        .lstgDcdNm(BatchUtil.getTagValue("lstgDcdNm", element))
                        .rsrnDt(BatchUtil.getTagValue("rsrnDt", element))
                        .rsrnStckCnt(BatchUtil.getTagValue("rsrnStckCnt", element))
                        .scrsItmsKcd(BatchUtil.getTagValue("scrsItmsKcd", element))
                        .scrsItmsKcdNm(BatchUtil.getTagValue("scrsItmsKcdNm", element))
                        .stckIssuCmpyNm(BatchUtil.getTagValue("stckIssuCmpyNm", element))
                        .stckLblHoldRcd(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("stckLblHoldRcd", element))))
                        .stckLblHoldRcdNm(BatchUtil.getTagValue("stckLblHoldRcdNm", element))
                        .build());
            } else {
                log.info("Empty node found.");
            }
        }

    }
}
