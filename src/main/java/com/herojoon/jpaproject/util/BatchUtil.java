package com.herojoon.jpaproject.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class BatchUtil {
    /**
     * 태그 값 조회
     * @param tag tag
     * @param eElement eElement
     * @return tag value
     */
    public static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = nlList.item(0);
        return nValue == null ? null : BatchUtil.MinusRemove(nValue.getNodeValue());
    }

    /**
     * 노드의 예외 처리를 위한 유틸
     * @param nodeValue nodeValue
     * @return nodeValue
     */
    private static String MinusRemove(String nodeValue) {
        if (nodeValue.length() < 2){
            if (nodeValue.equals("-")){
                return "0.0";
            }else{
                return nodeValue;
            }
        }else{
            return nodeValue;
        }
    }

    /**
     * 총 건수 조회
     * @param response response
     * @return totalCount
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException SAXException
     * @throws IOException IOException
     */
    public static int getTotalCount(String response) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(response)));
        document.getDocumentElement().normalize();

        NodeList totalCountList = document.getElementsByTagName("totalCount");
        if (totalCountList.getLength() > 0) {
            return Integer.parseInt(totalCountList.item(0).getTextContent());
        }
        return 0;
    }
}
