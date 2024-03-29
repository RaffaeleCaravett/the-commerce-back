package com.example.thecommerce.nation;

import com.example.thecommerce.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

import java.util.List;

@Service
public class NationService {
    @Autowired
    private NationRepository nationRepository;

    public List<Nation> getNations() {
        return nationRepository.findAll();
    }

    public Nation findById(long id) throws NotFoundException {
        return nationRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public Nation save(Nation nation) {
        return nationRepository.save(nation);
    }

    public void findByIdAndDelete(long id) throws NotFoundException {
        Nation found = this.findById(id);
        nationRepository.delete(found);
    }

    public Nation findNationsByCityId(int id) {
        return nationRepository.findByCities_Id(id);
    }
}
