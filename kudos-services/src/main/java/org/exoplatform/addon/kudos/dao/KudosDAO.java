package org.exoplatform.addon.kudos.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.exoplatform.addon.kudos.entity.KudosEntity;
import org.exoplatform.addon.kudos.model.KudosPeriod;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

public class KudosDAO extends GenericDAOJPAImpl<KudosEntity, Long> {
  private static final Log    LOG                               = ExoLogger.getLogger(KudosDAO.class);

  private static final String KUDOS_REMOVAL_IS_DISABLED_MESSAGE = "Kudos removal is disabled";

  @Override
  public void deleteAll() {
    throw new IllegalStateException(KUDOS_REMOVAL_IS_DISABLED_MESSAGE);
  }

  @Override
  public void delete(KudosEntity entity) {
    throw new IllegalStateException(KUDOS_REMOVAL_IS_DISABLED_MESSAGE);
  }

  @Override
  public void deleteAll(List<KudosEntity> entities) {
    throw new IllegalStateException(KUDOS_REMOVAL_IS_DISABLED_MESSAGE);
  }

  public List<KudosEntity> getAllKudosByPeriod(KudosPeriod kudosPeriod) {
    TypedQuery<KudosEntity> query = getEntityManager().createNamedQuery("Kudos.getAllKudosByPeriod", KudosEntity.class);
    setPeriodParameters(query, kudosPeriod);
    return query.getResultList();
  }

  public List<KudosEntity> getAllKudosByPeriodAndEntityType(KudosPeriod kudosPeriod, int entityType) {
    TypedQuery<KudosEntity> query = getEntityManager().createNamedQuery("Kudos.getAllKudosByPeriodAndEntityType",
                                                                        KudosEntity.class);
    setPeriodParameters(query, kudosPeriod);
    query.setParameter("entityType", entityType);
    return query.getResultList();
  }

  public List<KudosEntity> getAllKudosByEntity(int entityType, long entityId) {
    TypedQuery<KudosEntity> query = getEntityManager().createNamedQuery("Kudos.getAllKudosByEntity", KudosEntity.class);
    query.setParameter("entityId", entityId);
    query.setParameter("entityType", entityType);
    return query.getResultList();
  }

  public List<KudosEntity> getKudosByPeriodAndReceiver(KudosPeriod kudosPeriod, long receiverId, boolean isReceiverUser) {
    TypedQuery<KudosEntity> query = getEntityManager().createNamedQuery("Kudos.getKudosByPeriodAndReceiver", KudosEntity.class);
    setPeriodParameters(query, kudosPeriod);
    query.setParameter("receiverId", receiverId);
    query.setParameter("isReceiverUser", isReceiverUser);
    return query.getResultList();
  }

  public long countKudosByPeriodAndReceiver(KudosPeriod kudosPeriod, long receiverId, boolean isReceiverUser) {
    TypedQuery<Long> query = getEntityManager().createNamedQuery("Kudos.countKudosByPeriodAndReceiver", Long.class);
    setPeriodParameters(query, kudosPeriod);
    query.setParameter("receiverId", receiverId);
    query.setParameter("isReceiverUser", isReceiverUser);
    Long count = query.getSingleResult();
    return count == null ? 0 : count;
  }

  public List<KudosEntity> getKudosByPeriodAndSender(KudosPeriod kudosPeriod, long senderId) {
    TypedQuery<KudosEntity> query = getEntityManager().createNamedQuery("Kudos.getKudosByPeriodAndSender", KudosEntity.class);
    setPeriodParameters(query, kudosPeriod);
    query.setParameter("senderId", senderId);
    return query.getResultList();
  }

  public long countKudosByPeriodAndSender(KudosPeriod kudosPeriod, long senderId) {
    TypedQuery<Long> query = getEntityManager().createNamedQuery("Kudos.countKudosByPeriodAndSender", Long.class);
    setPeriodParameters(query, kudosPeriod);
    query.setParameter("senderId", senderId);
    return query.getSingleResult();
  }

  public KudosEntity findKudosByActivityId(long activityId) {
    TypedQuery<KudosEntity> query = getEntityManager().createNamedQuery("Kudos.findKudosByActivityId", KudosEntity.class);
    query.setParameter("activityId", activityId);
    List<KudosEntity> result = query.getResultList();
    if (result == null || result.isEmpty()) {
      return null;
    } else {
      if (result.size() > 1) {
        LOG.warn("More than one result are returned for Kudos per activity with id {}", activityId);
      }
      return result.get(0);
    }
  }

  private void setPeriodParameters(TypedQuery<?> query, KudosPeriod kudosPeriod) {
    query.setParameter("startDate", kudosPeriod.getStartDateInSeconds());
    query.setParameter("endDate", kudosPeriod.getEndDateInSeconds());
  }

}