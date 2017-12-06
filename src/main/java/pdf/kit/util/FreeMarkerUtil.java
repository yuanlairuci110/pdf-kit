package pdf.kit.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang.StringUtils;
import pdf.kit.component.PDFKit;
import pdf.kit.exception.FreeMarkerException;
import pdf.kit.exception.PDFException;

import java.io.File;
import java.io.StringWriter;

/**
 * Created by fgm on 2017/4/22.
 * FREEMARKER 模板工具类
 *
 */
public class FreeMarkerUtil {

    /**
     * @description 获取模板
     */
    public static String getContent(String fileName,Object data){
    	
        String templatePath=getPDFTemplatePath(fileName).replace("\\", "/");
        String templateFileName=getTemplateName(templatePath).replace("\\", "/");
        String templateFilePath=getTemplatePath(templatePath).replace("\\", "/");
        System.out.println("templatePath:"+templatePath);
        System.out.println("templateFileName:"+templateFileName);
        System.out.println("templateFilePath:"+templateFilePath);
        if(StringUtils.isEmpty(templatePath)){
            throw new FreeMarkerException("templatePath can not be empty!");
        }
        try{System.out.println("进到这里了，有来无回1");
            Configuration config = new Configuration(Configuration.VERSION_2_3_25);
            config.setDefaultEncoding("UTF-8");
            config.setDirectoryForTemplateLoading(new File(templateFilePath));
            config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            config.setLogTemplateExceptions(false);System.out.println("进到这里了，有来无回2");
            Template template = config.getTemplate(templateFileName);System.out.println("进到这里了，有来无回3");
            StringWriter writer = new StringWriter();
            template.process(data, writer);
            writer.flush();
            String html = writer.toString();
            return html;
        }catch (Exception ex){
            throw new FreeMarkerException("FreeMarkerUtil process fail",ex);
        }
    }

    private static String getTemplatePath(String templatePath) {
        if(StringUtils.isEmpty(templatePath)){
            return "";
        }
        String path = "";
        int ss = templatePath.lastIndexOf("/");
        if(ss==-1){
        	path=templatePath.substring(0,templatePath.lastIndexOf("\\"));
        } else {
        	path=templatePath.substring(0,templatePath.lastIndexOf("/"));
        }
        System.out.println("八嘎呀路path:"+path);
        return path;
    }

    private static String getTemplateName(String templatePath) {
        if(StringUtils.isEmpty(templatePath)){
            return "";
        }
        String fileName=templatePath.substring(templatePath.lastIndexOf("/")+1);
        return fileName;
    }

    /**
     * @description 获取PDF的模板路径,
     * 默认按照PDF文件名匹对应模板
     * @param fileName PDF文件名    (hello.pdf)
     * @return         匹配到的模板名
     */
    public static String getPDFTemplatePath(String fileName){
        String  classpath=PDFKit.class.getClassLoader().getResource("").getPath();
        String templatePath=classpath+"templates";
        System.out.println("战神归来009："+templatePath);
        File file=new File(templatePath);
        if(!file.isDirectory()){
            throw new PDFException("PDF模板文件不存在,请检查templates文件夹!");
        }System.out.println("能进到这里吗？");
        String pdfFileName=fileName.substring(0,fileName.lastIndexOf("."));
        File defaultTemplate=null;
        File matchTemplate=null;
        for(File f:file.listFiles()){
            if(!f.isFile()){
                continue;
            }
            String templateName=f.getName();
            if(templateName.lastIndexOf(".ftl")==-1){
                continue;
            }
            if(defaultTemplate==null){
                defaultTemplate=f;
            }
            if(StringUtils.isEmpty(fileName)&&defaultTemplate!=null){
                break;
            }
            templateName=templateName.substring(0,templateName.lastIndexOf("."));
            if(templateName.toLowerCase().equals(pdfFileName.toLowerCase())){
                matchTemplate=f;
                break;
            }
        }
        if(matchTemplate!=null){System.out.println("八嘎雅鹿111");
        	System.out.println("八嘎雅鹿111："+matchTemplate.getAbsolutePath());
            return matchTemplate.getAbsolutePath();
        }
        if(defaultTemplate!=null){System.out.println("八嘎雅鹿222");
            return defaultTemplate.getAbsolutePath();
        }System.out.println("八嘎雅鹿333");
        return null;
    }

}
