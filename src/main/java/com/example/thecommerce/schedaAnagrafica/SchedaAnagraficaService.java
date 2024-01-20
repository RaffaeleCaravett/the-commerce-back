package com.example.thecommerce.schedaAnagrafica;

import com.example.thecommerce.enums.UserRoles;
import com.example.thecommerce.exception.BadRequestException;
import com.example.thecommerce.payloads.entities.SchedaAnagraficaDTO;
import com.example.thecommerce.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedaAnagraficaService {

    @Autowired
    SchedaAnagraficaRepository schedaAnagraficaRepository;
    @Autowired
    UserRepository userRepository;



    public SchedaAnagrafica save (SchedaAnagrafica schedaAnagrafica, SchedaAnagraficaDTO schedaAnagraficaDTO){
        if(schedaAnagraficaRepository.findByEmail(schedaAnagraficaDTO.email())!=null){
            throw new BadRequestException("A questa email è già associato una scheda anagrafica");
        }
        schedaAnagrafica.setNome(schedaAnagraficaDTO.nome());
        schedaAnagrafica.setCognome(schedaAnagraficaDTO.cognome());
        schedaAnagrafica.setEmail(schedaAnagraficaDTO.email());
        schedaAnagrafica.setRole(UserRoles.valueOf(schedaAnagraficaDTO.role()));
        schedaAnagrafica.setCap(schedaAnagraficaDTO.cap());
        if(schedaAnagraficaDTO.capitaleSociale()!=0){
            schedaAnagrafica.setCapitaleSociale(schedaAnagraficaDTO.capitaleSociale());
        }
        schedaAnagrafica.setIndirizzo(schedaAnagraficaDTO.indirizzo());
        schedaAnagrafica.setNumeroCivico(schedaAnagraficaDTO.numeroCivico());
        schedaAnagrafica.setVia(schedaAnagraficaDTO.via());
        schedaAnagrafica.setUser(userRepository.findById(schedaAnagraficaDTO.user_id()).get());

        return schedaAnagraficaRepository.save(schedaAnagrafica);
    }

    public SchedaAnagrafica findById(long id){
        return schedaAnagraficaRepository.findById(id).get();
    }

    public SchedaAnagrafica updateById(long id, SchedaAnagraficaDTO schedaAnagraficaDTO){
        SchedaAnagrafica schedaAnagrafica=schedaAnagraficaRepository.findById(id).get();

        if(schedaAnagraficaDTO.codiceFiscale()!=null){
            SchedaAnagrafica schedaAnagrafica1=schedaAnagraficaRepository.findByCodiceFiscale(schedaAnagraficaDTO.codiceFiscale());
        if(schedaAnagrafica1!=null && !schedaAnagrafica1.equals(schedaAnagrafica)){
            throw new BadRequestException("Il codice fiscale appartiene ad un'altra scheda anagrafica");
        }
        }else if(schedaAnagraficaDTO.partitaIva()!=null){
            SchedaAnagrafica schedaAnagrafica1=schedaAnagraficaRepository.findByPartitaIva(schedaAnagraficaDTO.partitaIva());
            if(schedaAnagrafica1!=null && !schedaAnagrafica1.equals(schedaAnagrafica)){
                throw new BadRequestException("La partita iva appartiene ad un'altra scheda anagrafica");
            }
        }else if(schedaAnagraficaRepository.findByEmail(schedaAnagraficaDTO.email())!=null&& schedaAnagraficaRepository.findByEmail(schedaAnagraficaDTO.email()).getId()!=id){
            throw new BadRequestException("L'email che hai inserito corrisponde ad un'altra scheda anagrafica.");
        }
        schedaAnagrafica.setNome(schedaAnagraficaDTO.nome());
        schedaAnagrafica.setCognome(schedaAnagraficaDTO.cognome());
        schedaAnagrafica.setEmail(schedaAnagraficaDTO.email());
        schedaAnagrafica.setRole(UserRoles.valueOf(schedaAnagraficaDTO.role()));
        schedaAnagrafica.setCap(schedaAnagraficaDTO.cap());
        if(schedaAnagraficaDTO.capitaleSociale()!=0){
            schedaAnagrafica.setCapitaleSociale(schedaAnagraficaDTO.capitaleSociale());
        }
        schedaAnagrafica.setCodiceFiscale(schedaAnagraficaDTO.codiceFiscale());
        schedaAnagrafica.setPartitaIva(schedaAnagraficaDTO.partitaIva());
        schedaAnagrafica.setIndirizzo(schedaAnagraficaDTO.indirizzo());
        schedaAnagrafica.setNumeroCivico(schedaAnagraficaDTO.numeroCivico());
        schedaAnagrafica.setVia(schedaAnagraficaDTO.via());
        schedaAnagrafica.setUser(userRepository.findById(schedaAnagraficaDTO.user_id()).get());

        return schedaAnagraficaRepository.save(schedaAnagrafica);
    }

}
