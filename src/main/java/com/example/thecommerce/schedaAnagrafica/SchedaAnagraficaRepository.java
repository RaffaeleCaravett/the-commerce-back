package com.example.thecommerce.schedaAnagrafica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedaAnagraficaRepository extends JpaRepository<SchedaAnagrafica,Long> {
    SchedaAnagrafica findByCodiceFiscale(String codiceFiscale);
    SchedaAnagrafica findByPartitaIva(String partitaIva);
    SchedaAnagrafica findByEmail(String email);
    SchedaAnagrafica findByUser_Id(long id);

}
