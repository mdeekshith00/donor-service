CREATE TABLE donor (
    donor_id SERIAL PRIMARY KEY,

    user_id INT NOT NULL UNIQUE, -- refers to user-service user
    blood_group VARCHAR(10),
    donation_eligibility_status VARCHAR(50),
    is_available_to_donate BOOLEAN,

    last_donation_date DATE,
    next_eligible_date DATE,
    total_donations INT DEFAULT 0,
    total_units_donated INT DEFAULT 0,

    is_eligible_to_donate BOOLEAN,
    ineligibility_reason TEXT,
    temporarily_ineligible_until DATE,

    is_active BOOLEAN DEFAULT TRUE,
    is_verified BOOLEAN DEFAULT FALSE,

    registered_via VARCHAR(20),
    weight_in_kg NUMERIC(5,2),
    hemoglobin_level NUMERIC(4,1),
    has_chronic_diseases BOOLEAN,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    status VARCHAR(20), -- ACTIVE, INACTIVE, DECEASED

    recent_medications TEXT,
    medical_conditions TEXT
);

-- Optional: Indexes for faster lookups
CREATE INDEX idx_donor_user_id ON donor(user_id);
CREATE INDEX idx_donor_blood_group ON donor(blood_group);
CREATE INDEX idx_donor_status ON donor(status);
