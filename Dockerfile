FROM ubuntu:16.04

RUN apt-get -qq -y update && \
    apt-get -qq -y install openjdk-8-jdk maven gradle curl && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /src
ADD . .
RUN ./service.sh dev_build
CMD ["/src/service.sh", "dev_run", "data/example"]
