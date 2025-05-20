CREATE TABLE requirements (
    building_id INT NOT NULL,
    requirements_id INT NOT NULL,
    PRIMARY KEY (building_id, requirements_id),
    FOREIGN KEY (building_id) REFERENCES building(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (requirements_id) REFERENCES building(id) ON DELETE CASCADE ON UPDATE CASCADE
);