package com.herojoon.jpaproject.controller;

import com.herojoon.jpaproject.entity.StocIssuInfo;
import com.herojoon.jpaproject.repository.MemberRepository;
import com.herojoon.jpaproject.repository.StocIssuInfoRepository;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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


@Api(tags = "test")
@RequiredArgsConstructor
@RequestMapping("api/test")
@RestController
public class ApiController {
    public static final String USER_AGENT = "Mozilla/5.0";
    @Autowired
    private StocIssuInfoRepository stocIssuInfoRepository;

    @GetMapping("/api")
    public void api() throws IOException, ParserConfigurationException, SAXException {

        InputStreamReader isr = null;
        BufferedReader in = null;

        try {
            String body = "";
            String host = "https://apis.data.go.kr/";
            String service = "1160100/service/GetStocIssuInfoService_V2/getStocIssuInfo_V2";
            String serviceKey = "3NKSD4pMiU1dAnSi9YfhhEcZyp1uL2gFUk8wq7Iy3Nex4lGzhRXbYlaKnxUDb2P5IxztSaDkmL14JHAbRONlDw%3D%3D";
            String serviceUrl = host + service + "?serviceKey=" + serviceKey + "&pageNo=1&numOfRows=2000&resultType=xml&basDt=";

            System.out.println(serviceUrl);
            URL url = new URL(serviceUrl);

            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            //con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("CONTENT-TYPE", "text/xml");
            con.setDoOutput(true);
            con.setConnectTimeout(10000);
            con.setReadTimeout(5000);

            in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));

            String inputline;
            StringBuilder response = new StringBuilder();

            while ((inputline = in.readLine()) != null) {
                response.append(inputline.trim());
            }

            String responseBody = "";
            if (!response.toString().isEmpty()) {
                responseBody = response.toString();
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            System.out.println(responseBody);

            Document document = builder.parse(new InputSource(new StringReader(responseBody)));

            // root 요소 가져오기
//            Element root = document.getDocumentElement();
//            // root 요소 확인 : 첫 태그 sample
//            System.out.println(root.getNodeName());
//            // root 요소의 첫번째 노드는 #Text
//            Node firstNode = root.getFirstChild();
//            // 다음 노드는 customer
//            Node customer = firstNode.getNextSibling();
//            // customer 요소 안의 노드 리스트
//            NodeList childList = customer.getChildNodes();
            document.getDocumentElement().normalize();

            NodeList childList = document.getElementsByTagName("item");

            for(int i = 0; i < childList.getLength(); i++) {
                Node item = childList.item(i);
                if(item.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) item;
//                    System.out.println("isinCdNm >>> "+getTagValue("isinCdNm",element));
                    //System.out.println(item.getNodeName());
                    //System.out.println(item.getTextContent());

                    stocIssuInfoRepository.save(StocIssuInfo.builder()
                            .basDt(getTagValue("basDt",element))
                            .crno(getTagValue("crno",element))
                            .isinCd(getTagValue("isinCd",element))
                            .isinCdNm(getTagValue("isinCdNm",element))
                            .issuStckCnt(getTagValue("issuStckCnt",element))
                            .lstgDt(getTagValue("lstgDt",element))
                            .scrsDcd(getTagValue("scrsDcd",element))
                            .scrsItmsKcd(getTagValue("scrsItmsKcd",element))
                            .scrsItmsKcdNm(getTagValue("scrsItmsKcdNm",element))
                            .stckIssuCmpyNm(getTagValue("stckIssuCmpyNm",element))
                            .stckIssuDcnt(Integer.parseInt(Objects.requireNonNull(getTagValue("stckIssuDcnt", element))))
                            .stckIssuDt(getTagValue("stckIssuDt",element))
                            .stckIssuRcd(getTagValue("stckIssuRcd",element))
                            .stckIssuRcdNm(getTagValue("stckIssuRcdNm",element))
                            .stckIssuSqno(Integer.parseInt(Objects.requireNonNull(getTagValue("stckIssuSqno", element))))
                            .build());
                } else {
                    System.out.println("공백 입니다.");
                }
            }

            con.disconnect();

        } catch (IOException | ParserConfigurationException | SAXException ex) {
            throw ex;
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
    private String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }
}
