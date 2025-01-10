package com.herojoon.jpaproject.service;

import com.herojoon.jpaproject.entity.*;

import com.herojoon.jpaproject.repository.*;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscInfoService {
    private final CapiIncrWithConsDiscInfoRepository capiIncrWithConsDiscInfoRepository;
    private final DiviDiscInfoRepository diviDiscInfoRepository;
    private final BonuIssuDiscInfoRepository bonuIssuDiscInfoRepository;
    private final CapiIncrWithConsBonuIssuDiscInfoRepository capiIncrWithConsBonuIssuDiscInfoRepository;
    private final GeneMeetStocPublNotiDiscInfoRepository geneMeetStocPublNotiDiscInfoRepository;
    private final AsseTranPutBackOptiDiscInfoRepository asseTranPutBackOptiDiscInfoRepository;
    private final DishDiscInfoRepository dishDiscInfoRepository;
    private final BusiSuspDiscInfoRepository busiSuspDiscInfoRepository;
    private final ReviProcDiscInfoRepository reviProcDiscInfoRepository;
    private final DissReasDiscInfoRepository dissReasDiscInfoRepository;
    private final ReduCapiDiscInfoRepository reduCapiDiscInfoRepository;
    private final ProcByCredBankDiscInfoRepository procByCredBankDiscInfoRepository;
    private final LitiEtcDiscInfoRepository litiEtcDiscInfoRepository;
    private final OffsSecuMarkListDiscInfoRepository offsSecuMarkListDiscInfoRepository;

    public static final String USER_AGENT = "Mozilla/5.0";
    @Value("${api.service.key}")
    private String serviceKey;
    @Value("${api.service.url}")
    private String serviceUrl;

    /**
     * 1.금융위원회_공시정보 (배당공시정보조회)
     *
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
                } else {
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
     *
     * @param responseBody responseBody
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException                 SAXException
     * @throws IOException                  IOException
     */
    private void DiviDiscInfoProcessResponse(String responseBody) throws ParserConfigurationException, SAXException, IOException {
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
                diviDiscInfoRepository.save(DiviDiscInfoEntity.builder()
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
            }
        }
    }


    /**
     * 2.금융위원회_공시정보 (유상증자결정공시정보조회)
     * 기준일자, 법인등록번호를 통하여 시설자금액, 운영자금액, 주식내용, 주식상환금액, 정관근거내용등을 조회하는 유상증자결정공시정보조회 기능
     *
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
                } else {
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
     *
     * @param responseBody responseBody
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException                 SAXException
     * @throws IOException                  IOException
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
                capiIncrWithConsDiscInfoRepository.save(CapiIncrWithConsDiscInfoEntity.builder()
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
            }
        }
    }


    /**
     * 3.금융위원회_공시정보 (무상증자결정공시정보조회)
     *
     * @throws IOException IOException
     */
    public void BonuIssuDiscInfo() throws IOException {
        int pageNo = 1;
        int numOfRows = 2000;
        int totalCount = 0;

        do {
            String urlStr = serviceUrl + "/1160100/service/GetDiscInfoService_V2/getBonuIssuDiscInfo_V2?serviceKey=" + serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&resultType=xml";
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
                log.info("BonuIssuDiscInfo totalCount:{}, pageNo: {}, pageSize:{}", totalCount, pageNo, Math.ceil((double) totalCount / numOfRows));
                if (pageNo == 1) {
                    totalCount = BatchUtil.getTotalCount(response.toString());
                }
                if (totalCount == (int) bonuIssuDiscInfoRepository.count()) {
                    break;
                } else {
                    this.BonuIssuDiscInfoProcessResponse(response.toString());
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
     * 3.금융위원회_공시정보 (무상증자결정공시정보조회) - API 응답 처리
     *
     * @param responseBody responseBody
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException                 SAXException
     * @throws IOException                  IOException
     */
    private void BonuIssuDiscInfoProcessResponse(String responseBody) throws ParserConfigurationException, SAXException, IOException {
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
                bonuIssuDiscInfoRepository.save(BonuIssuDiscInfoEntity.builder()
                        .audtCmbrAtndYn(BatchUtil.getTagValue("audtCmbrAtndYn", element))
                        .basDt(BatchUtil.getTagValue("basDt", element))
                        .bfciOnskTisuStckCnt(Float.parseFloat(Objects.requireNonNull(BatchUtil.getTagValue("bfciOnskTisuStckCnt", element))))
                        .bfciOtshTisuStckCnt(Float.parseFloat(Objects.requireNonNull(BatchUtil.getTagValue("bfciOtshTisuStckCnt", element))))
                        .bodRsolDt(BatchUtil.getTagValue("bodRsolDt", element))
                        .crno(BatchUtil.getTagValue("crno", element))
                        .ivsRefCtt(BatchUtil.getTagValue("ivsRefCtt", element))
                        .nrfsHndvSchDt(BatchUtil.getTagValue("nrfsHndvSchDt", element))
                        .nstAlctBasDt(BatchUtil.getTagValue("nstAlctBasDt", element))
                        .nstDvdnRckDt(BatchUtil.getTagValue("nstDvdnRckDt", element))
                        .nstLstgSchDt(BatchUtil.getTagValue("nstLstgSchDt", element))
                        .onskNstAlctStckCnt(Float.parseFloat(Objects.requireNonNull(BatchUtil.getTagValue("onskNstAlctStckCnt", element))))
                        .onskNstCnt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("onskNstCnt", element))))
                        .otdrAbncNopeCnt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("otdrAbncNopeCnt", element))))
                        .otdrAtndNopeCnt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("otdrAtndNopeCnt", element))))
                        .otshNstAlctStckCnt(Float.parseFloat(Objects.requireNonNull(BatchUtil.getTagValue("otshNstAlctStckCnt", element))))
                        .otshNstCnt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("otshNstCnt", element))))
                        .rptFileCtt(BatchUtil.getTagValue("rptFileCtt", element))
                        .stckParPrc(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("stckParPrc", element))))
                        .build()
                );
            }
        }
    }


    /**
     * 4.금융위원회_공시정보 (유무상증자결정공시정보조회)
     *
     * @throws IOException IOException
     */
    public void CapiIncrWithConsBonuIssuDiscInfo() throws IOException {
        int pageNo = 1;
        int numOfRows = 2000;
        int totalCount = 0;

        do {
            String urlStr = serviceUrl + "/1160100/service/GetDiscInfoService_V2/getCapiIncrWithConsBonuIssuDiscInfo_V2?serviceKey=" + serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&resultType=xml";
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
                log.info("CapiIncrWithConsBonuIssuDiscInfo totalCount:{}, pageNo: {}, pageSize:{}", totalCount, pageNo, Math.ceil((double) totalCount / numOfRows));
                if (pageNo == 1) {
                    totalCount = BatchUtil.getTotalCount(response.toString());
                }
                if (totalCount == (int) capiIncrWithConsBonuIssuDiscInfoRepository.count()) {
                    break;
                } else {
                    this.CapiIncrWithConsBonuIssuDiscInfoProcessResponse(response.toString());
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
     * 4.금융위원회_공시정보 (유무상증자결정공시정보조회) - API 응답 처리
     *
     * @param responseBody String
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException                 SAXException
     * @throws IOException                  IOException
     */
    private void CapiIncrWithConsBonuIssuDiscInfoProcessResponse(String responseBody) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(responseBody)));
        document.getDocumentElement().normalize();
        NodeList childList = document.getElementsByTagName("item");
        for (int i = 0; i < childList.getLength(); i++) {
            Node item = childList.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) item;
                capiIncrWithConsBonuIssuDiscInfoRepository.save(CapiIncrWithConsBonuIssuDiscInfoEntity.builder()
                        .acthInvtYn(BatchUtil.getTagValue("acthInvtYn", element))
                        .arasBsisCtt(BatchUtil.getTagValue("arasBsisCtt", element))
                        .audpnAtndYn(BatchUtil.getTagValue("audpnAtndYn", element))
                        .basDt(BatchUtil.getTagValue("basDt", element))
                        .basStprDcXchrRt(BatchUtil.getTagValue("basStprDcXchrRt", element))
                        .bfSthdSbscSchEdDt(BatchUtil.getTagValue("bfSthdSbscSchEdDt", element))
                        .bfSthdSbscSchSttgDt(BatchUtil.getTagValue("bfSthdSbscSchSttgDt", element))
                        .bfciOnskTisuStckCnt(BatchUtil.getTagValue("bfciOnskTisuStckCnt", element))
                        .bfciOtshTisuStckCnt(BatchUtil.getTagValue("bfciOtshTisuStckCnt", element))
                        .bodRsolDt(BatchUtil.getTagValue("bodRsolDt", element))
                        .capiMthoNm(BatchUtil.getTagValue("capiMthoNm", element))
                        .cnvrIssuStckCnt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("cnvrIssuStckCnt", element))))
                        .cnvrStckKindNm(BatchUtil.getTagValue("cnvrStckKindNm", element))
                        .corRsnCtt(BatchUtil.getTagValue("corRsnCtt", element))
                        .crno(BatchUtil.getTagValue("crno", element))
                        .dturLstgPsblYn(BatchUtil.getTagValue("dturLstgPsblYn", element))
                        .dturLstgYn(BatchUtil.getTagValue("dturLstgYn", element))
                        .emstowUnnFoalAlctRto(Double.parseDouble(Objects.requireNonNull(BatchUtil.getTagValue("emstowUnnFoalAlctRto", element))))
                        .emstowUnnSbscSchEdDt(BatchUtil.getTagValue("emstowUnnSbscSchEdDt", element))
                        .emstowUnnSbscSchSttgDt(BatchUtil.getTagValue("emstowUnnSbscSchSttgDt", element))
                        .enpCorpNm(BatchUtil.getTagValue("enpCorpNm", element))
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
                        .nstDvdnRckDt(BatchUtil.getTagValue("nstDvdnRckDt", element))
                        .nstLstgSchDt(BatchUtil.getTagValue("nstLstgSchDt", element))
                        .onskFrmIssuPricSchDt(BatchUtil.getTagValue("onskFrmIssuPricSchDt", element))
                        .onskIssuFrmPric(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("onskIssuFrmPric", element))))
                        .onskIssuSchPric(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("onskIssuSchPric", element))))
                        .onskNstAlctStckCnt(Double.parseDouble(Objects.requireNonNull(BatchUtil.getTagValue("onskNstAlctStckCnt", element))))
                        .onskNstCnt(BatchUtil.getTagValue("onskNstCnt", element))
                        .otcoScrtAcqAmt(BatchUtil.getTagValue("otcoScrtAcqAmt", element))
                        .otdrAbncNopeCnt(BatchUtil.getTagValue("otdrAbncNopeCnt", element))
                        .otdrAtndNopeCnt(BatchUtil.getTagValue("otdrAtndNopeCnt", element))
                        .otshFrmIssuPricSchDt(BatchUtil.getTagValue("otshFrmIssuPricSchDt", element))
                        .otshIssuFrmPric(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("otshIssuFrmPric", element))))
                        .otshIssuSchPric(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("otshIssuSchPric", element))))
                        .otshNstAlctStckCnt(Double.parseDouble(Objects.requireNonNull(BatchUtil.getTagValue("otshNstAlctStckCnt", element))))
                        .otshNstCnt(BatchUtil.getTagValue("otshNstCnt", element))
                        .pbsbSbscSchEdDt(BatchUtil.getTagValue("pbsbSbscSchEdDt", element))
                        .pbsbSbscSchSttgDt(BatchUtil.getTagValue("pbsbSbscSchSttgDt", element))
                        .pftDvdnCtt(BatchUtil.getTagValue("pftDvdnCtt", element))
                        .prmrCnvYn(BatchUtil.getTagValue("prmrCnvYn", element))
                        .prmrCrtfLstgYn(BatchUtil.getTagValue("prmrCrtfLstgYn", element))
                        .pymtSchActhInvtAmt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("pymtSchActhInvtAmt", element))))
                        .pymtSchActhInvtRto(BatchUtil.getTagValue("pymtSchActhInvtRto", element))
                        .pymtSchStckCnt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("pymtSchStckCnt", element))))
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
                        .stckRdptAmt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("stckRdptAmt", element))))
                        .stckRdptCondNm(BatchUtil.getTagValue("stckRdptCondNm", element))
                        .stckRdptMthNm(BatchUtil.getTagValue("stckRdptMthNm", element))
                        .stckRdptTermMnthCnt(BatchUtil.getTagValue("stckRdptTermMnthCnt", element))
                        .stckVtrgCtt(BatchUtil.getTagValue("stckVtrgCtt", element))
                        .trdMdatFinInvsNm(BatchUtil.getTagValue("trdMdatFinInvsNm", element))
                        .wt1yRdptSchYn(BatchUtil.getTagValue("wt1yRdptSchYn", element))
                        .build());
            }
        }
    }

    /**
     * 5.금융위원회_공시정보 (주주총회소집공고공시정보조회)
     * 기준일자, 법인등록번호, 기업법인명을 통하여 시설자금액, 증자방식명, 정관근거내용등을 조회하는 유무상증자결정공시정보조회 기능
     *
     * @throws IOException IOException
     */
    public void GeneMeetStocPublNotiDiscInfo() throws IOException {
        int pageNo = 1;
        int numOfRows = 2000;
        int totalCount = 0;

        do {
            String urlStr = serviceUrl + "/1160100/service/GetDiscInfoService_V2/getGeneMeetStocPublNotiDiscInfo_V2?serviceKey=" + serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&resultType=xml";
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
                log.info("GeneMeetStocPublNotiDiscInfo totalCount:{}, pageNo: {}, pageSize:{}", totalCount, pageNo, Math.ceil((double) totalCount / numOfRows));
                if (pageNo == 1) {
                    totalCount = BatchUtil.getTotalCount(response.toString());
                }
                if (totalCount == (int) geneMeetStocPublNotiDiscInfoRepository.count()) {
                    break;
                } else {
                    this.GeneMeetStocPublNotiDiscInfoProcessResponse(response.toString());
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
     * 5.금융위원회_공시정보 (주주총회소집공고공시정보조회) - API 응답 처리
     *
     * @param responseBody responseBody
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException                 SAXException
     * @throws IOException                  IOException
     */
    private void GeneMeetStocPublNotiDiscInfoProcessResponse(String responseBody) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(responseBody)));
        document.getDocumentElement().normalize();
        NodeList childList = document.getElementsByTagName("item");

        for (int i = 0; i < childList.getLength(); i++) {
            Node item = childList.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) item;
                geneMeetStocPublNotiDiscInfoRepository.save(GeneMeetStocPublNotiDiscInfoEntity.builder()
                        .basDt(BatchUtil.getTagValue("basDt", element))
                        .corpNm(BatchUtil.getTagValue("corpNm", element))
                        .crno(BatchUtil.getTagValue("crno", element))
                        .rptFileCtt(BatchUtil.getTagValue("rptFileCtt", element))
                        .build());
            }
        }
    }

    /**
     * 6.금융위원회_공시정보 (자산양수도(기타)_풋백옵션공시정보조회)
     *
     * @throws IOException IOException
     */
    public void AsseTranPutBackOptiDiscInfo() throws IOException {
        int pageNo = 1;
        int numOfRows = 2000;
        int totalCount = 0;

        do {
            String urlStr = serviceUrl + "/1160100/service/GetDiscInfoService_V2/getAsseTranPutBackOptiDiscInfo_V2?serviceKey=" + serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&resultType=xml";
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
                log.info("AsseTranPutBackOptiDiscInfo totalCount:{}, pageNo: {}, pageSize:{}", totalCount, pageNo, Math.ceil((double) totalCount / numOfRows));
                if (pageNo == 1) {
                    totalCount = BatchUtil.getTotalCount(response.toString());
                }
                if (totalCount == (int) asseTranPutBackOptiDiscInfoRepository.count()) {
                    break;
                } else {
                    this.AsseTranPutBackOptiDiscInfoProcessResponse(response.toString());
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
     * 6.금융위원회_공시정보 (자산양수도(기타)_풋백옵션공시정보조회) - API 응답 처리
     *
     * @param responseBody responseBody
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException                 SAXException
     * @throws IOException                  IOException
     */
    private void AsseTranPutBackOptiDiscInfoProcessResponse(String responseBody) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(responseBody)));
        document.getDocumentElement().normalize();
        NodeList childList = document.getElementsByTagName("item");

        for (int i = 0; i < childList.getLength(); i++) {
            Node item = childList.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) item;
                asseTranPutBackOptiDiscInfoRepository.save(AsseTranPutBackOptiDiscInfoEntity.builder()
                        .basDt(BatchUtil.getTagValue("basDt", element))
                        .crno(BatchUtil.getTagValue("crno", element))
                        .enpCorpNm(BatchUtil.getTagValue("enpCorpNm", element))
                        .rptFileCtt(BatchUtil.getTagValue("rptFileCtt", element))
                        .build());
            }
        }
    }

    /**
     * 7.금융위원회_공시정보 (부도발생공시정보조회)
     *
     * @throws IOException IOException
     */
    public void DishDiscInfo() throws IOException {
        int pageNo = 1;
        int numOfRows = 2000;
        int totalCount = 0;

        do {
            String urlStr = serviceUrl + "/1160100/service/GetDiscInfoService_V2/getDishDiscInfo_V2?serviceKey=" + serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&resultType=json";
            URL url = new URL(urlStr);

            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("CONTENT-TYPE", "application/json");
            con.setDoOutput(true);
            con.setConnectTimeout(10000);
            con.setReadTimeout(5000);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String inputline;
                while ((inputline = in.readLine()) != null) {
                    response.append(inputline.trim());
                }
                log.info("DishDiscInfo totalCount:{}, pageNo: {}, pageSize:{}", totalCount, pageNo, Math.ceil((double) totalCount / numOfRows));
                if (pageNo == 1) {
                    JSONParser jsonParser = new JSONParser();
                    JSONObject jsonObject = (JSONObject) jsonParser.parse(response.toString());

                    JSONObject res = (JSONObject) jsonObject.get("response");
                    JSONObject body = (JSONObject) res.get("body");

                    totalCount = Integer.parseInt(body.get("totalCount").toString());
                }
                if (totalCount == (int) dishDiscInfoRepository.count()) {
                    break;
                } else {
                    this.DishDiscInfoProcessResponse(response.toString());
                }
            } catch (IOException ex) {
                log.error("Error occurred while calling API", ex);
                throw ex;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            } finally {
                con.disconnect();
            }
            pageNo++;
        } while (pageNo <= Math.ceil((double) totalCount / numOfRows));
    }

    /**
     * 7.금융위원회_공시정보 (부도발생공시정보조회) - API 응답 처리
     *
     * @param responseBody
     * @throws ParseException
     * @throws IOException
     */
    private void DishDiscInfoProcessResponse(String responseBody) throws ParseException, IOException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);

        JSONObject response = (JSONObject) jsonObject.get("response");
        JSONObject body = (JSONObject) response.get("body");

        JSONObject items = (JSONObject) body.get("items");
        JSONArray item = (JSONArray) items.get("item");

        for (Object json : item) {
            JSONObject field = (JSONObject) json;
            dishDiscInfoRepository.save(DishDiscInfoEntity.builder()
                    .lastDshDt(field.get("lastDshDt").toString())
                    .dshRsnCtt(field.get("dshRsnCtt").toString())
                    .dshOccrBnkNm(field.get("dshOccrBnkNm").toString())
                    .ivsRefCtt(field.get("ivsRefCtt").toString())
                    .dshCtt(field.get("dshCtt").toString())
                    .basDt(field.get("basDt").toString())
                    .dshAmt(field.get("dshAmt").toString())
                    .crno(field.get("crno").toString())
                    .rptFileCtt(field.get("rptFileCtt").toString())
                    .build());
        }
    }

    /**
     * 8.금융위원회_공시정보 (영업정지공시정보조회)
     *
     * @param responseBody String
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException                 SAXException
     * @throws IOException                  IOException
     */
    private void BusiSuspDiscInfoProcessResponse(String responseBody) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(responseBody)));
        document.getDocumentElement().normalize();
        NodeList childList = document.getElementsByTagName("item");

        for (int i = 0; i < childList.getLength(); i++) {
            Node item = childList.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) item;
                busiSuspDiscInfoRepository.save(BusiSuspDiscInfoEntity.builder()
                        .audpnAtndYn(BatchUtil.getTagValue("audpnAtndYn", element))
                        .basDt(BatchUtil.getTagValue("basDt", element))
                        .bodRsolDt(BatchUtil.getTagValue("bodRsolDt", element))
                        .bzopStopAmt(BatchUtil.getTagValue("bzopStopAmt", element))
                        .bzopStopAmtRto(Double.parseDouble(Objects.requireNonNull(BatchUtil.getTagValue("bzopStopAmtRto", element))))
                        .bzopStopCtpnCtt(BatchUtil.getTagValue("bzopStopCtpnCtt", element))
                        .bzopStopCtt(BatchUtil.getTagValue("bzopStopCtt", element))
                        .bzopStopDt(BatchUtil.getTagValue("bzopStopDt", element))
                        .bzopStopFildNm(BatchUtil.getTagValue("bzopStopFildNm", element))
                        .bzopStopInfcCtt(BatchUtil.getTagValue("bzopStopInfcCtt", element))
                        .bzopStopRsnCtt(BatchUtil.getTagValue("bzopStopRsnCtt", element))
                        .crno(BatchUtil.getTagValue("crno", element))
                        .ivsRefCtt(BatchUtil.getTagValue("ivsRefCtt", element))
                        .lgscCorpYn(BatchUtil.getTagValue("lgscCorpYn", element))
                        .ltstTsleAmt(BatchUtil.getTagValue("ltstTsleAmt", element))
                        .otdrAbncNopeCnt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("otdrAbncNopeCnt", element))))
                        .otdrAtndNopeCnt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("otdrAtndNopeCnt", element))))
                        .rptFileCtt(BatchUtil.getTagValue("rptFileCtt", element))
                        .xchgLblPbanYn(BatchUtil.getTagValue("xchgLblPbanYn", element))
                        .build());
            }
        }
    }

    /**
     * 8.금융위원회_공시정보 (영업정지공시정보조회)
     *
     * @throws IOException IOException
     */
    public void BusiSuspDiscInfo() throws IOException {
        int pageNo = 1;
        int numOfRows = 2000;
        int totalCount = 0;

        do {
            String urlStr = serviceUrl + "/1160100/service/GetDiscInfoService_V2/getBusiSuspDiscInfo_V2?serviceKey=" + serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&resultType=xml";
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
                log.info("BusiSuspDiscInfo totalCount:{}, pageNo: {}, pageSize:{}", totalCount, pageNo, Math.ceil((double) totalCount / numOfRows));
                if (pageNo == 1) {
                    totalCount = BatchUtil.getTotalCount(response.toString());
                }
                if (totalCount == (int) busiSuspDiscInfoRepository.count()) {
                    break;
                } else {
                    this.BusiSuspDiscInfoProcessResponse(response.toString());
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
     * 9.금융위원회_공시정보 (회생절차개시신청공시정보조회)
     *
     * @throws IOException IOException
     */
    public void ReviProcDiscInfo() throws IOException {
        int pageNo = 1;
        int numOfRows = 2000;
        int totalCount = 0;
        do {
            String urlStr = serviceUrl + "/1160100/service/GetDiscInfoService_V2/getReviProcDiscInfo_V2?serviceKey=" + serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&resultType=xml";
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
                log.info("BusiSuspDiscInfo totalCount:{}, pageNo: {}, pageSize:{}", totalCount, pageNo, Math.ceil((double) totalCount / numOfRows));
                if (pageNo == 1) {
                    totalCount = BatchUtil.getTotalCount(response.toString());
                }
                if (totalCount == (int) reviProcDiscInfoRepository.count()) {
                    break;
                } else {
                    this.ReviProcDiscInfoProcessResponse(response.toString());
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
     * 9.금융위원회_공시정보 (회생절차개시신청공시정보조회) - API 응답 처리
     *
     * @param responseBody
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private void ReviProcDiscInfoProcessResponse(String responseBody) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(responseBody)));
        document.getDocumentElement().normalize();
        NodeList childList = document.getElementsByTagName("item");

        for (int i = 0; i < childList.getLength(); i++) {
            Node item = childList.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) item;
                reviProcDiscInfoRepository.save(ReviProcDiscInfoEntity.builder()
                        .basDt(BatchUtil.getTagValue("basDt", element))
                        .corvAplpnFnm(BatchUtil.getTagValue("corvAplpnFnm", element))
                        .corvCtpnCtt(BatchUtil.getTagValue("corvCtpnCtt", element))
                        .corvJurdCurtNm(BatchUtil.getTagValue("corvJurdCurtNm", element))
                        .corvPropDt(BatchUtil.getTagValue("corvPropDt", element))
                        .corvPropRsnCtt(BatchUtil.getTagValue("corvPropRsnCtt", element))
                        .crno(BatchUtil.getTagValue("crno", element))
                        .ivsRefCtt(BatchUtil.getTagValue("ivsRefCtt", element))
                        .rptFileCtt(BatchUtil.getTagValue("rptFileCtt", element))
                        .build());
            }
        }
    }

    /**
     * 10.금융위원회_공시정보 (해산사유발생공시정보조회)
     *
     * @throws IOException IOException
     */
    public void DissReasDiscInfo() throws IOException {
        int pageNo = 1;
        int numOfRows = 2000;
        int totalCount = 0;
        do {
            String urlStr = serviceUrl + "/1160100/service/GetDiscInfoService_V2/getDissReasDiscInfo_V2?serviceKey=" + serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&resultType=xml";
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
                log.info("BusiSuspDiscInfo totalCount:{}, pageNo: {}, pageSize:{}", totalCount, pageNo, Math.ceil((double) totalCount / numOfRows));
                if (pageNo == 1) {
                    totalCount = BatchUtil.getTotalCount(response.toString());
                }
                if (totalCount == (int) dissReasDiscInfoRepository.count()) {
                    break;
                } else {
                    this.DissReasDiscInfoProcessResponse(response.toString());
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
     * 10.금융위원회_공시정보 (해산사유발생공시정보조회) - API 응답 처리
     *
     * @param responseBody String
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException                 SAXException
     * @throws IOException                  IOException
     */
    private void DissReasDiscInfoProcessResponse(String responseBody) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(responseBody)));
        document.getDocumentElement().normalize();
        NodeList childList = document.getElementsByTagName("item");

        for (int i = 0; i < childList.getLength(); i++) {
            Node item = childList.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) item;
                dissReasDiscInfoRepository.save(DissReasDiscInfoEntity.builder()
                        .audtCmbrAtndYn(BatchUtil.getTagValue("audtCmbrAtndYn", element))
                        .basDt(BatchUtil.getTagValue("basDt", element))
                        .corpDsonCtt(BatchUtil.getTagValue("corpDsonCtt", element))
                        .corpDsonRsnCtt(BatchUtil.getTagValue("corpDsonRsnCtt", element))
                        .corpDsonRsnOccrDt(BatchUtil.getTagValue("corpDsonRsnOccrDt", element))
                        .crno(BatchUtil.getTagValue("crno", element))
                        .ivsRefCtt(BatchUtil.getTagValue("ivsRefCtt", element))
                        .otdrAbncNopeCnt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("otdrAbncNopeCnt", element))))
                        .otdrAtndNopeCnt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("otdrAtndNopeCnt", element))))
                        .rptFileCtt(BatchUtil.getTagValue("rptFileCtt", element))
                        .build());
            }
        }
    }

    /**
     * 11.금융위원회_공시정보 (감자결정공시정보조회)
     *
     * @throws IOException IOException
     */
    public void ReduCapiDiscInfo() throws IOException {
        int pageNo = 1;
        int numOfRows = 2000;
        int totalCount = 0;
        do {
            String urlStr = serviceUrl + "/1160100/service/GetDiscInfoService_V2/getReduCapiDiscInfo_V2?serviceKey=" + serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&resultType=xml";
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
                log.info("BusiSuspDiscInfo totalCount:{}, pageNo: {}, pageSize:{}", totalCount, pageNo, Math.ceil((double) totalCount / numOfRows));
                if (pageNo == 1) {
                    totalCount = BatchUtil.getTotalCount(response.toString());
                }
                if (totalCount == (int) reduCapiDiscInfoRepository.count()) {
                    break;
                } else {
                    this.ReduCapiDiscInfoProcessResponse(response.toString());
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
     * 11.금융위원회_공시정보 (감자결정공시정보조회) - API 응답 처리
     *
     * @param responseBody String
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException                 SAXException
     * @throws IOException                  IOException
     */
    private void ReduCapiDiscInfoProcessResponse(String responseBody) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(responseBody)));
        document.getDocumentElement().normalize();
        NodeList childList = document.getElementsByTagName("item");

        for (int i = 0; i < childList.getLength(); i++) {
            Node item = childList.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) item;
                reduCapiDiscInfoRepository.save(ReduCapiDiscInfoEntity.builder()
                        .afrcCptlAmt(BatchUtil.getTagValue("afrcCptlAmt", element))
                        .afrcIssuOnskCnt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("afrcIssuOnskCnt", element))))
                        .afrcIssuOtshCnt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("afrcIssuOtshCnt", element))))
                        .arasBsisCtt(BatchUtil.getTagValue("arasBsisCtt", element))
                        .audtCmbrAtndYn(BatchUtil.getTagValue("audtCmbrAtndYn", element))
                        .basDt(BatchUtil.getTagValue("basDt", element))
                        .bfrcCptlAmt(BatchUtil.getTagValue("bfrcCptlAmt", element))
                        .bfrcIssuOnskCnt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("bfrcIssuOnskCnt", element))))
                        .bfrcIssuOtshCnt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("bfrcIssuOtshCnt", element))))
                        .bodRsolDt(BatchUtil.getTagValue("bodRsolDt", element))
                        .crno(BatchUtil.getTagValue("crno", element))
                        .dsceSbmsEdDt(BatchUtil.getTagValue("dsceSbmsEdDt", element))
                        .dsceSbmsSttgDt(BatchUtil.getTagValue("dsceSbmsSttgDt", element))
                        .etcCtt(BatchUtil.getTagValue("etcCtt", element))
                        .gmshSchDt(BatchUtil.getTagValue("gmshSchDt", element))
                        .imarTrCmteDclTrgtYn(BatchUtil.getTagValue("imarTrCmteDclTrgtYn", element))
                        .ivsRefCtt(BatchUtil.getTagValue("ivsRefCtt", element))
                        .nrfsHndvPlcNm(BatchUtil.getTagValue("nrfsHndvPlcNm", element))
                        .nrfsHndvSchDt(BatchUtil.getTagValue("nrfsHndvSchDt", element))
                        .nstLstgSchDt(BatchUtil.getTagValue("nstLstgSchDt", element))
                        .onskRdcpRto(Double.parseDouble(Objects.requireNonNull(BatchUtil.getTagValue("onskRdcpRto", element))))
                        .orfsSbmsTermMnthCnt(BatchUtil.getTagValue("orfsSbmsTermMnthCnt", element))
                        .otdrAbncNopeCnt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("otdrAbncNopeCnt", element))))
                        .otdrAtndNopeCnt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("otdrAtndNopeCnt", element))))
                        .otshRdcpRto(Double.parseDouble(Objects.requireNonNull(BatchUtil.getTagValue("otshRdcpRto", element))))
                        .rdcpBasDt(BatchUtil.getTagValue("rdcpBasDt", element))
                        .rdcpMthNm(BatchUtil.getTagValue("rdcpMthNm", element))
                        .rdcpOnskCnt(BatchUtil.getTagValue("rdcpOnskCnt", element))
                        .rdcpOtshCnt(BatchUtil.getTagValue("rdcpOtshCnt", element))
                        .rdcpRsnCtt(BatchUtil.getTagValue("rdcpRsnCtt", element))
                        .rptFileCtt(BatchUtil.getTagValue("rptFileCtt", element))
                        .stckCtt(BatchUtil.getTagValue("stckCtt", element))
                        .stckParPrc(BatchUtil.getTagValue("stckParPrc", element))
                        .trStopSchTermMnthCnt(BatchUtil.getTagValue("trStopSchTermMnthCnt", element))
                        .trsnmStopTermMnthCnt(BatchUtil.getTagValue("trsnmStopTermMnthCnt", element))
                        .build());
            }
        }
    }

    /**
     * 12.금융위원회_공시정보 (채권은행등의관리절차개시공시정보조회)
     *
     * @throws IOException IOException
     */
    public void ProcByCredBankDiscInfo() throws IOException {
        int pageNo = 1;
        int numOfRows = 2000;
        int totalCount = 0;
        do {
            String urlStr = serviceUrl + "/1160100/service/GetDiscInfoService_V2/getProcByCredBankDiscInfo_V2?serviceKey=" + serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&resultType=xml";
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
                log.info("BusiSuspDiscInfo totalCount:{}, pageNo: {}, pageSize:{}", totalCount, pageNo, Math.ceil((double) totalCount / numOfRows));
                if (pageNo == 1) {
                    totalCount = BatchUtil.getTotalCount(response.toString());
                }
                if (totalCount == (int) procByCredBankDiscInfoRepository.count()) {
                    break;
                } else {
                    this.ProcByCredBankDiscInfoProcessResponse(response.toString());
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
     * 12.금융위원회_공시정보 (채권은행등의관리절차개시공시정보조회) - API 응답 처리
     *
     * @param responseBody String
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException                 SAXException
     * @throws IOException                  IOException
     */
    private void ProcByCredBankDiscInfoProcessResponse(String responseBody) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(responseBody)));
        document.getDocumentElement().normalize();
        NodeList childList = document.getElementsByTagName("item");

        for (int i = 0; i < childList.getLength(); i++) {
            Node item = childList.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) item;
                procByCredBankDiscInfoRepository.save(ProcByCredBankDiscInfoEntity.builder()
                        .basDt(BatchUtil.getTagValue("basDt", element))
                        .bondMngInstNm(BatchUtil.getTagValue("bondMngInstNm", element))
                        .crbkMngCtt(BatchUtil.getTagValue("crbkMngCtt", element))
                        .crbkMngRsnCtt(BatchUtil.getTagValue("crbkMngRsnCtt", element))
                        .crno(BatchUtil.getTagValue("crno", element))
                        .ivsRefCtt(BatchUtil.getTagValue("ivsRefCtt", element))
                        .lwstAfrmDt(BatchUtil.getTagValue("lwstAfrmDt", element))
                        .mngOpngDecsDt(BatchUtil.getTagValue("mngOpngDecsDt", element))
                        .mngTermMnthCnt(BatchUtil.getTagValue("mngTermMnthCnt", element))
                        .rptFileCtt(BatchUtil.getTagValue("rptFileCtt", element))
                        .build());
            }
        }
    }

    /**
     * 13.금융위원회_공시정보 (소송등의제기공시정보조회)
     * @throws IOException IOException
     */
    public void LitiEtcDiscInfo() throws IOException {
        int pageNo = 1;
        int numOfRows = 2000;
        int totalCount = 0;
        do {
            String urlStr = serviceUrl + "/1160100/service/GetDiscInfoService_V2/getLitiEtcDiscInfo_V2?serviceKey="+ serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&resultType=xml";
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
                log.info("BusiSuspDiscInfo totalCount:{}, pageNo: {}, pageSize:{}", totalCount, pageNo, Math.ceil((double) totalCount / numOfRows));
                if (pageNo == 1) {
                    totalCount = BatchUtil.getTotalCount(response.toString());
                }
                if (totalCount == (int) litiEtcDiscInfoRepository.count()) {
                    break;
                } else {
                    this.LitiEtcDiscInfoProcessResponse(response.toString());
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
     * 13.금융위원회_공시정보 (소송등의제기공시정보조회) - API 응답 처리
     * @param responseBody String
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException SAXException
     * @throws IOException IOException
     */
    private void LitiEtcDiscInfoProcessResponse(String responseBody) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(responseBody)));
        document.getDocumentElement().normalize();
        NodeList childList = document.getElementsByTagName("item");

        for (int i = 0; i < childList.getLength(); i++) {
            Node item = childList.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) item;
                litiEtcDiscInfoRepository.save(LitiEtcDiscInfoEntity.builder()
                        .basDt(BatchUtil.getTagValue("basDt", element))
                        .crno(BatchUtil.getTagValue("crno", element))
                        .ivsRefCtt(BatchUtil.getTagValue("ivsRefCtt", element))
                        .lwstAfrmDt(BatchUtil.getTagValue("lwstAfrmDt", element))
                        .lwstAplpnNm(BatchUtil.getTagValue("lwstAplpnNm", element))
                        .lwstClmCtt(BatchUtil.getTagValue("lwstClmCtt", element))
                        .lwstIcdtNm(BatchUtil.getTagValue("lwstIcdtNm", element))
                        .lwstJurdCurtNm(BatchUtil.getTagValue("lwstJurdCurtNm", element))
                        .lwstRsltHcfhCtpnCtt(BatchUtil.getTagValue("lwstRsltHcfhCtpnCtt", element))
                        .lwstSggsDt(BatchUtil.getTagValue("lwstSggsDt", element))
                        .rptFileCtt(BatchUtil.getTagValue("rptFileCtt", element))
                        .build());
            }
        }
    }

    /**
     * 14.금융위원회_공시정보 (해외증권시장주권등상장공시정보조회)
     * @throws IOException IOException
     */
    public void OffsSecuMarkListDiscInfo() throws IOException {
        int pageNo = 1;
        int numOfRows = 2000;
        int totalCount = 0;
        do {
            String urlStr = serviceUrl + "/1160100/service/GetDiscInfoService_V2/getOffsSecuMarkListDiscInfo_V2?serviceKey="+ serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&resultType=xml";
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
                log.info("OffsSecuMarkListDiscInfo totalCount:{}, pageNo: {}, pageSize:{}", totalCount, pageNo, Math.ceil((double) totalCount / numOfRows));
                if (pageNo == 1) {
                    totalCount = BatchUtil.getTotalCount(response.toString());
                }
                if (totalCount == (int) offsSecuMarkListDiscInfoRepository.count()) {
                    break;
                } else {
                    this.OffsSecuMarkListDiscInfoProcessResponse(response.toString());
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
     * 14.금융위원회_공시정보 (해외증권시장주권등상장공시정보조회) - API 응답 처리
     * @param responseBody String
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException SAXException
     * @throws IOException IOException
     */
    private void OffsSecuMarkListDiscInfoProcessResponse(String responseBody) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(responseBody)));
        document.getDocumentElement().normalize();
        NodeList childList = document.getElementsByTagName("item");

        for (int i = 0; i < childList.getLength(); i++) {
            Node item = childList.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) item;
                offsSecuMarkListDiscInfoRepository.save(OffsSecuMarkListDiscInfoEntity.builder()
                        .arasBsisCtt(BatchUtil.getTagValue("arasBsisCtt", element))
                        .basDt(BatchUtil.getTagValue("basDt", element))
                        .crno(BatchUtil.getTagValue("crno", element))
                        .drIsinCdNm(BatchUtil.getTagValue("drIsinCdNm", element))
                        .enpCorpNm(BatchUtil.getTagValue("enpCorpNm", element))
                        .enpOvseXchgLstgDt(BatchUtil.getTagValue("enpOvseXchgLstgDt", element))
                        .etcCtt(BatchUtil.getTagValue("etcCtt", element))
                        .ivsRefCtt(BatchUtil.getTagValue("ivsRefCtt", element))
                        .lstgOnskCnt(BatchUtil.getTagValue("lstgOnskCnt", element))
                        .lstgOtshCnt(Integer.parseInt(Objects.requireNonNull(BatchUtil.getTagValue("lstgOtshCnt", element))))
                        .lstgXchgDwplNtnlNm(BatchUtil.getTagValue("lstgXchgDwplNtnlNm", element))
                        .lwstAfrmDt(BatchUtil.getTagValue("lwstAfrmDt", element))
                        .rptFileCtt(BatchUtil.getTagValue("rptFileCtt", element))
                        .stckCtt(BatchUtil.getTagValue("stckCtt", element))
                        .build());
            }
        }
    }
}
