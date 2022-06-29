# a-clean-eva

1 Init

1. mvn -N io.takari:maven:wrapper, .gitignore
2. Modules
    1. in root dir >> aggregate our library and application modules
    2. parent >> manage dependencies and properties
    3. domain >> jar for our model classes with jpa annotations, responsible for business logic //TODO >> in ddd design
       replase with jmolecules annotations
    4. app >> jar with application services and port-interfaces //TODO jmolecules
    5. io >> jar with adapters for incoming and outgoing communication with application and infrastructure
    6. startup >>

--

1.use create-drop with h2. when schemas look ok export sql-script 1.ddl-auto=drop-create, platform=h2
2.ddl-auto=drop-create, platform=h2, scripts 2.use postgres with flyway and baseline from exported sql-script