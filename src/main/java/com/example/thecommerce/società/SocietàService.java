package com.example.thecommerce.società;

import com.example.thecommerce.payloads.entities.SocietàDTO;
import com.example.thecommerce.schedaAnagrafica.SchedaAnagraficaRepository;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SocietàService{

    @Autowired
    SocietàRepository societàRepository;

    @Autowired
    SchedaAnagraficaRepository schedaAnagraficaRepository;
    public Società save (SocietàDTO societàDTO){
        Società società=new Società();

        società.setNome(societàDTO.nome());
        società.setSchedaAnagrafica(schedaAnagraficaRepository.findById(societàDTO.scheda_anagrafica_id()).get());

        return societàRepository.save(società);
    }
    public Page<Società> getSocietà(int page , int size, String ordedBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(ordedBy));
        return societàRepository.findAll(pageable);
    }

    public Società getSocietàBySchedaAnagraficaId(long id){
        return societàRepository.findBySchedaAnagrafica_id(id);
    }
public boolean deleteById(long id){
        try {
            societàRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
}

public Società updateById(long id, SocietàDTO societàDTO){
        Società società = new Società();

        società.setNome(societàDTO.nome());
        società.setSchedaAnagrafica(schedaAnagraficaRepository.findById(societàDTO.scheda_anagrafica_id()).get());

        return societàRepository.save(società);
}

}
