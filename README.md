Scratchpad
======

Application used for know analystics of SQLE and MLE functions performance on Cloud platforms like Azure, AWS, GCP

## Build from source ##

### MacOS/Linux/Unix ###

#### Prerequisites ####

* Git
* Java 11
* Docker
* Maven


#### MacOS / Linux ####

``` bash
git clone "this repository"

cd scratchpad

mvn clean install

docker build -t scratchpad

docker run -p 8080:8080 -t scratchpad
```


#### Test ####

Run the following command to see if it works.

Open application in browser with host name as docker run host name and port number is 8080.
Example : docker execution machine sdt1234
hostname:  8080.

Open following url sdt1234:8080.
