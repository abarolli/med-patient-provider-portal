CREATE TABLE IF NOT EXISTS r3_patient_provider_portal_schema.patients (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    age INT NOT NULL,
    is_active BOOLEAN DEFAULT true NOT NULL
);

CREATE TABLE IF NOT EXISTS r3_patient_provider_portal_schema.locations (
	id SERIAL PRIMARY KEY,
    address VARCHAR(255) NOT NULL,
    stripe_account VARCHAR(255) NOT NULL,
    is_active BOOLEAN DEFAULT true NOT NULL
);

CREATE TABLE IF NOT EXISTS r3_patient_provider_portal_schema.providers (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    is_active BOOLEAN DEFAULT true NOT NULL
);

CREATE TABLE IF NOT EXISTS r3_patient_provider_portal_schema.provider_locations (
    location_id INT NOT NULL,
    provider_id INT NOT NULL,
    PRIMARY KEY (location_id, provider_id),
    FOREIGN KEY (location_id) REFERENCES r3_patient_provider_portal_schema.locations(id),
    FOREIGN KEY (provider_id) REFERENCES r3_patient_provider_portal_schema.providers(id)
);

CREATE TABLE IF NOT EXISTS r3_patient_provider_portal_schema.appointments (
    id SERIAL PRIMARY KEY,
    patient_id INT NOT NULL,
    location_id INT NOT NULL,
    provider_id INT NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES r3_patient_provider_portal_schema.patients(id),
    FOREIGN KEY (location_id) REFERENCES r3_patient_provider_portal_schema.locations(id),
    FOREIGN KEY (provider_id) REFERENCES r3_patient_provider_portal_schema.providers(id),
    appointment_time TIMESTAMPTZ NOT NULL
);

CREATE TABLE IF NOT EXISTS r3_patient_provider_portal_schema.invoices (
    id SERIAL PRIMARY KEY,
    appointment_id INT NOT NULL,
    amount_due NUMERIC(12, 2) NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('pending', 'payed'))
);


CREATE TABLE IF NOT EXISTS r3_patient_provider_portal_schema.invoice_items (
    id SERIAL PRIMARY KEY,
    invoice_id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    price NUMERIC(12, 2) NOT NULL
);