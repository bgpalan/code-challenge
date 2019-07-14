mkdir test && cd testecho "cloning the repo..."
git clone https://github.com/bgpalan/code-challenge.git
cd code-challenge
mvn clean install && cd target
java -jar migration-service.jar

