# NewsApp
A news android app using RecyclerView and CardView.
It fetches the JSON data from https://newsapi.org/v2/top-headlines?country=in&apiKey=3d5998d023614120acefd255e7017c2a and parse it using **Volley** library.
The parsed data is shown in a RecyclerView which consists of CardView.
It also gets the **image url** from the JSON data and load it using **Picasso** library.
