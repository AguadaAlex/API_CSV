package com.test.API_CSV.task;

import com.test.API_CSV.Constants;
import com.test.API_CSV.dto.Producto;
import com.test.API_CSV.services.ProductoServiceImplApiExterna;
import com.test.API_CSV.utils.DateUtil;
import com.test.API_CSV.utils.ExcelExporter;
import com.test.API_CSV.utils.ExcelWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
@Component
public class TaskSearchLogsImpl implements TaskSearchLogs{
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskSearchLogsImpl.class);

    private List<Producto> apiProductoList;
    private ExcelWriter excelWriter;
    @Autowired
    ProductoServiceImplApiExterna productoService;

    public byte[] createExcelFileWithData( String dateTimeFileName, List<Producto> logsDtoList) {
        LOGGER.info("[CREATING XSLX FILE]");
        excelWriter = new ExcelWriter();

        excelWriter.writeHeaderLine(dateTimeFileName + "_Productos", Constants.headerNames);
        excelWriter.writeDataLines(logsDtoList);
        try {
            byte[] fileContent = excelWriter.writeFileExcel(dateTimeFileName + "_Productos_" + ".xlsx");
            LOGGER.info("[XSLX FILE CREATED]");
            LOGGER.info("[XSLX FILE UBICACION]"+ this.excelWriter.getExcelFileLocation());
            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    @Scheduled(cron = "${cron.datadog.sync}")
    public void getLogsFromApiProductos() {

        try {
            LOGGER.info("[CONSULTA API PRODUCTOS]");
            apiProductoList = productoService.getProductos();

            // Verificar que logsList y sus propiedades no sean null
            if (apiProductoList != null ) {
                if (apiProductoList.size() > 0) {
                    LOGGER.info("[COMIENZO DE LOGICA ]");
                    LOGGER.info("************INICIO***********: " + apiProductoList);
                    //SE CREA FECHA PARA NOMBRE DE ARCHIVO
                    String dateFile = DateUtil.getCorrectOffsetDateTimeFilePrefix(OffsetDateTime.now().toString());
                    //SE REEMPLAZA ALGUNOS CARACTERES
                    String dateTimeFileName = dateFile.replace("T", "-").replace(":", "").replace(".", "-");
                    LOGGER.info("[FILE PREFIX NAME] => " + dateTimeFileName);
                    createExcelFileWithData( dateTimeFileName, apiProductoList);
                    LOGGER.info("************FIN*********** ");
                } else {
                    LOGGER.info("[CONSULTA SIN DATOS]");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
