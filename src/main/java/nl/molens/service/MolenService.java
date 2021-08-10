package nl.molens.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nl.molens.jpa.MolenMapper;
import nl.molens.jpa.MolenRepository;
import nl.molens.model.Molen;
import nl.molens.model.bs.MolenBS;

@Service
public class MolenService {
  @Autowired
  MolenRepository molenRepository;
  @Autowired
  EntityManager entityManager;
  @Autowired
  IndexingService indexingService;
  @Autowired
  MolenMapper molenMapper;

  public Molen getMolenById(int id) {
    return molenRepository.findById(id).orElse(null);
  }

  public Molen addOrUpdateMolen(Molen molenDto) {
    if (molenDto.getMolenId() != 0) {
      Molen molen = getMolenById(molenDto.getMolenId());
      molenMapper.updateMolenFromDto(molenDto, molen);
      return molenRepository.save(molen);
    } else {
      return molenRepository.save(molenDto);
    }

  }

  public void deleteMolen(Molen molen) {
    molenRepository.delete(molen);
  }

  public List<MolenBS> getMolenBSList(int offset, int limit) {
    ArrayList<MolenBS> list = new ArrayList<>();
    entityManager.createNamedQuery("Molens.scrollMolens", Molen.class).setFirstResult(offset) // equivalent to OFFSET
        .setMaxResults(limit) // equivalent to LIMIT
        .getResultList().forEach(molen -> {
          MolenBS molenBS = new MolenBS();
          molenBS.cloneFromMolen(molen);
          list.add(molenBS);
        });
    return list;

  }

  public List<MolenBS> getMolenBSList(int offset, int limit, String keywords) {

    FullTextEntityManager fullTextEntityManager = indexingService.getFullTextEntityManager();

    QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Molen.class).get();

    Query molenQueryText = qb.simpleQueryString()
        .onFields("naam", "type", "kenmerken", "functie", "adres", "molenaar", "eigenaar", "plaats").matching(keywords)
        .createQuery();

    String intSearch = keywords.replaceAll("[^0-9]", "");
    Query molenQuery;
    if (intSearch.length() > 0) {
      int jaar = Integer.parseInt(intSearch);
      Query molenQueryNumber = qb.keyword().onField("bouwjaar").matching(jaar).createQuery();
      molenQuery = qb.bool().should(molenQueryText).should(molenQueryNumber).createQuery();
    } else {
      molenQuery = molenQueryText;
    }

    FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(molenQuery, Molen.class);
    fullTextQuery.setFirstResult(offset).setMaxResults(limit);

    List<MolenBS> molens = new ArrayList<>();

    List<?> molensList = fullTextQuery.getResultList();

    molensList.forEach(molen -> {
      MolenBS molenBS = new MolenBS();
      molenBS.cloneFromMolen((Molen) molen);
      molens.add(molenBS);
    });

    return molens;

  }
}
