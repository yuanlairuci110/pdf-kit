package pdf.kit;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import pdf.kit.component.PDFHeaderFooter;
import pdf.kit.component.PDFKit;
import pdf.kit.component.chart.Line;
import pdf.kit.component.chart.impl.DefaultLineChart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fgm on 2017/4/17.
 * 360报告
 *
 */
@Slf4j
public class ReportKit360 {
	
	public static Logger log = Logger.getLogger(ReportKit360.class);

    public static List<Line> getTemperatureLineList() {
        List<Line> list= Lists.newArrayList();
        for(int i=1;i<=7;i++){
            Line line=new Line();
            float random=Math.round(Math.random()*10);
            line.setxValue("星期"+i);
            line.setyValue(20+random);
            line.setGroupName("下周");
            list.add(line);
        }
        for(int i=1;i<=7;i++){
            Line line=new Line();
            float random=Math.round(Math.random()*10);
            line.setxValue("星期"+i);
            line.setyValue(20+random);
            line.setGroupName("这周");
            list.add(line);
        }
        return list;
    }

    public  String createPDF(Object data, String fileName){
        //pdf保存路径
        try {
            //设置自定义PDF页眉页脚工具类
            PDFHeaderFooter headerFooter=new PDFHeaderFooter();
            PDFKit kit=new PDFKit();
            kit.setHeaderFooterBuilder(headerFooter);
            //设置输出路径
            kit.setSaveFilePath("D:/pdftest/hello.pdf");
            String saveFilePath=kit.exportToFile(fileName,data);
            return  saveFilePath;
        } catch (Exception e) {
        	System.out.println("竟然失败了，艹！");
        	e.printStackTrace();
//            log.error("PDF生成失败{}", ExceptionUtils.getFullStackTrace(e));
            log.error("PDF生成失败{}");
            return null;
        }
    }

    public static void main(String[] args) {
        ReportKit360 kit=new ReportKit360();
        TemplateBO templateBO=new TemplateBO();
        templateBO.setTemplateName("Hello iText! Hello freemarker! Hello jFreeChart!");
        templateBO.setFreeMarkerUrl("http://www.zheng-hang.com/chm/freemarker2_3_24/ref_directive_if.html");
        templateBO.setITEXTUrl("http://developers.itextpdf.com/examples-itext5");
        templateBO.setJFreeChartUrl("http://www.yiibai.com/jfreechart/jfreechart_referenced_apis.html");
        templateBO.setImageUrl("D:/pdftest/1.jpg");
        List<String> scores=new ArrayList<String>();
        scores.add("94");
        scores.add("95");
        scores.add("98");
        templateBO.setScores(scores);
        List<Line> lineList=getTemperatureLineList();
        DefaultLineChart lineChart=new DefaultLineChart();
        lineChart.setHeight(500);
        lineChart.setWidth(300);
        String picUrl=lineChart.draw(lineList,0);
        templateBO.setPicUrl(picUrl);System.out.println("picUrl:"+picUrl);
        String path= kit.createPDF(templateBO,"hello.pdf");
        System.out.println("打印："+path);
    }
}
