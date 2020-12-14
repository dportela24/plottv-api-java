# PlotTV - API

## Project Summary

PlotTv - API implements the back-end of PlotTv, a full stack project for developing an app that plots the evolution of TV Series' IMDB ratings over time.

The application's client side is available [here](https://github.com/dportela24/budget-board-client), implemented using React.

**Note:** Both server and client side of this project are still under development.

## Built with

This project was developed using Spring Boot + Maven. Data about new series is fetched using the project's [IMDBScraper](https://github.com/dportela24/IMDBScraper-java).
The database is managed through MySql using the Hibernate framework.

## Usage

For now, the server exposes a single endpoint for `GET` requests, `/tvseries`, to request data concerning a given TV Series.
This endpoint supports requests both for the TV Series name as for its IMDB ID, through a `q` query parameter.

As an example, a request for data about the TV Series Game Of Thrones can be performed as follows:

`/tvseries?q=Game+Of+Thrones` or `/tvseries?q=tt0944947`

A sucessfull response from this endpoint returns a JSON following the structure:

```json
{
   "imdbID":"tt0944947",
   "name":"Game of Thrones",
   "originalName":"Game of Thrones",
   "summary":"Nine noble families fight for control over the lands of Westeros, while an ancient enemy returns after being dormant for millennia.",
   "episodeDuration":"57min",
   "runtime":"2011–2019",
   "genres":[
      "Action",
      "Adventure",
      "Drama"
   ],
   "ratingValue":9.3,
   "ratingCount":1728,
   "poster":"https://m.media-amazon.com/images/M/MV5BYTRiNDQwYzAtMzVlZS00NTI5LWJjYjUtMzkwNTUzMWMxZTllXkEyXkFqcGdeQXVyNDIzMzcwNjc@._V1_UY268_CR7,0,182,268_AL_.jpg",
   "numberOfSeasons":8,
   "seasons":[
      {
         "number":1,
         "episodeList":[
            {
               "imdbID":"tt1480055",
               "number":"1",
               "name":"Winter Is Coming",
               "airdate":"17 Apr. 2011",
               "ratingValue":9.1,
               "ratingCount":40002,
               "summary":"Eddard Stark is torn between his family and an old friend when asked to serve at the side of King Robert Baratheon; Viserys plans to wed his sister to a nomadic warlord in exchange for an army."
            },
            {
               "imdbID":"tt1668746",
               "number":"2",
               "name":"The Kingsroad",
               "airdate":"24 Apr. 2011",
               "ratingValue":8.8,
               "ratingCount":30325,
               "summary":"While Bran recovers from his fall, Ned takes only his daughters to King's Landing. Jon Snow goes with his uncle Benjen to the Wall. Tyrion joins them."
            },

            ....
```

A complete representation of a full response JSON is available [here](https://github.com/dportela24/IMDBScrapper/blob/main/example_result).
