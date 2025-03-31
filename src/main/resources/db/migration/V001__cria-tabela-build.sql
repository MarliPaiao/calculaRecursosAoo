CREATE TABLE building (
    id BIGINT NOT NULL AUTO_INCREMENT,
    stage BIGINT NOT NULL,
    name VARCHAR(60) NOT NULL,
    buildingType ENUM('MAIN_HALL', 'CITY_WALL', 'ACADEMY', 'GARAGE', 'HOSPITAL', 'MEDIC_STATION', 'CAMP', 'FACTORY',
                      'FARM', 'REFN_OIL', 'STEEL_MILL', 'MINERAL_MINE', 'DEPOT', 'RECON_CENTER', 'EMBASSY',
                      'WORKSHOP', 'HOUSE', 'DISPATCH_CENTER'),
    qtdFood BIGINT,
    qtdOil BIGINT,
    qtdIron BIGINT,
    qtdMinerals BIGINT,
    qtdUranium BIGINT,
    troopType VARCHAR(60),
    recruitingSpeed BIGINT,
    woundedCapacity BIGINT,
    healingSpeed BIGINT,
    fleetLimitBonus BIGINT,
    PRIMARY KEY (id)
);
alter table building
add constraint UK_building unique (stage, name);