package teste.pratico.atendimento.util;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class ReportUtils {

    public static byte[] generateReportJasper(String report, List<?> list) throws FileNotFoundException, JRException, FileNotFoundException {

        JRBeanCollectionDataSource collection = new JRBeanCollectionDataSource(list);

        String pathReport = String.format("src/main/resources/reports/%s.jasper", report);

        InputStream jasperTemplate = new FileInputStream(pathReport);

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

        JasperPrint jp = JasperFillManager.fillReport(jasperTemplate ,parametros, collection);

//        JasperExportManager.exportReportToPdfFile(jp, "D:\\teste.pdf");

        return JasperExportManager.exportReportToPdf(jp);

    }

}
