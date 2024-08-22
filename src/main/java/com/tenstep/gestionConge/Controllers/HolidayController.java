package com.tenstep.gestionConge.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenstep.gestionConge.Services.impl.HolidaysService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/holiday")
@RequiredArgsConstructor
public class HolidayController {

    private final HolidaysService holidaysService;
    private static final Logger logger = LoggerFactory.getLogger(HolidayController.class);

    @PostMapping("/save")
    public String saveHolidays(@RequestBody List<String> holidays) {
        logger.info("Received holidays: {}", holidays);

        try {
            Path file = Paths.get("holidays.txt");
            Files.write(file, holidays, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            logger.info("Holidays saved successfully to ", file.toAbsolutePath());
            return "Holidays saved successfully!";
        } catch (IOException e) {
            logger.error("Error saving holidays", e);
            return "Error saving holidays!";
        }
    }
    @GetMapping("/all")
    public List <LocalDate> getHolidays() {
       return  holidaysService.getHolidays();
    }

}
