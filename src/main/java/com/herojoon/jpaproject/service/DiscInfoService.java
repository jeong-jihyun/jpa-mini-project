package com.herojoon.jpaproject.service;

import com.herojoon.jpaproject.entity.CapiIncrWithConsDiscInfoJPO;
import com.herojoon.jpaproject.entity.DiviDiscInfoJPO;
import com.herojoon.jpaproject.repository.CapiIncrWithConsDiscInfoRepository;
import com.herojoon.jpaproject.repository.DiviDiscInfoRepository;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscInfoService {
    private final CapiIncrWithConsDiscInfoRepository capiIncrWithConsDiscInfoRepository;
    private final DiviDiscInfoRepository diviDiscInfoRepository;
    public static final String USER_AGENT = "Mozilla/5.0";
    @Value("${api.service.key}")
    private String serviceKey;
    @Value("${api.service.url}")
    private String serviceUrl;
    /**
     * 1.금융위원회_공시정보 (배당공시정보조회)
     * @throws IOException IOException
     */
    public void DiviDiscInfo() throws IOException {
        int pageNo = 1;
        int numOfRows = 2000;
        int totalCount = 0;

        do {
            String urlStr = serviceUrl + "/1160100/service/GetDiscInfoService_V2/getDiviDiscInfo_V2?serviceKey=" + serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&resultType=xml";
            URL url = new URL(urlStr);

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
                log.info("DiviDiscInfo totalCount:{}, pageNo: {}, pageSize:{}", totalCount, pageNo, Math.ceil((double) totalCount / numOfRows));
                if (pageNo == 1) {
                    totalCount = BatchUtil.getTotalCount(response.toString());
                }
                if (totalCount == (int) diviDiscInfoRepository.count()) {
                    break;
                }else{
                    this.DiviDiscInfoProcessResponse(response.toString());
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
     * 1.금융위원회_공시정보 (배당공시정보조회) - API 응답 처리
     * @param responseBody responseBody
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException SAXException
     * @throws IOException IOException
     */
    private void DiviDiscInfoProcessResponse(String responseBody) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(responseBody)));
        document.getDocumentElement().normalize();
        NodeList childList = document.getElementsByTagName("item");
        for (int i = 0; i < childList.getLength(); i++) {
            Node item = childList.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) item;
                // DiscInfoJPO의 값을 매칭하여 아래 코드를 작성하세요.
                diviDiscInfoRepository.save(DiviDiscInfoJPO.builder()
                        .basDt(BatchUtil.getTagValue("basDt", element))
                        .bpvtrCashDvdnTndnCtt(BatchUtil.getTagValue("bpvtrCashDvdnTndnCtt", element))
                        .bpvtrCashTdvdAmt(BatchUtil.getTagValue("bpvtrCashTdvdAmt", element))
                        .bpvtrIdvCrtmNpf(BatchUtil.getTagValue("bpvtrIdvCrtmNpf", element))
                        .bpvtrOnskCashDvdnAmt(BatchUtil.getTagValue("bpvtrOnskCashDvdnAmt", element))
                        .bpvtrOnskCashDvdnBnfRt(BatchUtil.getTagValue("bpvtrOnskCashDvdnBnfRt", element))
                        .bpvtrOnskStckDvdnAmt(BatchUtil.getTagValue("bpvtrOnskStckDvdnAmt", element))
                        .bpvtrOnskStckDvdnBnfRt(BatchUtil.getTagValue("bpvtrOnskStckDvdnBnfRt", element))
                        .bpvtrParPrc(BatchUtil.getTagValue("bpvtrParPrc", element))
                        .bpvtrPfstCashDvdnAmt(BatchUtil.getTagValue("bpvtrPfstCashDvdnAmt", element))
                        .bpvtrPfstCashDvdnBnfRt(BatchUtil.getTagValue("bpvtrPfstCashDvdnBnfRt", element))
                        .bpvtrPfstStckDvdnAmt(BatchUtil.getTagValue("bpvtrPfstStckDvdnAmt", element))
                        .bpvtrPfstStckDvdnBnfRt(BatchUtil.getTagValue("bpvtrPfstStckDvdnBnfRt", element))
                        .bpvtrPstcNpf(BatchUtil.getTagValue("bpvtrPstcNpf", element))
                        .bpvtrStckTdvdAmt(BatchUtil.getTagValue("bpvtrStckTdvdAmt", element))
                        .crno(BatchUtil.getTagValue("crno", element))
                        .crtmCashDvdnTndnCtt(BatchUtil.getTagValue("crtmCashDvdnTndnCtt", element))
                        .crtmCashTdvdAmt(BatchUtil.getTagValue("crtmCashTdvdAmt", element))
                        .crtmIdvCrtmNpf(BatchUtil.getTagValue("crtmIdvCrtmNpf", element))
                        .crtmOnskCashDvdnAmt(BatchUtil.getTagValue("crtmOnskCashDvdnAmt", element))
                        .crtmOnskCashDvdnBnfRt(BatchUtil.getTagValue("crtmOnskCashDvdnBnfRt", element))
                        .crtmOnskStckDvdnAmt(BatchUtil.getTagValue("crtmOnskStckDvdnAmt", element))
                        .crtmOnskStckDvdnBnfRt(BatchUtil.getTagValue("crtmOnskStckDvdnBnfRt", element))
                        .crtmParPrc(BatchUtil.getTagValue("crtmParPrc", element))
                        .crtmPfstCashDvdnAmt(BatchUtil.getTagValue("crtmPfstCashDvdnAmt", element))
                        .crtmPfstCashDvdnBnfRt(BatchUtil.getTagValue("crtmPfstCashDvdnBnfRt", element))
                        .crtmPfstStckDvdnAmt(BatchUtil.getTagValue("crtmPfstStckDvdnAmt", element))
                        .crtmPfstStckDvdnBnfRt(BatchUtil.getTagValue("crtmPfstStckDvdnBnfRt", element))
                        .crtmPstcNpf(BatchUtil.getTagValue("crtmPstcNpf", element))
                        .crtmStckTdvdAmt(BatchUtil.getTagValue("crtmStckTdvdAmt", element))
                        .enpCrtmNpf(BatchUtil.getTagValue("enpCrtmNpf", element))
                        .fnclCrtmNpf(BatchUtil.getTagValue("fnclCrtmNpf", element))
                        .pvtrCashDvdnTndnCtt(BatchUtil.getTagValue("pvtrCashDvdnTndnCtt", element))
                        .pvtrCashTdvdAmt(BatchUtil.getTagValue("pvtrCashTdvdAmt", element))
                        .pvtrCrtmNpf(BatchUtil.getTagValue("pvtrCrtmNpf", element))
                        .pvtrIdvCrtmNpf(BatchUtil.getTagValue("pvtrIdvCrtmNpf", element))
                        .pvtrOnskCashDvdnAmt(BatchUtil.getTagValue("pvtrOnskCashDvdnAmt", element))
                        .pvtrOnskCashDvdnBnfRt(BatchUtil.getTagValue("pvtrOnskCashDvdnBnfRt", element))
                        .pvtrOnskStckDvdnAmt(BatchUtil.getTagValue("pvtrOnskStckDvdnAmt", element))
                        .pvtrOnskStckDvdnBnfRt(BatchUtil.getTagValue("pvtrOnskStckDvdnBnfRt", element))
                        .pvtrParPrc(BatchUtil.getTagValue("pvtrParPrc", element))
                        .pvtrPfstCashDvdnAmt(BatchUtil.getTagValue("pvtrPfstCashDvdnAmt", element))
                        .pvtrPfstCashDvdnBnfRt(BatchUtil.getTagValue("pvtrPfstCashDvdnBnfRt", element))
                        .pvtrPfstStckDvdnAmt(BatchUtil.getTagValue("pvtrPfstStckDvdnAmt", element))
                        .pvtrPfstStckDvdnBnfRt(BatchUtil.getTagValue("pvtrPfstStckDvdnBnfRt", element))
                        .pvtrPstcNpf(BatchUtil.getTagValue("pvtrPstcNpf", element))
                        .pvtrStckTdvdAmt(BatchUtil.getTagValue("pvtrStckTdvdAmt", element))
                        .rptFileCtt(BatchUtil.getTagValue("rptFileCtt", element))
                        .build()
                );
            } else {
                log.info("Empty node found.");
            }
        }
    }
    /**
     * 2.금융위원회_공시정보 (유상증자결정공시정보조회)
     * 기준일자, 법인등록번호를 통하여 시설자금액, 운영자금액, 주식내용, 주식상환금액, 정관근거내용등을 조회하는 유상증자결정공시정보조회 기능
     * @throws IOException IOException
     */
    public void CapiIncrWithConsDiscInfo() throws IOException {
        int pageNo = 1;
        int numOfRows = 2000;
        int totalCount = 0;

        do {
            String urlStr = serviceUrl + "/1160100/service/GetDiscInfoService_V2/getCapiIncrWithConsDiscInfo_V2?serviceKey=" + serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&resultType=xml";
            URL url = new URL(urlStr);

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
                log.info("CapiIncrWithConsDiscInfo totalCount:{}, pageNo: {}, pageSize:{}", totalCount, pageNo, Math.ceil((double) totalCount / numOfRows));
                if (pageNo == 1) {
                    totalCount = BatchUtil.getTotalCount(response.toString());
                }
                if (totalCount == (int) capiIncrWithConsDiscInfoRepository.count()) {
                    break;
                }else{
                    this.CapiIncrWithConsDiscInfoProcessResponse(response.toString());
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
     * 2.금융위원회_공시정보 (유상증자결정공시정보조회) - API 응답 처리
     * @param responseBody responseBody
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException SAXException
     * @throws IOException IOException
     */
    private void CapiIncrWithConsDiscInfoProcessResponse(String responseBody) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(responseBody)));
        document.getDocumentElement().normalize();
        NodeList childList = document.getElementsByTagName("item");
        for (int i = 0; i < childList.getLength(); i++) {
            Node item = childList.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) item;
                // DiscInfoJPO의 값을 매칭하여 아래 코드를 작성하세요.
                capiIncrWithConsDiscInfoRepository.save(CapiIncrWithConsDiscInfoJPO.builder()
                        .acthInvtYn(BatchUtil.getTagValue("acthInvtYn", element))
                        .arasBsisCtt(BatchUtil.getTagValue("arasBsisCtt", element))
                        .audpnAtndYn(BatchUtil.getTagValue("audpnAtndYn", element))
                        .basDt(BatchUtil.getTagValue("basDt", element))
                        .basStprDcXchrRt(BatchUtil.getTagValue("basStprDcXchrRt", element))
                        .bfSthdSbscSchEdDt(BatchUtil.getTagValue("bfSthdSbscSchEdDt", element))
                        .bfSthdSbscSchSttgDt(BatchUtil.getTagValue("bfSthdSbscSchSttgDt", element))
                        .bfciOnskIssuStckCnt(BatchUtil.getTagValue("bfciOnskIssuStckCnt", element))
                        .bfciPfstIssuStckCnt(BatchUtil.getTagValue("bfciPfstIssuStckCnt", element))
                        .bodRsolDt(BatchUtil.getTagValue("bodRsolDt", element))
                        .capiMthoNm(BatchUtil.getTagValue("capiMthoNm", element))
                        .cnvrIssuStckCnt(BatchUtil.getTagValue("cnvrIssuStckCnt", element))
                        .cnvrStckKindNm(BatchUtil.getTagValue("cnvrStckKindNm", element))
                        .corRsnCtt(BatchUtil.getTagValue("corRsnCtt", element))
                        .crno(BatchUtil.getTagValue("crno", element))
                        .dturLstgPsblYn(BatchUtil.getTagValue("dturLstgPsblYn", element))
                        .dturLstgYn(BatchUtil.getTagValue("dturLstgYn", element))
                        .emstowUnnFoalAlctRto(BatchUtil.getTagValue("emstowUnnFoalAlctRto", element))
                        .emstowUnnSbscSchEdDt(BatchUtil.getTagValue("emstowUnnSbscSchEdDt", element))
                        .emstowUnnSbscSchSttgDt(BatchUtil.getTagValue("emstowUnnSbscSchSttgDt", element))
                        .etcAgrmCtt(BatchUtil.getTagValue("etcAgrmCtt", element))
                        .etcCptlAmt(BatchUtil.getTagValue("etcCptlAmt", element))
                        .etcCtt(BatchUtil.getTagValue("etcCtt", element))
                        .fctfndAt(BatchUtil.getTagValue("fctfndAt", element))
                        .frstPcPlanCtt(BatchUtil.getTagValue("frstPcPlanCtt", element))
                        .hcfhPlanCtt(BatchUtil.getTagValue("hcfhPlanCtt", element))
                        .imarTrCmteDclTrgtYn(BatchUtil.getTagValue("imarTrCmteDclTrgtYn", element))
                        .issuPricCmpuMthCtt(BatchUtil.getTagValue("issuPricCmpuMthCtt", element))
                        .ivsRefCtt(BatchUtil.getTagValue("ivsRefCtt", element))
                        .mgmCptAt(BatchUtil.getTagValue("mgmCptAt", element))
                        .nrfsHndvSchDt(BatchUtil.getTagValue("nrfsHndvSchDt", element))
                        .nstAlctBasDt(BatchUtil.getTagValue("nstAlctBasDt", element))
                        .nstAlctStckCnt(BatchUtil.getTagValue("nstAlctStckCnt", element))
                        .nstDvdnRckDt(BatchUtil.getTagValue("nstDvdnRckDt", element))
                        .nstLstgSchDt(BatchUtil.getTagValue("nstLstgSchDt", element))
                        .onskFrmIssuPricSchDt(BatchUtil.getTagValue("onskFrmIssuPricSchDt", element))
                        .onskIssuFrmPric(BatchUtil.getTagValue("onskIssuFrmPric", element))
                        .onskNstCnt(BatchUtil.getTagValue("onskNstCnt", element))
                        .otcoScrtAcqAmt(BatchUtil.getTagValue("otcoScrtAcqAmt", element))
                        .otdrAbncNopeCnt(BatchUtil.getTagValue("otdrAbncNopeCnt", element))
                        .otdrAtndNopeCnt(BatchUtil.getTagValue("otdrAtndNopeCnt", element))
                        .otshFrmIssuPricSchDt(BatchUtil.getTagValue("otshFrmIssuPricSchDt", element))
                        .otshIssuFrmPric(BatchUtil.getTagValue("otshIssuFrmPric", element))
                        .otshIssuSchPric(BatchUtil.getTagValue("otshIssuSchPric", element))
                        .pbsbSbscSchEdDt(BatchUtil.getTagValue("pbsbSbscSchEdDt", element))
                        .pbsbSbscSchSttgDt(BatchUtil.getTagValue("pbsbSbscSchSttgDt", element))
                        .pfstNstCnt(BatchUtil.getTagValue("pfstNstCnt", element))
                        .pftDvdnCtt(BatchUtil.getTagValue("pftDvdnCtt", element))
                        .prmrCnvYn(BatchUtil.getTagValue("prmrCnvYn", element))
                        .prmrCrtfLstgYn(BatchUtil.getTagValue("prmrCrtfLstgYn", element))
                        .pymtSchActhInvtAmt(BatchUtil.getTagValue("pymtSchActhInvtAmt", element))
                        .pymtSchActhInvtRto(BatchUtil.getTagValue("pymtSchActhInvtRto", element))
                        .pymtSchStckCnt(BatchUtil.getTagValue("pymtSchStckCnt", element))
                        .rd3ptAlctArasBsisCtt(BatchUtil.getTagValue("rd3ptAlctArasBsisCtt", element))
                        .rgscUnltStckYn(BatchUtil.getTagValue("rgscUnltStckYn", element))
                        .rprsSptdCmpyNm(BatchUtil.getTagValue("rprsSptdCmpyNm", element))
                        .rptFileCtt(BatchUtil.getTagValue("rptFileCtt", element))
                        .sbmsExemRsnCtt(BatchUtil.getTagValue("sbmsExemRsnCtt", element))
                        .scrtDclrptSbmsTrgtYn(BatchUtil.getTagValue("scrtDclrptSbmsTrgtYn", element))
                        .shcpPymtDt(BatchUtil.getTagValue("shcpPymtDt", element))
                        .stckCnvrClmTermMnthCnt(BatchUtil.getTagValue("stckCnvrClmTermMnthCnt", element))
                        .stckCnvrCondCtt(BatchUtil.getTagValue("stckCnvrCondCtt", element))
                        .stckCtt(BatchUtil.getTagValue("stckCtt", element))
                        .stckParPrc(BatchUtil.getTagValue("stckParPrc", element))
                        .stckRdptAmt(BatchUtil.getTagValue("stckRdptAmt", element))
                        .stckRdptCondNm(BatchUtil.getTagValue("stckRdptCondNm", element))
                        .stckRdptMthNm(BatchUtil.getTagValue("stckRdptMthNm", element))
                        .stckRdptTermMnthCnt(BatchUtil.getTagValue("stckRdptTermMnthCnt", element))
                        .stckVtrgCtt(BatchUtil.getTagValue("stckVtrgCtt", element))
                        .trdMdatFinInvsNm(BatchUtil.getTagValue("trdMdatFinInvsNm", element))
                        .wt1yRdptSchYn(BatchUtil.getTagValue("wt1yRdptSchYn", element))
                        .build()
                );
            } else {
                log.info("Empty node found.");
            }
        }
    }
}
