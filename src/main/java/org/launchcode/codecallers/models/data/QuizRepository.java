package org.launchcode.codecallers.models.data;

import org.launchcode.codecallers.models.QuizHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends CrudRepository<QuizHistory, Integer> {

}
