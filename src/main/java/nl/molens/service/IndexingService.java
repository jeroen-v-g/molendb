package nl.molens.service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class IndexingService {

  @Autowired
  private EntityManager entityManager;
  private FullTextEntityManager fullTextEntityManager;


   public FullTextEntityManager getFullTextEntityManager() {
    if (fullTextEntityManager==null)
    {
      this.fullTextEntityManager = initiateIndexing(entityManager);
    }
    return Search.getFullTextEntityManager(entityManager);
  }

  @Transactional
  public FullTextEntityManager initiateIndexing(EntityManager em)
  {
    this.entityManager=em;
    FullTextEntityManager fullTextEntityManager = null;
    try {
        fullTextEntityManager =
        Search.getFullTextEntityManager(em);
        fullTextEntityManager.createIndexer().startAndWait();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return fullTextEntityManager;
  }

}
