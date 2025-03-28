package milenyumsoft.plataformaeducativa.repository;

import milenyumsoft.plataformaeducativa.modelo.Profesor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfesorRepository extends JpaRepository<Profesor,Long> {


    @Query("SELECT p FROM Profesor p WHERE p.codigoProfesor = :codigoProfesor ")
    public Profesor traerProfesorCodigo(@Param("codigoProfesor") String codigoProfesor);

}
