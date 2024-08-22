package com.tenstep.gestionConge.Controllers;

import com.lowagie.text.DocumentException;
import com.tenstep.gestionConge.Models.Demande;
import com.tenstep.gestionConge.Models.Historique;
import com.tenstep.gestionConge.Services.HistoriqueService;
import com.tenstep.gestionConge.utils.PdfGenerator;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoriqueController {

    private final HistoriqueService historiqueService;


    @GetMapping("/{id}/myHistory")
    public List<Historique> getMyDemands(@PathVariable String id) {
        return historiqueService.getMyHistory(id);
    }
    @GetMapping("/{id}/export")
    public void generatePdfFile(HttpServletResponse response, @PathVariable String id) throws DocumentException, IOException
    {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);
        List < Historique > listofHistoriques = historiqueService.getMyHistory(id);
        PdfGenerator generator = new PdfGenerator();
        generator.generate(listofHistoriques, response);
    }
}
