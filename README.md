# Image Searcher

This application is a demo of searching .csv files using Apache Lucene.

### How to start?
- clone the repo
- create `dataset` and `index` folders in `scr/main/resources` 
- download dataset images from [Open Images Dataset V3](https://storage.googleapis.com/openimages/2017_11/images_2017_11.tar.gz)
- extract content and copy all .csv files (different names) into `scr/main/resources/dataset`
- to start the application run command `mvn spring-boot:run`
- wait a few minutes to complete indexing after which server should start listening on `http://localhost:8080`
