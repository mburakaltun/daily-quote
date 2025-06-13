package com.mba.dailyquote.common.repository;

import com.mba.dailyquote.common.model.entity.RequestLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestLogRepository extends JpaRepository<RequestLogEntity, Long> {
}
