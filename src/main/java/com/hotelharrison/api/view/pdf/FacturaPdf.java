package com.hotelharrison.api.view.pdf;

import com.hotelharrison.api.model.Consumo;
import com.hotelharrison.api.model.Factura;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FacturaPdf {

    private static final Logger logger = LoggerFactory.getLogger(FacturaPdf.class);

    private static final Color DARK_COLOR = new Color(0, 36, 51);
    private static final String FONT_FAMILY = FontFactory.HELVETICA;

    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    String date_now = formatter.format(new Date());

    public ByteArrayOutputStream getFacturaPdf(Factura factura, List<Consumo> consumos) {

        Document document = new Document(PageSize.A5, 40, 40, 40, 40);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfPTable table = new PdfPTable(4);
            table.setSpacingBefore(20);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{5, 3, 3, 3});

            Font headFont = FontFactory.getFont(FONT_FAMILY);
            headFont.setColor(new Color(255, 255, 255));
            PdfPCell hcell;

            hcell = new PdfPCell(new Phrase("Producto", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(DARK_COLOR);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Cantidad", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setVerticalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(DARK_COLOR);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Precio Unit.", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(DARK_COLOR);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("SubTotal", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(DARK_COLOR);
            table.addCell(hcell);

            Font cellFont = new Font();
            cellFont.setSize(10);
            cellFont.setColor(DARK_COLOR);

            double consumoPrecio = 0;

            for (Consumo consumo : consumos) {

                consumoPrecio += consumo.getTotal();

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(consumo.getProducto().getNombre(), cellFont));
                cell.setVerticalAlignment(Element.ALIGN_CENTER);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(consumo.getCantidad().toString(), cellFont));
                cell.setVerticalAlignment(Element.ALIGN_CENTER);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("S/".concat(consumo.getProducto().getPrecio().toString()), cellFont));
                cell.setVerticalAlignment(Element.ALIGN_CENTER);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("S/".concat(consumo.getTotal().toString()), cellFont));
                cell.setVerticalAlignment(Element.ALIGN_CENTER);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(232, 112, 143));
                table.addCell(cell);
            }

            PdfWriter.getInstance(document, out);
            document.open();

            document.addTitle("FACTURA - HOTEL HARRISON");

            Path pathPhoto = Paths.get("src/main/resources/static").resolve("logoHarrison.png").toAbsolutePath();
            Image photo = Image.getInstance(String.valueOf(pathPhoto));
            photo.scaleToFit(40, 40);

            Paragraph header = new Paragraph();
            header.add(new Chunk(photo, 0, -40));
            document.add(header);

            Paragraph header1 = new Paragraph("Usuario: Alejandro Carlos Mendoza",
                    FontFactory.getFont(FONT_FAMILY, 8, DARK_COLOR));
            header1.setAlignment(Element.ALIGN_RIGHT);
            document.add(header1);


            Paragraph header2 = new Paragraph("Fecha: " + date_now,
                    FontFactory.getFont(FONT_FAMILY, 8, DARK_COLOR));
            header2.setAlignment(Element.ALIGN_RIGHT);
            document.add(header2);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            Paragraph title = new Paragraph("Hotel Harrison",
                    FontFactory.getFont(FONT_FAMILY, 20, Font.BOLD, DARK_COLOR));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Paragraph subtitle = new Paragraph("Factura Electronica",
                    FontFactory.getFont(FONT_FAMILY, 10, Font.UNDERLINE, DARK_COLOR));
            subtitle.setAlignment(Element.ALIGN_CENTER);
            document.add(subtitle);

            Paragraph blank = new Paragraph(" ",
                    FontFactory.getFont(FONT_FAMILY, 10, DARK_COLOR));
            document.add(blank);

            Paragraph fechaInicio = new Paragraph("Fecha Inicio: ".concat(factura.getReserva().getFechaInicio().toString()),
                    FontFactory.getFont(FONT_FAMILY, 10, DARK_COLOR));
            document.add(fechaInicio);

            Paragraph fechaFinal = new Paragraph("Fecha Final: ".concat(factura.getReserva().getFechaFinal().toString()),
                    FontFactory.getFont(FONT_FAMILY, 10, DARK_COLOR));
            document.add(fechaFinal);

            Paragraph huesped1 = new Paragraph("Huésped: ".concat(factura.getReserva().getHuesped().getNombre().concat(" ").concat(factura.getReserva().getHuesped().getApellido())),
                    FontFactory.getFont(FONT_FAMILY, 10, DARK_COLOR));
            document.add(huesped1);

            Paragraph huesped2 = new Paragraph("Documento: ".concat(factura.getReserva().getHuesped().getDocumento()),
                    FontFactory.getFont(FONT_FAMILY, 10, DARK_COLOR));
            document.add(huesped2);

            Paragraph habitacion = new Paragraph("Habitación: (".concat(factura.getReserva().getHabitacion().getNombre().concat(") ").concat(factura.getReserva().getHabitacion().getTipoHabitacion().getNombre()).concat(" - S/").concat(factura.getReserva().getHabitacion().getTipoHabitacion().getPrecio().toString())),
                    FontFactory.getFont(FONT_FAMILY, 10, DARK_COLOR));
            document.add(habitacion);

            Paragraph precioEstadía = new Paragraph("Precio Estadía: S/".concat(factura.getReserva().getPrecioTotal().toString()),
                    FontFactory.getFont(FONT_FAMILY, 11, new Color(0, 92, 10)));
            document.add(precioEstadía);

            document.add(table);

            document.add(blank);

            Paragraph totalConsumo = new Paragraph("Total Consumo: S/".concat(String.valueOf(consumoPrecio)),
                    FontFactory.getFont(FONT_FAMILY, 11, new Color(0, 92, 10)));
            document.add(totalConsumo);

            PdfPTable tableFooter = new PdfPTable(1);
            tableFooter.setSpacingBefore(20);
            tableFooter.setWidthPercentage(100);

            Phrase footer = new Phrase("PRECIO TOTAL: S/".concat(String.valueOf(consumoPrecio + factura.getReserva().getPrecioTotal())),
                    FontFactory.getFont(FONT_FAMILY, 15, Font.BOLD, new Color(165, 0, 47)));

            PdfPCell cellFooter = new PdfPCell(footer);
            cellFooter.setPadding(20);
            cellFooter.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellFooter.setVerticalAlignment(Element.ALIGN_CENTER);
            tableFooter.addCell(cellFooter);

            document.add(tableFooter);

            document.close();
        } catch (DocumentException | IOException ex) {
            logger.error("Error occurred: ", ex);
        }
        return out;
    }
}