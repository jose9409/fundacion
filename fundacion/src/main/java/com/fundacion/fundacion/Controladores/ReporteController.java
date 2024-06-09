package com.fundacion.fundacion.Controladores;

import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fundacion.fundacion.Entidades.ProcesoAdopcion;
import com.fundacion.fundacion.Entidades.RegistroAdoptante;
import com.fundacion.fundacion.Repositorios.ProcesoAdopcionRepository;
import com.fundacion.fundacion.Repositorios.RegistroAdoptanteRepository;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/reporte")
public class ReporteController {

    private final RegistroAdoptanteRepository registroAdoptanteRepository;
    private final ProcesoAdopcionRepository procesoAdopcionRepository;

    @Autowired
    public ReporteController(RegistroAdoptanteRepository registroAdoptanteRepository, ProcesoAdopcionRepository procesoAdopcionRepository) {
        this.registroAdoptanteRepository = registroAdoptanteRepository;
        this.procesoAdopcionRepository = procesoAdopcionRepository;
    }

    @GetMapping("/adoptantes/excel")
    public ResponseEntity<byte[]> generarReporteAdoptantesExcel() {
        List<RegistroAdoptante> adoptantes = registroAdoptanteRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Adoptantes");

            // Encabezados
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Tipo Documento", "Num Cedula", "Primer Nombre", "Segundo Nombre",
                                "Primer Apellido", "Segundo Apellido", "Telefono", "Correo",
                                "Dirección", "Barrio", "Fecha Nacimiento", "Contraseña", "Genero", "Estado"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Datos
            int rowNum = 1;
            for (RegistroAdoptante adoptante : adoptantes) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(adoptante.getIdregistro_adoptante());
                row.createCell(1).setCellValue(adoptante.getTipo_documento());
                row.createCell(2).setCellValue(adoptante.getNum_cedula());
                row.createCell(3).setCellValue(adoptante.getPrimer_nombre());
                row.createCell(4).setCellValue(adoptante.getSegundo_nombre());
                row.createCell(5).setCellValue(adoptante.getPrimer_apellido());
                row.createCell(6).setCellValue(adoptante.getSegundo_apellido());
                row.createCell(7).setCellValue(adoptante.getTelefono());
                row.createCell(8).setCellValue(adoptante.getCorreo());
                row.createCell(9).setCellValue(adoptante.getDirreccion());
                row.createCell(10).setCellValue(adoptante.getBarrio());
                row.createCell(11).setCellValue(adoptante.getFecha_nacimiento().toString());
                row.createCell(12).setCellValue(adoptante.getContraseña());
                row.createCell(13).setCellValue(adoptante.getGenero());
                row.createCell(14).setCellValue(adoptante.getEstado());
            }

            // Convertir el workbook a un arreglo de bytes
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            byte[] bytes = outputStream.toByteArray();

            // Configurar los encabezados de la respuesta para que se descargue el archivo
            HttpHeaders headers1 = new HttpHeaders();
            headers1.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers1.setContentDispositionFormData("attachment", "reporte_adoptantes.xlsx");

            // Crear la respuesta con el archivo como cuerpo
            return new ResponseEntity<>(bytes, headers1, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al generar el reporte.".getBytes(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/procesos/excel")
    public ResponseEntity<byte[]> generarReporteProcesosExcel() {
        List<ProcesoAdopcion> procesos = procesoAdopcionRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Procesos de Adopción");

            // Encabezados
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID Proceso", "Fecha Inicio", "Fecha Fin", "Estado", "ID Animal", "Nombre Animal",
                                "ID Adoptante", "Nombre Adoptante", "Avances del proceso"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Datos
            int rowNum = 1;
            for (ProcesoAdopcion proceso : procesos) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(proceso.getIdproceso_adoptcion());
                row.createCell(1).setCellValue(proceso.getFecha_inicio().toString());
                row.createCell(2).setCellValue(proceso.getFecha_fin().toString());
                row.createCell(3).setCellValue(proceso.getEstado());
                row.createCell(4).setCellValue(proceso.getAnimales().getIdanimales());
                row.createCell(5).setCellValue(proceso.getAnimales().getNombre());
                row.createCell(6).setCellValue(proceso.getRegistroAdoptante().getIdregistro_adoptante());
                row.createCell(7).setCellValue(proceso.getRegistroAdoptante().getPrimer_nombre() + " " + proceso.getRegistroAdoptante().getPrimer_apellido());
                row.createCell(8).setCellValue(proceso.getDescripcion());
            }

            // Convertir el workbook a un arreglo de bytes
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            byte[] bytes = outputStream.toByteArray();

            // Configurar los encabezados de la respuesta para que se descargue el archivo
            HttpHeaders headers1 = new HttpHeaders();
            headers1.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers1.setContentDispositionFormData("attachment", "reporte_procesos_adopcion.xlsx");

            // Crear la respuesta con el archivo como cuerpo
            return new ResponseEntity<>(bytes, headers1, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al generar el reporte.".getBytes(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}