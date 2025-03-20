package com.test.API_CSV.utils;

import com.test.API_CSV.dto.Producto;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Getter
@Setter
public class ExcelWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelWriter.class);

    private SXSSFWorkbook workbook;
    private SXSSFSheet sheet;
    private ExcelHelper excelHelper;
    private String fileLocationExcel;
    private FileOutputStream outputStream;
    private byte[] fileContent;

    public ExcelWriter() {
        this.workbook = new SXSSFWorkbook();
        this.sheet = this.workbook.createSheet();
        this.excelHelper = new ExcelHelper();
    }

    public void writeHeaderLine(String sheetName, List<String> headerNames) {
        LOGGER.info("writeHeaderLine");

        Row row = this.getSheet().createRow(0);
        CellStyle style = this.getWorkbook().createCellStyle();
        XSSFFont font = (XSSFFont) this.getWorkbook().findFont(true, (short) 0, (short) 200, "Calibri", false, false, (short) 0, (byte) 0);
        style.setFont(font);

        AtomicInteger columnCount = new AtomicInteger(0);

        headerNames.stream().forEach(headerName -> {
            this.getExcelHelper().createCell(row, columnCount.get(), headerName, style, this.getSheet());
            columnCount.getAndIncrement();
        });
        LOGGER.info("FIN writeHeaderLine");
    }

    public void writeDataLines(List<Producto> logsDtoList) {
        LOGGER.info("writeDataLines");
        AtomicInteger rowCount = new AtomicInteger(1);

        CellStyle style = this.getWorkbook().createCellStyle();
        XSSFFont font = (XSSFFont) this.getWorkbook().findFont(true, (short) 0, (short) 150, "Calibri", false, false, (short) 0, (byte) 0);
        style.setFont(font);

        logsDtoList.stream().forEach(Producto -> {
            Row row = getSheet().createRow(rowCount.getAndIncrement());
            int columnCount = 0;
            this.getExcelHelper().createCell(row, columnCount++, Producto.getId(), style, this.getSheet());
            this.getExcelHelper().createCell(row, columnCount++, Producto.getNombre(), style, this.getSheet());
            this.getExcelHelper().createCell(row, columnCount++, Producto.getPrecio(), style, this.getSheet());
            this.getExcelHelper().createCell(row, columnCount++, Producto.getStock(), style, this.getSheet());
        });
        LOGGER.info("FIN writeDataLines");
    }

    public byte[] writeFileExcel(String fileNameExcel) throws IOException {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        this.setFileLocationExcel(path.substring(0, path.length() - 1) + fileNameExcel);
        this.setOutputStream(new FileOutputStream(this.getFileLocationExcel()));
        this.getWorkbook().write(this.getOutputStream());
        this.setFileContent(Files.readAllBytes(Path.of(this.getFileLocationExcel())));
        return this.getFileContent();
    }

    public String getExcelFileLocation() {
        return this.getFileLocationExcel();
    }

    public FileOutputStream getFileOutputStreamExcel() {
        return this.getOutputStream();
    }

    public boolean closeFileExcel(FileOutputStream outputStream) throws IOException {
        this.outputStream.close();
        return true;
    }

}
