package ru.sovcombank.frontoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sovcombank.frontoffice.entity.FrontSysReport;
import ru.sovcombank.frontoffice.entity.FrontSysReportPK;

public interface FrontSysReportRepository extends JpaRepository<FrontSysReport, FrontSysReportPK> {
}
