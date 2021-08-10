package nl.molens.jpa;

import org.springframework.data.repository.CrudRepository;
import nl.molens.model.Molen;

public interface MolenRepository extends CrudRepository<Molen, Integer>
{
}
