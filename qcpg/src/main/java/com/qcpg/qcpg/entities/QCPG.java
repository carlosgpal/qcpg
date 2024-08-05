package com.qcpg.qcpg.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class QCPG {

    @Id
    @PrimaryKey
    @CassandraType(type = Name.UUID)
    private Long id;
    private String filename;
    private String content;

    @CassandraType(type = Name.LIST, typeArguments = Name.UDT, userTypeName = "generic_node")
    private List<GenericNode> nodes;
}
