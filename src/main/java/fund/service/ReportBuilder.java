package fund.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.export.type.HtmlSizeUnitEnum;

public class ReportBuilder {
    static String reportFolderPath;
    String reportFileName;
    JRDataSource dataSource;
    HttpServletRequest request;
    HttpServletResponse response;
    JasperReport report;
    
    String fileName="report";
    
    public ReportBuilder(String reportFileName, Collection<?> collection, String name, 
            HttpServletRequest request, HttpServletResponse response) throws JRException {
        this.reportFileName = reportFileName;
        this.dataSource = new JRBeanCollectionDataSource(collection);
        this.request = request;
        this.response = response;
        this.fileName=name;
        if (reportFolderPath == null)
            reportFolderPath = request.getSession().getServletContext().getRealPath("/WEB-INF/report");
        String reportFilePath = reportFolderPath + "/" + reportFileName + ".jasper";
        this.report = (JasperReport)JRLoader.loadObjectFromFile(reportFilePath);
    }
    
    public void build(String type) throws JRException, IOException {
        switch (type) {
        case "pdf": buildPDFReport(); break;
        case "xlsx": buildXlsxReport(); break;
        default: buildHtmlReport(); break;
        }        
    }
    
    public void buildHtmlReport() throws JRException, IOException {
        response.setCharacterEncoding("UTF-8");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put(JRParameter.IS_IGNORE_PAGINATION, true);
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
        List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
        jasperPrintList.add(jasperPrint);
        HtmlExporter exporter = new HtmlExporter();
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
        SimpleHtmlReportConfiguration configuration = new SimpleHtmlReportConfiguration();
        configuration.setIgnorePageMargins(true);
        configuration.setBorderCollapse("collapse");
        configuration.setSizeUnit(HtmlSizeUnitEnum.POINT);
        exporter.setConfiguration(configuration);
        exporter.exportReport();
    }
    
    public void buildXlsxReport() throws IOException, JRException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put(JRParameter.IS_IGNORE_PAGINATION, true);        
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
        List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
        jasperPrintList.add(jasperPrint);
        JRXlsxExporter  exporter = new JRXlsxExporter (); 
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();        
        configuration.setOnePagePerSheet(false);
        configuration.setIgnorePageMargins(true);
        configuration.setIgnoreTextFormatting(true);
        configuration.setRemoveEmptySpaceBetweenColumns(true);
        configuration.setRemoveEmptySpaceBetweenRows(true);
        configuration.setWhitePageBackground(false);
        configuration.setCollapseRowSpan(false);
        exporter.setConfiguration(configuration);
        exporter.exportReport();
    }
    
    public void buildPDFReport() throws JRException, IOException {
        byte[] bytes = null;
        bytes = JasperRunManager.runReportToPdf(report, null, dataSource);
        response.setContentType("application/pdf");        
        response.setContentLength(bytes.length);
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";");
        ServletOutputStream ouputStream = response.getOutputStream();
        ouputStream.write(bytes, 0, bytes.length);
        ouputStream.flush();
        ouputStream.close();
    }      
}
