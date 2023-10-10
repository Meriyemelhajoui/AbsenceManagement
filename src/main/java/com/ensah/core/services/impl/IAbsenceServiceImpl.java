package com.ensah.core.services.impl;
import com.ensah.core.bo.Absence;
import com.ensah.core.dao.IAbsenceDao;
import com.ensah.core.services.IAbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class IAbsenceServiceImpl implements IAbsenceService {

    @Autowired
    IAbsenceDao absenceDao;


    @Override
    public Absence enregistrerAbsence(Absence absence) {
         return absenceDao.save(absence);
    }

    @Override
    public List<Absence> getAllAbsences() {
        return absenceDao.findAll();
    }

    @Override
    public List<Absence> getAbsencesByIdInscription(Long id) {
        return absenceDao.findAbsencesByIdInscription(id);
    }

    @Override
    public Absence getAbsenceByIdAbsence(long id) {
        return absenceDao.findAbsenceByIdAbsence(id);
    }


    @Transactional  // Ensures that the deletion will be done
    @Override
    public void SupprimerAbsenceByIdAbsence(Long id) {

        absenceDao.deleteAbsenceByIdAbsence(id);
    }


}
