package com.tenstep.gestionConge.Services;


import com.tenstep.gestionConge.Models.Historique;
import com.tenstep.gestionConge.dto.HistoriqueDto;

import java.util.List;

public interface HistoriqueService {
    HistoriqueDto addHistorique(HistoriqueDto historiqueDto);
    HistoriqueDto getHistoriqueById(String historique_id);

    void deleteHistorique(String historique_id);

}
