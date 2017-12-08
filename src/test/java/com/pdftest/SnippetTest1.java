package com.pdftest;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;


/**
 * Created by lichao on 2017/12/8.
 *
 * 根据模版pdf生成pdf
 *
 */
public class SnippetTest1 {
    // 利用模板生成pdf
    public static void fillTemplate() {
        // 模板路径
        String templatePath = "D:/pdftest/模版.pdf";
        // 生成的新文件路径
        String newPDFPath = "D:/pdftest/ceshi.pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            out = new FileOutputStream(newPDFPath);// 输出流
            reader = new PdfReader(templatePath);// 读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            BaseFont bf = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            form.addSubstitutionFont(bf);
            String[] str = { "123456789", "TOP__ONE", "男", "1991-01-01", "130222111133338888", "河北省保定市" };
            int i = 0;
            HashMap<String, AcroFields.Item> map = (HashMap<String, AcroFields.Item>) form.getFields();
            for(String key : map.keySet()){
                System.out.println("key : "+key +"  value : "+map.get(key));
                if(key.equals("id")){
                    form.setField(key, "1111");
                }else if(key.equals("name")){
                    form.setField(key, "    姓名222221");
                }else if(key.equals("sex")){
                    form.setField(key, "     333");
                }else if(key.equals("birthday")){
                    form.setField(key, "       4444");
                }else if(key.equals("cardId")){
                    form.setField(key, "     5555");
                }else if(key.equals("address")){
                    form.setField(key, "      999");
                }
            }

            //java.util.Iterator<String> it = form.getFields().keySet().iterator();
            //while (it.hasNext()) {
            //    String name = it.next().toString();
            //    System.out.println(name);
            //    form.setField(name, str[i++]);
            //}
            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();

            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();

        } catch (IOException e) {
            System.out.println(1);
        } catch (DocumentException e) {
            System.out.println(2);
        }

    }

    public static void main(String[] args) {
        fillTemplate();
    }
}
