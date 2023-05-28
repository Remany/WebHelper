package ru.CatsProgers.WebHelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.CatsProgers.WebHelper.models.LastResult;
@Repository
public interface LastResultRepository extends JpaRepository<LastResult, Integer> {
}
