# Louisville Golf Clubs Data Analysis
source data set: https://catalog.data.gov/dataset/louisville-metro-ky-parks-golf-sales-detail

## Python Data Analysis (LouisvilleData.ipynb)
A Jupyter notebook that processes the golf club data and shows key metrics such as total sales, best-selling products, 
and a graph of sale trends by month.

## Java REST Service (/demo directory)
A Java REST spring service that reads the data as a csv and exposes several get API methods in order to serve that data. 

/sales
A get endpoint that returns a list of all the sales transactions.

/products
A get endpoint that returns a map of itemId to Product, which contains key metrics such as total quantity sold and total sales of that product.

/salesByMonth 
A get endpoint that returns a map of month (as an int) to Month, which contains key metrics about that month such as total quantity sold and total sales in that month.

## Angular Frontend (/demo-frontend directory)
A frontend app that displays two analysis charts based on data received from the backend service

## Running the Demo
1. Start the Backend Java Service in the demo directory, which will run on http://localhost:8080
2. Install frontend dependencies with npm i in the demo-frontend directory
3. Run the frontend with npm start, which will be available at http://localhost:4200