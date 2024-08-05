CREATE KEYSPACE IF NOT EXISTS qcpg WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

CREATE TABLE IF NOT EXISTS qcpg.qcpg (
    id UUID PRIMARY KEY,
    filename TEXT,
    content TEXT,
    nodes TEXT
);