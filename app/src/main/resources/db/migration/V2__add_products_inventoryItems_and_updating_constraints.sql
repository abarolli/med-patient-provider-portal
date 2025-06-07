CREATE TABLE IF NOT EXISTS r3_patient_provider_portal_schema.products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);


CREATE TABLE IF NOT EXISTS r3_patient_provider_portal_schema.inventory_items (
    location_id INT NOT NULL,
    product_id INT NOT NULL,
    PRIMARY KEY (location_id, product_id),
    FOREIGN KEY (location_id) REFERENCES r3_patient_provider_portal_schema.locations(id),
    FOREIGN KEY (product_id) REFERENCES r3_patient_provider_portal_schema.products(id),
    price NUMERIC(12, 2) NOT NULL,
    quantity INT NOT NULL
);


ALTER TABLE r3_patient_provider_portal_schema.invoices
ADD CONSTRAINT uq_appointment_id UNIQUE (appointment_id);