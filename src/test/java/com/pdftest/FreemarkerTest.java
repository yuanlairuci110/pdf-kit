package com.pdftest;

import pdf.kit.TemplateBO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lichao on 2017/12/9.
 */
public class FreemarkerTest {
    public static void main(String[] args) {
        System.out.println("通过Freemarker生成PDF文件");
        TemplateBO templateBO = new TemplateBO();
        templateBO.setTemplateName("Hello iText! Hello freemarker! Hello jFreeChart!");
        templateBO.setITEXTUrl("http://developers.itextpdf.com/examples-itext5");
        templateBO.setFreeMarkerUrl("http://www.zheng-hang.com/chm/freemarker2_3_24/ref_directive_if.html");
        templateBO.setJFreeChartUrl("http://www.yiibai.com/jfreechart/jfreechart_referenced_apis.html");
        List<String> scores=new ArrayList<String>();
        scores.add("94");
        scores.add("95");
        scores.add("98");
        templateBO.setScores(scores);

    }
}
