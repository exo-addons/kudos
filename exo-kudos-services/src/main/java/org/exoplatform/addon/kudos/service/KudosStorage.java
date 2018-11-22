package org.exoplatform.addon.kudos.service;

import java.time.YearMonth;
import java.util.List;

import org.exoplatform.addon.kudos.model.Kudos;

public interface KudosStorage {

  Kudos getKudoById(long id);

  void saveKudos(Kudos kudos);

  List<Kudos> getAllKudosByMonth(YearMonth yearMonth);

  List<Kudos> getAllKudosByMonthAndEntityType(YearMonth yearMonth, String entityType);

  List<Kudos> getAllKudosByEntity(String entityType, String entityId);

  long countKudosByMonthAndSender(YearMonth yearMonth, String senderId);

}
