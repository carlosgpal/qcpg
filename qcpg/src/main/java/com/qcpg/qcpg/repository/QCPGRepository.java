package com.qcpg.qcpg.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.cassandra.repository.CassandraRepository;
import com.qcpg.qcpg.entities.QCPG;

@Repository
public interface QCPGRepository extends CassandraRepository<QCPG, Long> {
}