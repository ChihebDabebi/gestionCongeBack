package com.tenstep.gestionConge.Services.impl;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class HolidaysService {
    private static final Logger logger = LoggerFactory.getLogger(HolidaysService.class);

    public Boolean validateDates(LocalDate dateDebut, LocalDate dateFin) {
        logger.info("Validating dates: dateDebut={}, dateFin={}", dateDebut, dateFin);

        try {
            List<String> datesStrings = Files.readAllLines(Paths.get("holidays.txt"));
            logger.info("Read holidays from file: {}", datesStrings);

            List<LocalDate> dates = datesStrings.stream()
                    .map(LocalDate::parse)
                    .toList();
            logger.info("Parsed holiday dates: {}", dates);

            if (dates.contains(dateDebut) || dates.contains(dateFin)) {
                logger.info("DateDebut or DateFin is a holiday: dateDebut={}, dateFin={}", dateDebut, dateFin);
                return false;
            } else if (dateFin.isBefore(dateDebut) || dateDebut.isBefore(LocalDate.now())) {
                logger.info("Invalid date range: dateDebut={}, dateFin={}", dateDebut, dateFin);
                return false;
            }

            for (LocalDate date : dates) {
                if (date.isAfter(dateDebut) && date.isBefore(dateFin)) {
                    logger.info("A holiday is within the range: {}", date);
                    return false;
                }
            }

        } catch (IOException e) {
            logger.error("Error reading holidays file", e);
        }

        logger.info("Date range is valid: dateDebut={}, dateFin={}", dateDebut, dateFin);
        return true;
    }
    public List<LocalDate> getHolidays () {
        List<LocalDate> holidays = null;
        try {
            List<String> datesStrings = Files.readAllLines(Paths.get("holidays.txt"));
            holidays = datesStrings.stream()
                    .map(LocalDate::parse)
                    .toList();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return holidays;
    }
}
