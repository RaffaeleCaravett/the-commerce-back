package com.example.thecommerce.schedaAnagrafica;

import com.example.thecommerce.enums.UserRoles;
import com.example.thecommerce.exception.BadRequestException;
import com.example.thecommerce.payloads.entities.SchedaAnagraficaDTO;
import com.example.thecommerce.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("scheda")
public class SchedaAnagraficaController {

@Autowired
    SchedaAnagraficaService schedaAnagraficaService;
@Autowired
SchedaAnagraficaRepository schedaAnagraficaRepository;
@Autowired
    UserRepository userRepository;

@GetMapping("/{id}")
@PreAuthorize("hasAnyAuthority('UTENTE','VENDITORE')")
    public SchedaAnagrafica getById(@PathVariable long id){
    return schedaAnagraficaService.findById(id);
}

@PostMapping("")
@PreAuthorize("hasAnyAuthority('UTENTE','VENDITORE')")
public long save(@RequestBody @Validated SchedaAnagraficaDTO schedaAnagraficaDTO, BindingResult validation){
    SchedaAnagrafica schedaAnagrafica=new SchedaAnagrafica();
    if(validation.hasErrors()){
        throw new BadRequestException(validation.getAllErrors());
    }else if(schedaAnagraficaDTO.codiceFiscale() != null){
        if(schedaAnagraficaRepository.findByCodiceFiscale(schedaAnagraficaDTO.codiceFiscale())!=null){
            throw new BadRequestException("Codice fiscale già presente nel database");
        }else{
            schedaAnagrafica.setCodiceFiscale(schedaAnagraficaDTO.codiceFiscale());
        }
    }else if(schedaAnagraficaDTO.partitaIva()!=null){
        if(schedaAnagraficaRepository.findByPartitaIva(schedaAnagraficaDTO.partitaIva())!=null){
            throw new BadRequestException("Partita Iva già presente nel database");
        }else{
            schedaAnagrafica.setPartitaIva(schedaAnagraficaDTO.partitaIva());
        }
    }else if(schedaAnagraficaDTO.partitaIva()==null&&schedaAnagraficaDTO.codiceFiscale()==null){
        throw new BadRequestException("Deve esserci almeno il Codice Fiscale o la partita Iva");
    }

    return schedaAnagraficaService.save(schedaAnagrafica,schedaAnagraficaDTO).getId();
}

@DeleteMapping("/{id}")
@PreAuthorize("hasAnyAuthority('UTENTE','VENDITORE')")
public boolean deleteById(@PathVariable long id){
    try {
        schedaAnagraficaRepository.deleteById(id);
        return true;
    }catch (Exception e){
        return false;
    }
}

@PutMapping("/{id}")
@PreAuthorize("hasAnyAuthority('UTENTE','VENDITORE')")
    public SchedaAnagrafica updateById(@PathVariable long id, @RequestBody SchedaAnagraficaDTO schedaAnagraficaDTO){

    return schedaAnagraficaService.updateById(id,schedaAnagraficaDTO);
}

    @GetMapping("/codiceFiscale/{codiceFiscale}")
    @PreAuthorize("hasAnyAuthority('UTENTE','VENDITORE')")
    public SchedaAnagrafica getByCodiceFiscale(@PathVariable String codiceFiscale){
        return schedaAnagraficaRepository.findByCodiceFiscale(codiceFiscale);
    }
    @GetMapping("/partitaIva/{partitaIva}")
    @PreAuthorize("hasAnyAuthority('UTENTE','VENDITORE')")
    public SchedaAnagrafica getByPartitaIva(@PathVariable String partitaIva){
        return schedaAnagraficaRepository.findByPartitaIva(partitaIva);
    }
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyAuthority('UTENTE','VENDITORE')")
    public SchedaAnagrafica getByUserId(@PathVariable long userId){
        return schedaAnagraficaRepository.findByUser_Id(userId);
    }
}
