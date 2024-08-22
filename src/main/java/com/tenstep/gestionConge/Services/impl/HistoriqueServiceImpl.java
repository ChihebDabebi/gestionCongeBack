package com.tenstep.gestionConge.Services.impl;

import com.tenstep.gestionConge.Models.Demande;
import com.tenstep.gestionConge.Models.Historique;
import com.tenstep.gestionConge.Repositories.HistoriqueRepository;
import com.tenstep.gestionConge.Services.HistoriqueService;
import com.tenstep.gestionConge.dto.HistoriqueDto;
import com.tenstep.gestionConge.mappers.HistoriqueMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class HistoriqueServiceImpl implements HistoriqueService {

    private final HistoriqueRepository historiqueRepository;

    public HistoriqueServiceImpl(HistoriqueRepository historiqueRepository){
        this.historiqueRepository = historiqueRepository;
    }
    @Override
    public HistoriqueDto addHistorique(HistoriqueDto historiqueDto) {
        Historique historique = HistoriqueMapper.mapToHistorique(historiqueDto);
        historique.setHistorique_id(UUID.randomUUID().toString().split("-")[0]);
        historiqueRepository.save(historique);
        return HistoriqueMapper.mapToHistoriqueDto(historique);
    }

    @Override
    public HistoriqueDto getHistoriqueById(String historique_id) {
       Historique historique =  historiqueRepository.findById(historique_id).orElseThrow(()-> new RuntimeException("aucune historique avec cet id "));
        return HistoriqueMapper.mapToHistoriqueDto(historique);
    }

    

    @Override
    public void deleteHistorique(String historique_id) {
        historiqueRepository.findById(historique_id).orElseThrow(()-> new RuntimeException("aucune historique avec cet id "));
        historiqueRepository.deleteById(historique_id);
    }
    @Override
    public List <Historique> getMyHistory(String id) {
        List<Historique> historiques = historiqueRepository.findByUserId(id);

        return historiques;
    }
}
