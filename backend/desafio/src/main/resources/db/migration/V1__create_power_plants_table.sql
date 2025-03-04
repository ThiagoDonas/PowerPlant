CREATE TABLE power_plants (
    id SERIAL PRIMARY KEY,
    codeCEG VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    state VARCHAR(2) NOT NULL,
    energy_source VARCHAR(255) NOT NULL,
    generation_type VARCHAR(50) NOT NULL,
    capacity_kw DOUBLE PRECISION NOT NULL,
    status VARCHAR(100) NOT NULL,
    construction_start DATE,
    commissioning_date DATE,
    connection_type VARCHAR(100),
    connection_company VARCHAR(255),
    connection_voltage DOUBLE PRECISION,
    report_date DATE NOT NULL
);
